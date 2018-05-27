package xyz.tuny.project.monitor.job.dao;

import java.util.List;

import xyz.tuny.framework.web.dao.BaseDao;

import xyz.tuny.project.monitor.job.domain.JobLog;
import org.springframework.stereotype.Repository;
import xyz.tuny.framework.web.dao.BaseDao;
import xyz.tuny.project.monitor.job.domain.JobLog;

/**
 * 调度任务日志信息 数据层
 * 
 * @author tuny
 */
@Repository
public interface IJobLogDao extends BaseDao<JobLog, Long>
{

    /**
     * 通过调度任务日志ID查询调度信息
     *
     * @param jobId 调度任务日志ID
     * @return 调度任务日志对象信息
     */
    public JobLog findJobLogByJobLogId(Long jobLogId);


}
