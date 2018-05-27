package xyz.tuny.project.monitor.job.domain;

import xyz.tuny.framework.web.domain.BaseEntity;
import xyz.tuny.framework.web.page.PageDomain;
import xyz.tuny.framework.web.domain.BaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 定时任务调度日志信息 sys_job_log
 * 
 */
@Entity
@Table(name="sys_job_log")
public class JobLog  extends BaseEntity
{
    /** ID */
    @Id
    @GeneratedValue
    @Column(name = "job_log_id")
    private Long jobLogId;

    /** 任务名称 */
    @Column(name = "job_name")
    private String jobName;

    /** 任务组名 */
    @Column(name = "job_group")
    private String jobGroup;

    /** 任务方法 */
    @Column(name = "method_name")
    private String methodName;

    /** 方法参数 */
    @Column(name = "params")
    private String params;

    /** 日志信息 */
    @Column(name = "job_message")
    private String jobMessage;

    /** 是否异常 */
    @Column(name = "is_exception")
    private int isException;

    /** 异常信息 */
    @Column(name = "exception_info")
    private String exceptionInfo;

    /** 创建时间 */
    @Column(name = "create_time")
    private Date createTime;

    public Long getJobLogId() {
        return jobLogId;
    }

    public void setJobLogId(Long jobLogId) {
        this.jobLogId = jobLogId;
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

    public String getJobMessage() {
        return jobMessage;
    }

    public void setJobMessage(String jobMessage) {
        this.jobMessage = jobMessage;
    }

    public int getIsException() {
        return isException;
    }

    public void setIsException(int isException) {
        this.isException = isException;
    }

    public String getExceptionInfo() {
        return exceptionInfo;
    }

    public void setExceptionInfo(String exceptionInfo) {
        this.exceptionInfo = exceptionInfo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
