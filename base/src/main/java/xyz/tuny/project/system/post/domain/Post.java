package xyz.tuny.project.system.post.domain;


import xyz.tuny.framework.web.domain.BaseEntity;
import xyz.tuny.framework.web.page.PageDomain;
import xyz.tuny.framework.web.domain.BaseEntity;

import javax.persistence.*;

/**
 * 岗位对象 sys_post
 * 
 */
@Entity
@Table(name="sys_post")
public class Post extends BaseEntity
{
    /** 岗位ID */
    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long postId;

    /** 岗位编码 */
    @Column(name = "post_code")
    private String postCode;

    /** 岗位名称 */
    @Column(name = "post_name")
    private String postName;

    /** 岗位排序 */
    @Column(name = "post_sort")
    private String postSort;

    /** 状态（0正常 1停用） */
    @Column(name = "status")
    private int status;

    /** 用户是否存在此岗位标识 默认不存在 */
    @Transient
    private boolean flag = false;

    /** 创建者 */
    @Column(name = "create_by")
    private String createBy;

    /** 创建时间 */
    @Column(name = "create_time")
    private String createTime;

    /** 更新时间 */
    @Column(name = "update_time")
    private String updateTime;

    /** 更新者 */
    @Column(name = "update_by")
    private String updateBy;

    /** 备注 */
    @Column(name = "remark")
    private String remark;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getPostSort() {
        return postSort;
    }

    public void setPostSort(String postSort) {
        this.postSort = postSort;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
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

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", postCode='" + postCode + '\'' +
                ", postName='" + postName + '\'' +
                ", postSort='" + postSort + '\'' +
                ", status=" + status +
                ", flag=" + flag +
                ", createBy='" + createBy + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
