package xyz.tuny.project.system.user.dao;

import java.util.List;

import xyz.tuny.framework.web.dao.BaseDao;
import xyz.tuny.project.system.role.domain.Role;
import xyz.tuny.project.system.user.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import xyz.tuny.framework.web.dao.BaseDao;
import xyz.tuny.project.system.user.domain.User;

/**
 * 用户表 数据层
 * 
 * @author tuny
 */
@Repository
public interface IUserDao extends BaseDao<User, Long>
{

    /**
     * 根据条件分页查询用户对象
     * 
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    @Query(value = "select * from sys_user where login_name = ?1",nativeQuery = true)
    public List<User> selectUserList(String searchValue);


    /**
     * 根据条件查询用户列表
     * @param searchValue
     * @param deptId
     * @return
     */
    @Query(value = "select * from sys_user where login_name = ?1 " +
            "and dept_id IN (SELECT dept_id FROM sys_dept WHERE dept_id = ?2 OR parent_id = ?2)",nativeQuery = true)
    public List<User> selectUserListInDept(String searchValue,Long deptId);

    @Query(value = "select * from sys_user where dept_id IN (SELECT dept_id FROM sys_dept WHERE dept_id = ?1 OR parent_id = ?1)",nativeQuery = true)
    public List<User> selectUserListInDeptA(Long deptId);


    /**
     * 通过用户名查询用户
     * 
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Query(value = "select  u.* " +
            "from sys_user u " +
            "left join sys_dept d on u.dept_id = d.dept_id " +
            "left join sys_user_role ur on u.user_id = ur.user_id " +
            "where u.login_name = ?1",nativeQuery = true)
    public User selectUserByName(String userName);

    /**
     * 通过用户ID查询用户
     * 
     * @param userId 用户ID
     * @return 用户对象信息
     */
    @Query(value = "select  u.* " +
            "from sys_user u " +
            "left join sys_dept d on u.dept_id = d.dept_id " +
            "left join sys_user_role ur on u.user_id = ur.user_id " +
            "where u.user_id = ?1",nativeQuery = true)
    public User selectUserById(Long userId);

}
