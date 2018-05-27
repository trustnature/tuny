package xyz.tuny.project.system.user.service;

import java.util.List;

import xyz.tuny.framework.web.page.TableDataInfo;
import xyz.tuny.project.system.user.domain.User;
import org.springframework.data.domain.PageRequest;
import xyz.tuny.framework.web.page.TableDataInfo;
import xyz.tuny.project.system.user.domain.User;

/**
 * 用户 业务层
 * 
 * @author tuny
 */
public interface IUserService
{

    /**
     * 根据条件分页查询用户对象
     * 
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    public TableDataInfo selectUserList(PageRequest pageRequest, User user);

    /**
     * 通过用户名查询用户
     * 
     * @param userName 用户名
     * @return 用户对象信息
     */
    public User selectUserByName(String userName);

    /**
     * 通过用户ID查询用户
     * 
     * @param userId 用户ID
     * @return 用户对象信息
     */
    public User selectUserById(Long userId);

    /**
     * 通过用户ID删除用户
     * 
     * @param userId 用户ID
     * @return 结果
     */
    public boolean deleteUserById(Long userId);

    /**
     * 批量删除用户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public boolean batchDeleteUser(Long[] ids);

    /**
     * 保存用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    public boolean saveUser(User user);
    
    /**
     * 修改用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    public boolean updateUser(User user);

    /**
     * 校验用户名称是否唯一
     * 
     * @param loginName 登录名称
     * @return 结果
     */
    public String checkNameUnique(String loginName);

}
