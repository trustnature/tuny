package xyz.tuny.project.system.role.domain;

import xyz.tuny.framework.web.domain.BaseEntity;
import xyz.tuny.framework.web.page.PageDomain;
import xyz.tuny.framework.web.domain.BaseEntity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;

/**
 * 角色对象 sys_role
 * 
 */
@Entity
@Table(name="sys_role")
public class Role extends BaseEntity
{
    /** 角色ID */
    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private Long roleId;

    /** 角色名 */
    @Column(name = "role_name")
    private String roleName;

    /** 角色权限 */
    @Column(name = "role_key")
    private String roleKey;

    /** 角色排序 */
    @Column(name = "role_sort")
    private String roleSort;

    /** 角色状态:0正常,1禁用 */
    @Column(name = "status")
    private int status;

    /** 创建者 */
    @Column(name = "create_by")
    private String createBy;

    /** 创建时间 */
    @Column(name = "create_time")
    private Date createTime;

    /** 更新时间 */
    @Column(name = "update_time")
    private Date updateTime;

    /** 更新者 */
    @Column(name = "update_by")
    private String updateBy;

    /** 备注 */
    @Column(name = "remark")
    private String remark;

    /** 用户是否存在此角色标识 默认不存在 */
    @Transient
    private boolean flag = false;

    /** 菜单组 */
    private Long[] menuIds;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public String getRoleSort() {
        return roleSort;
    }

    public void setRoleSort(String roleSort) {
        this.roleSort = roleSort;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Long[] getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(Long[] menuIds) {
        this.menuIds = menuIds;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleKey='" + roleKey + '\'' +
                ", roleSort='" + roleSort + '\'' +
                ", status=" + status +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", updateBy='" + updateBy + '\'' +
                ", remark='" + remark + '\'' +
                ", flag=" + flag +
                ", menuIds=" + Arrays.toString(menuIds) +
                '}';
    }
}
