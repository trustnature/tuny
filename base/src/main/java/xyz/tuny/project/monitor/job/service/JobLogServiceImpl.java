package xyz.tuny.project.monitor.job.service;

import java.util.ArrayList;
import java.util.List;

import xyz.tuny.framework.web.page.TableDataInfo;
import xyz.tuny.project.monitor.job.domain.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import xyz.tuny.project.monitor.job.dao.IJobLogDao;
import xyz.tuny.project.monitor.job.domain.JobLog;
import xyz.tuny.framework.web.page.TableDataInfo;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 定时任务调度日志信息 服务层
 * 
 * @author tuny
 */
@Service("jobLogService")
public class JobLogServiceImpl implements IJobLogService
{

    @Autowired
    private IJobLogDao jobLogDao;

    /**
     * 获取quartz调度器日志的计划任务
     * 
     * @param jobLog 调度日志信息
     * @return 调度任务日志集合
     */
    public TableDataInfo selectJobLogList(PageRequest pageRequest, JobLog jobLog)
    {
        String keyword = jobLog.getSearchValue();
        Specification<JobLog> spec = new Specification<JobLog>() {
            @Override
            public Predicate toPredicate(Root<JobLog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
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
        Page<JobLog> pageJobLog = jobLogDao.findAll(spec,pageRequest);
        TableDataInfo rspData = new TableDataInfo();
        rspData.setRows(pageJobLog.getContent());
        rspData.setTotal(pageJobLog.getTotalElements());
        return rspData;
    }

    /**
     * 通过调度任务日志ID查询调度信息
     * 
     * @return 调度任务日志对象信息
     */
    public JobLog selectJobLogById(Long jobLogId)
    {
        return jobLogDao.findJobLogByJobLogId(jobLogId);
    }

    /**
     * 新增任务日志
     * 
     * @param jobLog 调度日志信息
     */
    public void addJobLog(JobLog jobLog)
    {
        jobLogDao.save(jobLog);
    }

    /**
     * 批量删除调度日志信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public boolean batchDeleteJoblog(Long[] ids)
    {
        List<JobLog> lj = new ArrayList<>();
        for (Long id : ids) {
            JobLog jobLog = new JobLog();
            jobLog.setJobLogId(id);
            lj.add(jobLog);
        }
        jobLogDao.deleteInBatch(lj);
        return true;
    }

    /**
     * 删除任务日志
     * 
     * @param jobId 调度日志ID
     */
    public boolean deleteJobLogById(Long jobId)
    {
         jobLogDao.delete(jobId);
         return true;
    }

}
