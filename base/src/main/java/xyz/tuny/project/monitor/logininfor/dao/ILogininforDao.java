package xyz.tuny.project.monitor.logininfor.dao;

import java.util.List;

import xyz.tuny.framework.web.dao.BaseDao;
import xyz.tuny.project.monitor.logininfor.domain.Logininfor;
import org.springframework.stereotype.Repository;
import xyz.tuny.framework.web.dao.BaseDao;

/**
 * 系统访问日志情况信息 数据层
 * 
 * @author tuny
 */
@Repository
public interface ILogininforDao extends BaseDao<Logininfor, Long>
{
}
