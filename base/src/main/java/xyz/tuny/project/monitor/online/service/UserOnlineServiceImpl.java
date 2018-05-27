package xyz.tuny.project.monitor.online.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import xyz.tuny.framework.web.page.TableDataInfo;
import xyz.tuny.project.monitor.operlog.domain.OperLog;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import xyz.tuny.common.utils.DateUtils;
import xyz.tuny.framework.shiro.session.OnlineSessionDAO;
import xyz.tuny.project.monitor.online.dao.IUserOnlineDao;
import xyz.tuny.project.monitor.online.domain.UserOnline;
import xyz.tuny.framework.web.page.TableDataInfo;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 在线用户 服务层处理
 * 
 * @author tuny
 */
@Service("userOnlineService")
public class UserOnlineServiceImpl implements IUserOnlineService
{
    @Autowired
    private IUserOnlineDao userOnlineDao;

    @Autowired
    private OnlineSessionDAO onlineSessionDAO;

    /**
     * 通过会话序号查询信息
     * 
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    @Override
    public UserOnline selectOnlineById(String sessionId)
    {
        return userOnlineDao.findUserOnlineBySessionId(sessionId);
    }

    /**
     * 通过会话序号删除信息
     * 
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    @Override
    public void deleteOnlineById(String sessionId)
    {
        UserOnline userOnline = selectOnlineById(sessionId);
        if (userOnline != null)
        {
            userOnlineDao.delete(sessionId);
        }
    }

    /**
     * 通过会话序号删除信息
     * 
     * @param sessions 会话ID集合
     * @return 在线用户信息
     */
    @Override
    public void batchDeleteOnline(List<String> sessions)
    {
        for (String sessionId : sessions)
        {
            UserOnline userOnline = selectOnlineById(sessionId);
            if (userOnline != null)
            {
                userOnlineDao.delete(sessionId);
            }
        }
    }

    /**
     * 保存会话信息
     * 
     * @param online 会话信息
     */
    @Override
    public void saveOnline(UserOnline online)
    {
        userOnlineDao.save(online);
    }

    /**
     * 查询会话集合
     * 
     */
    @Override
    public TableDataInfo selectUserOnlineList(PageRequest pageRequest, UserOnline userOnline)
    {
        String keyword = userOnline.getSearchValue();
        Specification<UserOnline> spec = new Specification<UserOnline>() {
            @Override
            public Predicate toPredicate(Root<UserOnline> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
                List<Predicate> predicates =new ArrayList();
                if (StringUtils.isNotEmpty(keyword)) {
                    Predicate predicateT = builder.like(root.<String> get("loginName"), "%/" + keyword + "%", '/');
                    predicates.add(predicateT);
                }
                // 将所有条件用 and 联合起来
                if (predicates.size() > 0) {
                    return builder.and(predicates.toArray(new Predicate[predicates.size()]));
                }
                return builder.conjunction();
            }
        };
        Page<UserOnline> pageUserOnline = userOnlineDao.findAll(spec,pageRequest);
        TableDataInfo rspData = new TableDataInfo();
        rspData.setRows(pageUserOnline.getContent());
        rspData.setTotal(pageUserOnline.getTotalElements());
        return rspData;
    }

    /**
     * 强退用户
     * 
     * @param sessionId 会话ID
     */
    @Override
    public void forceLogout(String sessionId)
    {
        Session session = onlineSessionDAO.readSession(sessionId);
        if (session == null)
        {
            return;
        }
        session.setTimeout(1000);
        userOnlineDao.delete(sessionId);
    }

    /**
     * 查询会话集合
     * 
     */
    @Override
    public List<UserOnline> selectOnlineByExpired(Date expiredDate)
    {
        String lastAccessTime = DateUtils.dateTime("yyyy-MM-dd HH:mm:ss", expiredDate);
        return userOnlineDao.selectOnlineByExpired(lastAccessTime);
    }
}
