package xyz.tuny.project.monitor.job.service;

import java.util.List;

import xyz.tuny.framework.web.page.TableDataInfo;
import xyz.tuny.project.monitor.job.domain.JobLog;
import org.springframework.data.domain.PageRequest;
import xyz.tuny.framework.web.page.TableDataInfo;

/**
 * 定时任务调度日志信息信息 服务层
 * 
 * @author tuny
 */
public interface IJobLogService
{

    /**
     * 获取quartz调度器日志的计划任务
     *
     * @param jobLog 调度日志信息
     * @return 调度任务日志集合
     */
    public TableDataInfo selectJobLogList(PageRequest pageRequest, JobLog jobLog);

    /**
     * 通过调度任务日志ID查询调度信息
     * 
     * @param jobId 调度任务日志ID
     * @return 调度任务日志对象信息
     */
    public JobLog selectJobLogById(Long jobLogId);

    /**
     * 新增任务日志
     * 
     * @param jobLog 调度日志信息
     */
    public void addJobLog(JobLog jobLog);

    /**
     * 批量删除调度日志信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public boolean batchDeleteJoblog(Long[] ids);

    /**
     * 删除任务日志
     * 
     * @param jobId 调度日志ID
     */
    public boolean deleteJobLogById(Long jobId);

}
