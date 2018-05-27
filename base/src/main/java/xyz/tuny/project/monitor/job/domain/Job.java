package xyz.tuny.project.monitor.job.domain;

import xyz.tuny.framework.web.domain.BaseEntity;
import xyz.tuny.framework.web.page.PageDomain;
import xyz.tuny.framework.web.domain.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务调度信息 sys_job
 * 
 */
@Entity
@Table(name="sys_job")
public class Job extends BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 任务ID */
    @Id
    @GeneratedValue
    @Column(name="job_id")
    private Long jobId;

    /** 任务名称 */
    @Column(name="job_name")
    private String jobName;

    /** 任务组名 */
    @Column(name="job_group")
    private String jobGroup;

    /** 任务方法 */
    @Column(name="method_name")
    private String methodName;

    /** 方法参数 */
    @Column(name="params")
    private String params;

    /** cron执行表达式 */
    @Column(name="cron_expression")
    private String cronExpression;

    /** 状态（0正常 1暂停） */
    @Column(name="status",insertable = false,updatable = false)
    private int status;

    /** 创建者 */
    @Column(name="create_by")
    private String createBy;

    /** 创建时间 */
    @Column(name="create_time")
    private Date createTime;

    /** 更新时间 */
    @Column(name="update_time")
    private Date updateTime;

    /** 更新者 */
    @Column(name="update_by")
    private String updateBy;

    /** 备注 */
    @Column(name="remark")
    private String remark;

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
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
}
