package xyz.tuny.project.system.role.service;

import java.util.List;
import java.util.Set;

import xyz.tuny.framework.web.dao.BaseDao;
import xyz.tuny.framework.web.page.TableDataInfo;
import xyz.tuny.project.system.role.domain.Role;
import xyz.tuny.project.system.user.domain.User;
import org.springframework.data.domain.PageRequest;
import xyz.tuny.framework.web.page.TableDataInfo;

/**
 * 角色业务层
 * 
 * @author tuny
 */
public interface IRoleService
{

    /**
     * 根据条件分页查询角色数据
     * 
     * @param role 角色信息
     * @return 角色数据集合信息
     */
    public TableDataInfo selectRoleList(PageRequest pageRequest, Role role);

    /**
     * 根据用户ID查询角色
     * 
     * @param userId 用户ID
     * @return 权限列表
     */
    public Set<String> selectRoleKeys(Long userId);

    /**
     * 根据用户ID查询角色
     * 
     * @param userId 用户ID
     * @return 角色列表
     */
    public List<Role> selectRolesByUserId(Long userId);

    /**
     * 查询所有角色
     * 
     * @return 角色列表
     */
    public List<Role> selectRoleAll();

    /**
     * 通过角色ID查询角色
     * 
     * @param roleId 角色ID
     * @return 角色对象信息
     */
    public Role selectRoleById(Long roleId);

    /**
     * 通过角色ID删除角色
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    public boolean deleteRoleById(Long roleId);

    /**
     * 批量删除角色用户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public boolean batchDeleteRole(Long[] ids);

    /**
     * 保存角色信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    public boolean saveRole(Role role);

}
