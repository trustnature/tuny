package xyz.tuny.project.monitor.job.dao;

import java.util.List;

import xyz.tuny.framework.web.dao.BaseDao;
import xyz.tuny.project.monitor.job.domain.Job;
import org.springframework.stereotype.Repository;
import xyz.tuny.framework.web.dao.BaseDao;
import xyz.tuny.project.monitor.job.domain.Job;

/**
 * 调度任务信息 数据层
 * 
 * @author tuny
 */
@Repository
public interface IJobDao extends BaseDao<Job, Long>
{

    /**
     * 通过调度ID查询调度任务信息
     *
     * @param jobId 调度ID
     * @return 角色对象信息
     */
    public Job findJobByJobId(Long jobId);

}
