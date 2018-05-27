package xyz.tuny.project.system.dept.domain;


import xyz.tuny.framework.web.domain.BaseEntity;
import xyz.tuny.framework.web.domain.BaseEntity;

import javax.persistence.*;

/**
 * 部门对象 sys_dept
 * 
 */
@Entity
@Table(name="sys_dept")
public class Dept extends BaseEntity
{
    /** 部门ID */
    @Id
    @GeneratedValue
    @Column(name = "dept_id")
    private Long deptId;

    /** 父部门ID */
    @Column(name = "parent_id")
    private Long parentId;

    /** 部门名称 */
    @Column(name = "dept_name")
    private String deptName;

    /** 显示顺序 */
    @Column(name = "order_num")
    private String orderNum;

    /** 负责人 */
    @Column(name = "leader")
    private String leader;

    /** 联系电话 */
    @Column(name = "phone")
    private String phone;

    /** 邮箱 */
    @Column(name = "email")
    private String email;

    /** 部门状态:0正常,1停用 */
    @Column(name = "status")
    private int status;

    /** 父部门名称 */
    @Column(name = "parent_name")
    private String parentName;

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

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
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

    @Override
    public String toString() {
        return "Dept{" +
                "deptId=" + deptId +
                ", parentId=" + parentId +
                ", deptName='" + deptName + '\'' +
                ", orderNum='" + orderNum + '\'' +
                ", leader='" + leader + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", parentName='" + parentName + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
