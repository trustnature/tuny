package xyz.tuny.project.monitor.operlog.domain;

import xyz.tuny.framework.web.domain.BaseEntity;
import xyz.tuny.framework.web.page.PageDomain;
import xyz.tuny.framework.web.domain.BaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 操作日志记录 oper_log
 * 
 */
@Entity
@Table(name="sys_oper_log")
public class OperLog extends BaseEntity
{
    /** 日志主键 */
    @Id
    @GeneratedValue
    @Column(name = "oper_id")
    private Long operId;

    /** 模块标题 */
    @Column(name = "title")
    private String title;

    /** 功能请求 */
    @Column(name = "action")
    private String action;

    /** 请求方法 */
    @Column(name = "method")
    private String method;

    /** 来源渠道 */
    @Column(name = "channel")
    private String channel;

    /** 操作员名称 */
    @Column(name = "login_name")
    private String loginName;

    /** 部门名称 */
    @Column(name = "dept_name")
    private String deptName;

    /** 请求url */
    @Column(name = "oper_url")
    private String operUrl;

    /** 操作地址 */
    @Column(name = "oper_ip")
    private String operIp;

    /** 请求参数 */
    @Column(name = "oper_param")
    private String operParam;

    /** 状态0正常 1异常 */
    @Column(name = "status")
    private int status;

    /** 错误消息 */
    @Column(name = "error_msg")
    private String errorMsg;

    /** 操作时间 */
    @Column(name = "oper_time")
    private Date operTime;

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getOperUrl() {
        return operUrl;
    }

    public void setOperUrl(String operUrl) {
        this.operUrl = operUrl;
    }

    public String getOperIp() {
        return operIp;
    }

    public void setOperIp(String operIp) {
        this.operIp = operIp;
    }

    public String getOperParam() {
        return operParam;
    }

    public void setOperParam(String operParam) {
        this.operParam = operParam;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Date getOperTime() {
        return operTime;
    }

    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }

    @Override
    public String toString() {
        return "OperLog{" +
                "operId=" + operId +
                ", title='" + title + '\'' +
                ", action='" + action + '\'' +
                ", method='" + method + '\'' +
                ", channel='" + channel + '\'' +
                ", loginName='" + loginName + '\'' +
                ", deptName='" + deptName + '\'' +
                ", operUrl='" + operUrl + '\'' +
                ", operIp='" + operIp + '\'' +
                ", operParam='" + operParam + '\'' +
                ", status=" + status +
                ", errorMsg='" + errorMsg + '\'' +
                ", operTime=" + operTime +
                '}';
    }
}
