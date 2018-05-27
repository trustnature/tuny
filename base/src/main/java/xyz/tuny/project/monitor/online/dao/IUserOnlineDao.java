package xyz.tuny.project.monitor.online.dao;

import java.util.List;

import xyz.tuny.framework.web.dao.BaseDao;
import xyz.tuny.project.monitor.online.domain.UserOnline;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import xyz.tuny.framework.web.dao.BaseDao;

/**
 * 在线用户 数据层
 * 
 * @author tuny
 */
@Repository
public interface IUserOnlineDao extends BaseDao<UserOnline, String>
{
    /**
     * 通过会话序号查询信息
     * 
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    public UserOnline findUserOnlineBySessionId(String sessionId);


    /**
     * 查询过期会话集合
     * 
     * @param lastAccessTime 过期时间
     * @return 会话集合
     */
    @Query(value = "SELECT * FROM sys_user_online o " +
            "WHERE o.last_access_time <= ?1 ORDER BY o.last_access_time ASC",nativeQuery = true)
    public List<UserOnline> selectOnlineByExpired(String lastAccessTime);
}
