package xyz.tuny.framework.shiro.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.tuny.common.constant.CommonConstant;
import xyz.tuny.common.constant.UserConstants;
import xyz.tuny.common.exception.user.UserBlockedException;
import xyz.tuny.common.exception.user.UserNotExistsException;
import xyz.tuny.common.exception.user.UserPasswordNotMatchException;
import xyz.tuny.common.utils.MessageUtils;
import xyz.tuny.common.utils.SystemLogUtils;
import xyz.tuny.project.system.user.domain.User;
import xyz.tuny.project.system.user.service.IUserService;
import xyz.tuny.common.constant.CommonConstant;
import xyz.tuny.common.constant.UserConstants;
import xyz.tuny.common.exception.user.UserBlockedException;
import xyz.tuny.common.exception.user.UserNotExistsException;
import xyz.tuny.common.exception.user.UserPasswordNotMatchException;
import xyz.tuny.common.utils.MessageUtils;
import xyz.tuny.common.utils.SystemLogUtils;
import xyz.tuny.project.system.user.domain.User;
import xyz.tuny.project.system.user.service.IUserService;

/**
 * 登录校验方法
 * 
 * @author tuny
 */
@Component
public class LoginService
{
    @Autowired
    private PasswordService passwordService;

    @Autowired
    private IUserService userService;

    /**
     * 登录
     */
    public User login(String username, String password)
    {
        // 用户名或密码为空 错误
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
        {
            SystemLogUtils.log(username, CommonConstant.LOGIN_FAIL, MessageUtils.message("not.null"));
            throw new UserNotExistsException();
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            SystemLogUtils.log(username, CommonConstant.LOGIN_FAIL, MessageUtils.message("user.password.not.match"));
            throw new UserPasswordNotMatchException();
        }

        // 用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            SystemLogUtils.log(username, CommonConstant.LOGIN_FAIL, MessageUtils.message("user.password.not.match"));
            throw new UserPasswordNotMatchException();
        }

        // 查询用户信息
        User user = userService.selectUserByName(username);

        if (user == null)
        {
            SystemLogUtils.log(username, CommonConstant.LOGIN_FAIL, MessageUtils.message("user.not.exists"));
            throw new UserNotExistsException();
        }

        passwordService.validate(user, password);

        if (UserConstants.USER_BLOCKED.equals(user.getStatus()))
        {
            SystemLogUtils.log(username, CommonConstant.LOGIN_FAIL, MessageUtils.message("user.blocked", user.getRefuseDes()));
            throw new UserBlockedException(user.getRefuseDes());
        }
        
        SystemLogUtils.log(username, CommonConstant.LOGIN_SUCCESS, MessageUtils.message("user.login.success"));
        return user;
    }

}
