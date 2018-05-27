package xyz.tuny.project.system.user.dao;

import java.util.List;

import xyz.tuny.framework.web.dao.BaseDao;
import xyz.tuny.project.system.user.domain.UserRole;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xyz.tuny.framework.web.dao.BaseDao;

/**
 * 用户表 数据层
 * 
 * @author tuny
 */
@Repository
public interface IUserRoleDao extends BaseDao<UserRole, Long>
{

    /**
     * 通过用户ID删除用户和角色关联
     * 
     * @param userId 用户ID
     * @return 结果
     */
    @Modifying
    @Transactional(propagation = Propagation.MANDATORY)
    @Query(value = "delete from sys_user_role where user_id=?1",nativeQuery = true)
    public void delUserRoleByUserId(Long userId);

    /**
     * 通过角色ID删除用户和角色关联
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    @Modifying
    @Query(value = "delete from sys_user_role where role_id=?1",nativeQuery = true)
    public void deleteUserRoleByRoleId(Long roleId);

}
