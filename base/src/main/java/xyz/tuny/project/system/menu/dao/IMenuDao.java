package xyz.tuny.project.system.menu.dao;

import java.util.List;

import xyz.tuny.framework.web.dao.BaseDao;
import xyz.tuny.project.system.menu.domain.Menu;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import xyz.tuny.framework.web.dao.BaseDao;

/**
 * 菜单表 数据层
 * 
 */
@Repository
public interface IMenuDao extends BaseDao<Menu, Long>
{

    /**
     * 根据用户ID查询菜单
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    @Query(value = "select distinct m.* " +
            "from sys_menu m " +
            "left join sys_role_menu rm on m.menu_id = rm.menu_id " +
            "left join sys_user_role ur on rm.role_id = ur.role_id " +
            "where ur.user_id = ?1 and m.menu_type in ('M', 'C') " +
            "order by m.order_num",nativeQuery = true)
    public List<Menu>  selectMenusByUserId(Long userId);

    /**
     * 根据用户ID查询权限
     * 
     * @param userId 用户ID
     * @return 权限列表
     */
    @Query(value = "select distinct m.perms " +
            "from sys_menu m " +
            "left join sys_role_menu rm on m.menu_id = rm.menu_id " +
            "left join sys_user_role ur on rm.role_id = ur.role_id " +
            "where ur.user_id = ?1",nativeQuery = true)
    public List<String> selectPermsByUserId(Long userId);

    /**
     * 根据角色ID查询菜单
     * 
     * @param roleId 角色ID
     * @return 菜单列表
     */
    @Query(value = "select concat(m.menu_id, m.perms) as perms " +
            "from sys_menu m " +
            "left join sys_role_menu rm on m.menu_id = rm.menu_id " +
            "where rm.role_id = ?1 " +
            "order by m.parent_id, m.order_num",nativeQuery = true)
    public List<String> selectMenuTree(Long roleId);


    /**
     * 删除菜单管理信息
     * 
     * @param menuId 菜单ID
     * @return 结果
     */
    @Modifying
    @Query(value = "delete from sys_menu where menu_id = ?1 or parent_id = ?1 ",nativeQuery = true)
    public void deleteMenuById(Long menuId);

    /**
     * 根据菜单ID查询信息
     * 
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    @Query(value="SELECT t.menu_id,t.menu_name,t.parent_id,t.order_num,t.url,t.menu_type,t.visible,t.perms,t.icon,t.create_by,t.create_time,t.update_time,t.update_by,t.remark," +
            "(SELECT menu_name FROM sys_menu WHERE menu_id = t.parent_id) parent_name " +
            "FROM sys_menu t where t.menu_id = ?1",nativeQuery = true)
    public Menu selectMenuById(Long menuId);


}
