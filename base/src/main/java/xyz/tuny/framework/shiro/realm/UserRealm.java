package xyz.tuny.framework.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.tuny.common.exception.user.RoleBlockedException;
import xyz.tuny.common.exception.user.UserBlockedException;
import xyz.tuny.common.exception.user.UserNotExistsException;
import xyz.tuny.common.exception.user.UserPasswordNotMatchException;
import xyz.tuny.common.exception.user.UserPasswordRetryLimitExceedException;
import xyz.tuny.common.utils.security.ShiroUtils;
import xyz.tuny.framework.shiro.service.LoginService;
import xyz.tuny.project.system.menu.service.IMenuService;
import xyz.tuny.project.system.role.service.IRoleService;
import xyz.tuny.project.system.user.domain.User;
import xyz.tuny.common.exception.user.UserBlockedException;
import xyz.tuny.common.exception.user.UserNotExistsException;
import xyz.tuny.common.exception.user.UserPasswordNotMatchException;
import xyz.tuny.common.exception.user.UserPasswordRetryLimitExceedException;
import xyz.tuny.common.utils.security.ShiroUtils;
import xyz.tuny.project.system.menu.service.IMenuService;
import xyz.tuny.project.system.role.service.IRoleService;
import xyz.tuny.project.system.user.domain.User;

/**
 * 自定义Realm 处理登录 权限
 * 
 * @author tuny
 */
public class UserRealm extends AuthorizingRealm
{
    private static final Logger log = LoggerFactory.getLogger(UserRealm.class);

    private static final Logger logeer = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private IMenuService menuService;
    
    @Autowired
    private IRoleService roleService;

    @Autowired
    private LoginService loginService;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0)
    {
        Long userId = ShiroUtils.getUserId();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 角色加入AuthorizationInfo认证对象
        info.setRoles(roleService.selectRoleKeys(userId));
        // 权限加入AuthorizationInfo认证对象
        info.setStringPermissions(menuService.selectPermsByUserId(userId));
        return info;
    }

    /**
     * 登录认证
     * principle是一个user实例
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
    {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        String password = "";
        if (upToken.getPassword() != null)
        {
            password = new String(upToken.getPassword());
        }

        User user = null;
        try
        {
            user = loginService.login(username, password);
        }
        catch (UserNotExistsException e)
        {
            throw new UnknownAccountException(e.getMessage(), e);
        }
        catch (UserPasswordNotMatchException e)
        {
            throw new IncorrectCredentialsException(e.getMessage(), e);
        }
        catch (UserPasswordRetryLimitExceedException e)
        {
            throw new ExcessiveAttemptsException(e.getMessage(), e);
        }
        catch (UserBlockedException e)
        {
            throw new LockedAccountException(e.getMessage(), e);
        }
        catch (Exception e)
        {
            logeer.info("对用户[" + username + "]进行登录验证..验证未通过{}", e.getMessage());
            throw new AuthenticationException(e.getMessage(), e);
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }

}
