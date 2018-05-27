package xyz.tuny.project.system.role.service;

import java.util.*;

import xyz.tuny.framework.web.page.TableDataInfo;
import xyz.tuny.project.system.post.domain.Post;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import xyz.tuny.common.utils.security.ShiroUtils;
import xyz.tuny.project.system.role.dao.RoleDao;
import xyz.tuny.project.system.role.dao.RoleMenuDao;
import xyz.tuny.project.system.role.domain.Role;
import xyz.tuny.project.system.role.domain.RoleMenu;
import xyz.tuny.project.system.user.dao.IUserRoleDao;
import org.springframework.transaction.annotation.Transactional;
import xyz.tuny.framework.web.page.TableDataInfo;
import xyz.tuny.project.system.user.dao.IUserRoleDao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 角色 业务层处理
 * 
 * @author tuny
 */
@Service("roleService")
public class RoleServiceImpl implements IRoleService
{

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RoleMenuDao roleMenuDao;

    @Autowired
    private IUserRoleDao userRoleDao;

    /**
     * 根据条件分页查询角色数据
     * 
     * @param role 角色信息
     * @return 角色数据集合信息
     */
    @Override
    public TableDataInfo selectRoleList(PageRequest pageRequest, Role role)
    {
        String keyword = role.getSearchValue();
        Specification<Role> spec = new Specification<Role>() {
            @Override
            public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
                List<Predicate> predicates =new ArrayList();
                if (StringUtils.isNotEmpty(keyword)) {
                    Predicate predicateT = builder.like(root.<String> get("roleName"), "%/" + keyword + "%", '/');
                    Predicate predicateP = builder.like(root.<String> get("roleKey"), "%/" + keyword + "%", '/');
                    predicates.add(builder.or(predicateT, predicateP));
                }
                if (predicates.size() > 0) {
                    return builder.and(predicates.toArray(new Predicate[predicates.size()]));
                }
                return builder.conjunction();
            }
        };
        Page<Role> pageRole = roleDao.findAll(spec,pageRequest);
        TableDataInfo rspData = new TableDataInfo();
        rspData.setRows(pageRole.getContent());
        rspData.setTotal(pageRole.getTotalElements());
        return rspData;
    }

    /**
     * 根据用户ID查询权限
     * 
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectRoleKeys(Long userId)
    {

        List<Role> perms = roleDao.selectRolesByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (Role perm : perms)
        {
            if (null != perms)
            {
                permsSet.addAll(Arrays.asList(perm.getRoleKey().toString().trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * 根据用户ID查询角色
     * 
     * @param userId 用户ID
     * @return 角色列表
     */
    @Override
    public List<Role> selectRolesByUserId(Long userId)
    {
        List<Role> userRoles = roleDao.selectRolesByUserId(userId);
        List<Role> roles = roleDao.findAll();
        for (Role role : roles)
        {
            for (Role userRole : userRoles)
            {
                if (role.getRoleId() == userRole.getRoleId())
                {
                    role.setFlag(true);
                    break;
                }
            }
        }
        return roles;
    }

    /**
     * 查询所有角色
     * 
     * @return 角色列表
     */
    @Override
    public List<Role> selectRoleAll()
    {
        return roleDao.findAll();
    }

    /**
     * 通过角色ID查询角色
     * 
     * @param roleId 角色ID
     * @return 角色对象信息
     */
    @Override
    public Role selectRoleById(Long roleId)
    {
        return roleDao.findByRoleId(roleId);
    }

    /**
     * 通过角色ID删除角色
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    @Override
    @Transactional
    public boolean deleteRoleById(Long roleId)
    {
        userRoleDao.delUserRoleByUserId(roleId);
        roleMenuDao.delete(roleId);
        roleDao.delete(roleId);
        return true;
    }

    /**
     * 批量删除角色用户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public boolean batchDeleteRole(Long[] ids)
    {
        List<Role> roles = new ArrayList<>();
        for(Long id : ids){
            Role temp = new Role();
            temp.setRoleId(id);
            roles.add(temp);
        }
        roleDao.deleteInBatch(roles);
        return true;
        //String idstr = StringUtils.join(ids,",");
        //return roleDao.batchDeleteRole(idstr);
    }

    /**
     * 保存角色信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public boolean saveRole(Role role)
    {
        Long roleId = role.getRoleId();
        if (null != roleId)
        {
            role.setUpdateBy(ShiroUtils.getLoginName());
            // 修改角色信息
            roleDao.save(role);
            // 删除角色与菜单关联
            roleMenuDao.delete(roleId);

        }
        else
        {
            role.setCreateBy(ShiroUtils.getLoginName());
            // 新增角色信息
            roleDao.save(role);
        }
        return insertRoleMenu(role);
    }

    /**
     * 新增角色菜单信息
     * 
     */
    public boolean insertRoleMenu(Role role)
    {
        // 新增用户与角色管理
        List<RoleMenu> list = new ArrayList<RoleMenu>();
        for (Long menuId : role.getMenuIds())
        {
            RoleMenu rm = new RoleMenu();
            rm.setRoleId(role.getRoleId());
            rm.setMenuId(menuId);
            list.add(rm);
        }
        if (list.size() > 0)
        {
            roleMenuDao.save(list);
        }
        return true;
    }

}
