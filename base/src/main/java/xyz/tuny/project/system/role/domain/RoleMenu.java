package xyz.tuny.project.system.role.domain;

import xyz.tuny.framework.web.domain.BaseEntity;
import xyz.tuny.framework.web.domain.BaseEntity;

import javax.persistence.*;

/**
 * 角色和菜单关联 sys_role_menu
 * 
 * @author tuny
 */
@Entity
@Table(name="sys_role_menu")
public class RoleMenu extends BaseEntity
{
    /** 角色ID */
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "role_id")
    private Long roleId;

    /** 菜单ID */
    @Column(name = "menu_id")
    private Long menuId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}
