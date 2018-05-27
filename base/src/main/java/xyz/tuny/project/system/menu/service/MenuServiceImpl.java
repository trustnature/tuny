package xyz.tuny.project.system.menu.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import xyz.tuny.common.utils.StringUtils;
import xyz.tuny.common.utils.TreeUtils;
import xyz.tuny.common.utils.security.ShiroUtils;
import xyz.tuny.project.system.menu.dao.IMenuDao;
import xyz.tuny.project.system.menu.domain.Menu;
import xyz.tuny.project.system.role.domain.Role;
import org.springframework.transaction.annotation.Transactional;
import xyz.tuny.project.system.menu.dao.IMenuDao;
import xyz.tuny.project.system.menu.domain.Menu;
import xyz.tuny.project.system.role.domain.Role;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 菜单 业务层处理
 * 
 * @author tuny
 */
@Service("menuService")
public class MenuServiceImpl implements IMenuService
{
    public static final String PREMISSION_STRING = "perms[\"{0}\"]";

    @Autowired
    private IMenuDao menuDao;

    /**
     * 根据用户ID查询菜单
     * 
     * @param userId 用户ID
     * @return 菜单列表
     */
    @Override
    public List<Menu> selectMenusByUserId(Long userId)
    {
        List<Menu> menus = menuDao.selectMenusByUserId(userId);
        return TreeUtils.getChildPerms(menus, 0);
    }

    /**
     * 查询菜单集合
     * 
     * @return 所有菜单信息
     */
    @Override
    public List<Menu> selectMenuAll()
    {
        return menuDao.findAll();
    }

    /**
     * 根据用户ID查询权限
     * 
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectPermsByUserId(Long userId)
    {
        List<String> perms = menuDao.selectPermsByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms)
        {
            if (StringUtils.isNotEmpty(perm))
            {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * 根据角色ID查询菜单
     * 
     * @param role 角色对象
     * @return 菜单列表
     */
    @Override
    public List<Map<String, Object>> selectMenuTree(Role role)
    {
        Long roleId = role.getRoleId();
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        List<Menu> menuList = menuDao.findAll();
        if (StringUtils.isNotNull(roleId))
        {
            List<String> roleMenuList = menuDao.selectMenuTree(roleId);
            trees = getTrees(menuList, true, roleMenuList);
        }
        else
        {
            trees = getTrees(menuList, false, null);
        }
        return trees;
    }

    /**
     * 查询系统所有权限
     * 
     * @return 权限列表
     */
    @Override
    public LinkedHashMap<String, String> selectPermsAll()
    {
        LinkedHashMap<String, String> section = new LinkedHashMap<>();
        List<Menu> permissions = menuDao.findAll();
        if (StringUtils.isNotEmpty(permissions))
        {
            for (Menu menu : permissions)
            {
                section.put(menu.getUrl(), MessageFormat.format(PREMISSION_STRING, menu.getPerms()));
            }
        }
        return section;
    }

    /**
     * 获取菜单树
     */
    public List<Map<String, Object>> getTrees(List<Menu> menuList, boolean isCheck, List<String> roleMenuList)
    {
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        for (Menu menu : menuList)
        {
            Map<String, Object> deptMap = new HashMap<String, Object>();
            deptMap.put("id", menu.getMenuId());
            deptMap.put("pId", menu.getParentId());
            deptMap.put("name",
                    menu.getMenuName() + "<font color=\"#888\">&nbsp;&nbsp;&nbsp;" + menu.getPerms() + "</font>");
            if (isCheck)
            {
                deptMap.put("checked", roleMenuList.contains(menu.getMenuId() + menu.getPerms()));
            }
            else
            {
                deptMap.put("checked", false);
            }
            trees.add(deptMap);
        }
        return trees;
    }

    /**
     * 删除菜单管理信息
     * 
     * @param menuId 菜单ID
     * @return 结果
     */
    @Override
    @Transactional
    public boolean deleteMenuById(Long menuId)
    {
         menuDao.delete(menuId);
         return true;
    }

    /**
     * 根据菜单ID查询信息
     * 
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    @Override
    public Menu selectMenuById(Long menuId)
    {
        return menuDao.selectMenuById(menuId);
    }

    /**
     * 保存菜单信息
     * 
     * @param menu 菜单信息
     * @return 结果
     */
    @Override
    public boolean saveMenu(Menu menu)
    {
        Long menuId = menu.getMenuId();
        if (StringUtils.isNotNull(menuId))
        {
            menu.setUpdateBy(ShiroUtils.getLoginName());
            menuDao.save(menu);
        }
        else
        {
            menu.setCreateBy(ShiroUtils.getLoginName());
            menuDao.save(menu);
        }
        return true;
    }

}
