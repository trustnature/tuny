package xyz.tuny.framework.shiro.web.filter.online;

import java.io.IOException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import xyz.tuny.common.constant.ShiroConstants;
import xyz.tuny.common.utils.security.ShiroUtils;
import xyz.tuny.framework.shiro.session.OnlineSessionDAO;
import xyz.tuny.project.monitor.online.domain.OnlineSession;
import xyz.tuny.project.system.user.domain.User;
import xyz.tuny.common.constant.ShiroConstants;
import xyz.tuny.common.utils.security.ShiroUtils;
import xyz.tuny.project.monitor.online.domain.OnlineSession;
import xyz.tuny.project.system.user.domain.User;

/**
 * Session 管理
 * 调用条件
 * 由SpringMVC管理 而不是shiro管理，只要SpringShiroFilter返回true，就会执行
 * 因为继承了PathMatchingFilter 所以还要URL匹配：
 *   filterChainDefinitionMap.put("/main", "onlineSession,syncOnlineSession");
 *   filterChainDefinitionMap.put("/system/**", "onlineSession,syncOnlineSession");
 *   filterChainDefinitionMap.put("/monitor/**", "onlineSession,syncOnlineSession");
 * @author tuny
 */
public class OnlineSessionFilter extends AccessControlFilter
{

    /**
     * 强制退出后重定向的地址
     */
    @Value("${shiro.user.loginUrl}")
    private String loginUrl;

    @Autowired
    private OnlineSessionDAO onlineSessionDAO;

    /**
     * 表示是否允许访问；mappedValue就是[urls]配置中拦截器参数部分，如果允许访问返回true，否则false；
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception
    {
        Subject subject = getSubject(request, response);
        if (subject == null || subject.getSession() == null)
        {
            return true;
        }
        Session session = onlineSessionDAO.readSession(subject.getSession().getId());//先从cache里面取
        if (session != null && session instanceof OnlineSession)
        {
            OnlineSession onlineSession = (OnlineSession) session;
            request.setAttribute(ShiroConstants.ONLINE_SESSION, onlineSession);
            // 把user对象设置进去
            boolean isGuest = onlineSession.getUserId() == null || onlineSession.getUserId() == 0L;
            if (isGuest == true)
            {
                User user = ShiroUtils.getUser();
                if (user != null)
                {
                    onlineSession.setUserId(user.getUserId());
                    onlineSession.setLoginName(user.getLoginName());
                    onlineSession.setDeptName(user.getDept().getDeptName());
                    onlineSession.markAttributeChanged();
                }
            }

            if (onlineSession.getStatus() == OnlineSession.OnlineStatus.off_line)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * 表示当访问拒绝时是否已经处理了；如果返回true表示需要继续处理；如果返回false表示该拦截器实例已经处理了，将直接返回即可。
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception
    {
        Subject subject = getSubject(request, response);
        if (subject != null)
        {
            subject.logout();
        }
        saveRequestAndRedirectToLogin(request, response);
        return true;
    }

    // 跳转到登录页
    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException
    {
        WebUtils.issueRedirect(request, response, loginUrl);
    }

}
