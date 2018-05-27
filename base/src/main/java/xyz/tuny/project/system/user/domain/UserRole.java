package xyz.tuny.project.system.user.domain;

import xyz.tuny.framework.web.domain.BaseEntity;
import xyz.tuny.framework.web.domain.BaseEntity;

import javax.persistence.*;

/**
 * 用户和角色关联 sys_user_role
 * 
 * @author tuny
 */
@Entity
@Table(name="sys_user_role")
public class UserRole extends BaseEntity
{
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    /** 用户ID */
    @Column(name = "user_id")
    private Long userId;

    /** 角色ID */
    @Column(name = "role_id")
    private Long roleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                '}';
    }
}
