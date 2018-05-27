package xyz.tuny.project.system.user.domain;

import xyz.tuny.framework.web.domain.BaseEntity;
import xyz.tuny.framework.web.page.PageDomain;
import xyz.tuny.project.system.dept.domain.Dept;
import xyz.tuny.framework.web.domain.BaseEntity;

import javax.persistence.*;
import java.util.Arrays;

/**
 * 用户对象 sys_user
 * 
 */
@Entity
@Table(name="sys_user")
public class User extends BaseEntity
{
    /** 用户ID */
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long userId;

    /** 部门ID */
    @Column(name = "dept_id")
    private Long deptId;

    /** 部门父ID */
    @Column(name = "parent_id")
    private Long parentId;

    /** 登录名 */
    @Column(name = "login_name")
    private String loginName;

    /** 用户名称 */
    @Column(name = "user_name")
    private String userName;

    /** 用户邮箱 */
    @Column(name = "email_name")
    private String email;

    /** 手机号码 */
    @Column(name = "phone_number")
    private String phonenumber;

    /** 密码 */
    @Column(name = "password")
    private String password;

    /** 盐加密 */
    @Column(name = "salt")
    private String salt;

    /** 类型:Y默认用户,N非默认用户 */
    @Column(name = "user_type" ,insertable = false,updatable = false,columnDefinition="varchar(1) default 'N'")
    private String userType;

    /** 帐号状态:0正常,1禁用 */
    @Column(name = "status")
    private int status;

    /** 拒绝登录描述 */
    @Column(name = "refuse_des")
    private String refuseDes;

    /** 创建者 */
    @Column(name = "create_by")
    private String createBy;

    /** 创建时间 */
    @Column(name = "create_time")
    private String createTime;

    /** 更新者 */
    @Column(name = "update_by")
    private String updateBy;

    /** 更新时间 */
    @Column(name = "update_time")
    private String updateTime;

    /** 部门对象 */
    @Transient
    private Dept dept;

    /** 角色组 */
    @Transient
    private Long[] roleIds;

    /** 岗位组 */
    @Transient
    private Long[] postIds;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRefuseDes() {
        return refuseDes;
    }

    public void setRefuseDes(String refuseDes) {
        this.refuseDes = refuseDes;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public Long[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Long[] roleIds) {
        this.roleIds = roleIds;
    }

    public Long[] getPostIds() {
        return postIds;
    }

    public void setPostIds(Long[] postIds) {
        this.postIds = postIds;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", deptId=" + deptId +
                ", parentId=" + parentId +
                ", loginName='" + loginName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", userType='" + userType + '\'' +
                ", status=" + status +
                ", refuseDes='" + refuseDes + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", dept=" + dept +
                ", roleIds=" + Arrays.toString(roleIds) +
                ", postIds=" + Arrays.toString(postIds) +
                '}';
    }
}
