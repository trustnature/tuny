package xyz.tuny.project.monitor.job.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import xyz.tuny.framework.web.page.TableDataInfo;
import xyz.tuny.project.monitor.operlog.domain.OperLog;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import xyz.tuny.common.constant.ScheduleConstants;
import xyz.tuny.common.utils.StringUtils;
import xyz.tuny.common.utils.security.ShiroUtils;
import xyz.tuny.project.monitor.job.dao.IJobDao;
import xyz.tuny.project.monitor.job.domain.Job;
import xyz.tuny.project.monitor.job.util.ScheduleUtils;
import xyz.tuny.framework.web.page.TableDataInfo;

/**
 * 定时任务调度信息 服务层
 * 
 * @author tuny
 */
@Service("jobService")
public class JobServiceImpl implements IJobService
{
    @Autowired
    private Scheduler scheduler;

    @Autowired
    private IJobDao jobDao;

    /**
     * 项目启动时，初始化定时器
     */
    @PostConstruct
    public void init()
    {
        List<Job> jobList = jobDao.findAll();
        for (Job job : jobList)
        {
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, job.getJobId());
            // 如果不存在，则创建
            if (cronTrigger == null)
            {
                ScheduleUtils.createScheduleJob(scheduler, job);
            }
            else
            {
                ScheduleUtils.updateScheduleJob(scheduler, job);
            }
        }
    }

    /**
     * 获取quartz调度器的计划任务列表
     * 
     * @param job 调度信息
     * @return
     */
    @Override
    public TableDataInfo selectJobList(PageRequest pageRequest, Job job)
    {
        String keyword = job.getSearchValue();
        Specification<Job> spec = new Specification<Job>() {
            @Override
            public Predicate toPredicate(Root<Job> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
                List<Predicate> predicates =new ArrayList();
                if (org.apache.commons.lang3.StringUtils.isNotEmpty(keyword)) {
                    Predicate predicateT = builder.like(root.<String> get("jobName"), "%/" + keyword + "%", '/');
                    Predicate predicateP = builder.like(root.<String> get("methodName"), "%/" + keyword + "%", '/');
                    predicates.add(builder.or(predicateT,predicateP));
                }
                // 将所有条件用 and 联合起来
                if (predicates.size() > 0) {
                    return builder.and(predicates.toArray(new Predicate[predicates.size()]));
                }
                return builder.conjunction();
            }
        };
        Page<Job> pageJob = jobDao.findAll(spec,pageRequest);
        TableDataInfo rspData = new TableDataInfo();
        rspData.setRows(pageJob.getContent());
        rspData.setTotal(pageJob.getTotalElements());
        return rspData;
    }

    /**
     * 通过调度任务ID查询调度信息
     * 
     * @param jobId 调度任务ID
     * @return 调度任务对象信息
     */
    @Override
    public Job selectJobById(Long jobId)
    {
        return jobDao.findJobByJobId(jobId);
    }

    /**
     * 暂停任务
     * 
     * @param job 调度信息
     */
    @Override
    public boolean pauseJob(Job job)
    {
        job.setStatus(ScheduleConstants.Status.PAUSE.getValue());
        job.setUpdateBy(ShiroUtils.getLoginName());
        jobDao.save(job);
        ScheduleUtils.pauseJob(scheduler, job.getJobId());
        return true;
    }

    /**
     * 恢复任务
     * 
     * @param job 调度信息
     */
    @Override
    public boolean resumeJob(Job job)
    {
        job.setStatus(ScheduleConstants.Status.NORMAL.getValue());
        job.setUpdateBy(ShiroUtils.getLoginName());
        jobDao.save(job);
        ScheduleUtils.resumeJob(scheduler, job.getJobId());
        return true;
    }

    /**
     * 删除任务后，所对应的trigger也将被删除
     * 
     * @param job 调度信息
     */
    @Override
    public boolean deleteJob(Job job)
    {
        jobDao.delete(job);
        ScheduleUtils.deleteScheduleJob(scheduler, job.getJobId());
        return true;
    }

    /**
     * 批量删除调度信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public void batchDeleteJob(Long[] ids)
    {
        for (Long jobId : ids)
        {
            Job job = jobDao.findJobByJobId(jobId);
            deleteJob(job);
        }
    }

    /**
     * 任务调度状态修改
     * 
     * @param job 调度信息
     */
    @Override
    public boolean changeStatus(Job job)
    {
        int rows = 0;
        int status = job.getStatus();
        if (status == 0)
        {
            resumeJob(job);
        }
        else if (status == 1)
        {
            pauseJob(job);
        }
        return true;
    }

    /**
     * 立即运行任务
     * 
     * @param job 调度信息
     */
    @Override
    public boolean triggerJob(Job job)
    {
        jobDao.save(job);
        ScheduleUtils.run(scheduler, job);
        return true;
    }

    /**
     * 新增任务
     * 
     * @param job 调度信息 调度信息
     */
    @Override
    public boolean addJobCron(Job job)
    {
        job.setCreateBy(ShiroUtils.getLoginName());
        job.setCreateTime(new Date());
        job.setStatus(ScheduleConstants.Status.PAUSE.getValue());
        jobDao.save(job);
        ScheduleUtils.createScheduleJob(scheduler, job);
        return true;
    }

    /**
     * 更新任务的时间表达式
     * 
     * @param job 调度信息
     */
    @Override
    public boolean updateJobCron(Job job)
    {
        jobDao.save(job);
        ScheduleUtils.updateScheduleJob(scheduler, job);
        return true;
    }

    /**
     * 保存任务的时间表达式
     * 
     * @param job 调度信息
     */
    @Override
    public boolean saveJobCron(Job job)
    {
        Long jobId = job.getJobId();
        int rows = 0;
        if (StringUtils.isNotNull(jobId))
        {
            updateJobCron(job);
        }
        else
        {
            addJobCron(job);
        }
        return true;
    }

}
