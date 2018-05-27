package xyz.tuny.framework.shiro.web.filter.sync;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import xyz.tuny.common.constant.ShiroConstants;
import xyz.tuny.framework.shiro.session.OnlineSessionDAO;
import xyz.tuny.project.monitor.online.domain.OnlineSession;
import xyz.tuny.common.constant.ShiroConstants;
import xyz.tuny.project.monitor.online.domain.OnlineSession;

/**
 * 同步Session数据到Db
 * 由SpringMVC管理 而不是shiro管理，只要SpringShiroFilter返回true，就会执行
 * 虽然继承了PathMatchingFilter 但是重写了preHandle，所以无需URL匹配就会执行
 * @author tuny
 */
public class SyncOnlineSessionFilter extends PathMatchingFilter
{
    @Autowired
    private OnlineSessionDAO onlineSessionDAO;

    /**
     * 同步会话数据到DB 一次请求最多同步一次 防止过多处理 需要放到Shiro过滤器之前
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     * 每个受控的URL请求都会走一遍 因为它不是重写的 OnHandle 。preHandle的判断该URL是否匹配该过滤器的功能去掉了
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception
    {
        OnlineSession session = (OnlineSession) request.getAttribute(ShiroConstants.ONLINE_SESSION);
        // 如果session stop了 也不同步
        // session停止时间，如果stopTimestamp不为null，则代表已停止
        if (session != null && session.getUserId() != null && session.getStopTimestamp() == null) {
            onlineSessionDAO.syncToDb(session);
        }//后面有个时间间隔 防止不断写入数据库        }
        return true;
    }
}
