package xyz.tuny.project.system.menu.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import xyz.tuny.project.system.menu.domain.Menu;
import xyz.tuny.project.system.role.domain.Role;
import xyz.tuny.project.system.menu.domain.Menu;
import xyz.tuny.project.system.role.domain.Role;

/**
 * 菜单 业务层
 * 
 * @author tuny
 */
public interface IMenuService
{

    /**
     * 根据用户ID查询菜单
     * 
     * @param userId 用户ID
     * @return 菜单列表
     */
    public List<Menu> selectMenusByUserId(Long userId);

    /**
     * 查询菜单集合
     * 
     * @return 所有菜单信息
     */
    public List<Menu> selectMenuAll();

    /**
     * 根据用户ID查询权限
     * 
     * @param userId 用户ID
     * @return 权限列表
     */
    public Set<String> selectPermsByUserId(Long userId);

    /**
     * 根据角色ID查询菜单
     * 
     * @param role 角色对象
     * @return 菜单列表
     */
    public List<Map<String, Object>> selectMenuTree(Role role);

    /**
     * 查询系统所有权限
     * 
     * @return 权限列表
     */
    public Map<String, String> selectPermsAll();

    /**
     * 删除菜单管理信息
     * 
     * @param menuId 菜单ID
     * @return 结果
     */
    public boolean deleteMenuById(Long menuId);

    /**
     * 根据菜单ID查询信息
     * 
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    public Menu selectMenuById(Long menuId);

    /**
     * 保存菜单信息
     * 
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean saveMenu(Menu menu);

    /**
     * 校验菜单名称是否唯一
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public String checkMenuNameUnique(Menu menu);

}
