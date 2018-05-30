package xyz.tuny.project.system.role.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import xyz.tuny.framework.web.dao.BaseDao;
import xyz.tuny.project.system.role.domain.RoleMenu;
import org.springframework.stereotype.Repository;
import xyz.tuny.framework.web.dao.BaseDao;

/**
 * 角色与菜单关联表 数据层
 * 
 * @author tuny
 */
@Repository
public interface RoleMenuDao extends BaseDao<RoleMenu, Long>
{
    @Query(value = "delete from sys_role_menu where role_id=?1",nativeQuery = true)
    @Modifying
    public void delRoleMenus(Long roleId);
}
