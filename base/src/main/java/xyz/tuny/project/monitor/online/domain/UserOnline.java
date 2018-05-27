package xyz.tuny.project.monitor.online.domain;

import java.util.Date;

import xyz.tuny.framework.web.domain.BaseEntity;
import xyz.tuny.framework.web.page.PageDomain;
import xyz.tuny.project.monitor.online.domain.OnlineSession.OnlineStatus;
import xyz.tuny.framework.web.domain.BaseEntity;

import javax.persistence.*;

/**
 * 当前在线会话 sys_user_online
 * 
 * @author tuny
 */
@Entity
@Table(name = "sys_user_online")
public class UserOnline extends BaseEntity
{
    /** 用户会话id */
    @Id
    @Column(name = "sessionid")
    private String sessionId;

    /** 部门名称 */
    @Column(name = "dept_name")
    private String deptName;

    /** 登录名称 */
    @Column(name = "login_name")
    private String loginName;

    /** 登录IP地址 */
    @Column(name = "ipaddr")
    private String ipaddr;

    /** 浏览器类型 */
    @Column(name = "browser")
    private String browser;

    /** 操作系统 */
    @Column(name = "os")
    private String os;

    /** session创建时间 */
    @Column(name = "start_timestsamp")
    private Date startTimestamp;

    /** session最后访问时间 */
    @Column(name = "last_access_time")
    private Date lastAccessTime;

    /** 超时时间，单位为分钟 */
    @Column(name = "expire_time",insertable = false,updatable = false)
    private Long expireTime;

    /** 在线状态 */
    @Transient
    private OnlineSession.OnlineStatus status = OnlineSession.OnlineStatus.on_line;

    /** 备份的当前用户会话 */
    @Transient
    private OnlineSession session;

    /**
     * 设置session对象
     */
    public static final UserOnline fromOnlineSession(OnlineSession session)
    {
        UserOnline online = new UserOnline();
        online.setSessionId(String.valueOf(session.getId()));
        online.setDeptName(session.getDeptName());
        online.setLoginName(session.getLoginName());
        online.setStartTimestamp(session.getStartTimestamp());
        online.setLastAccessTime(session.getLastAccessTime());
        online.setExpireTime(session.getTimeout());
        online.setIpaddr(session.getHost());
        online.setBrowser(session.getBrowser());
        online.setOs(session.getOs());
        online.setStatus(session.getStatus());
        online.setSession(session);
        return online;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getIpaddr() {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public Date getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(Date startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public Date getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(Date lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public OnlineSession.OnlineStatus getStatus() {
        return status;
    }

    public void setStatus(OnlineSession.OnlineStatus status) {
        this.status = status;
    }

    public OnlineSession getSession() {
        return session;
    }

    public void setSession(OnlineSession session) {
        this.session = session;
    }

    @Override
    public String toString() {
        return "UserOnline{" +
                "sessionId='" + sessionId + '\'' +
                ", deptName='" + deptName + '\'' +
                ", loginName='" + loginName + '\'' +
                ", ipaddr='" + ipaddr + '\'' +
                ", browser='" + browser + '\'' +
                ", os='" + os + '\'' +
                ", startTimestamp=" + startTimestamp +
                ", lastAccessTime=" + lastAccessTime +
                ", expireTime=" + expireTime +
                ", status=" + status +
                ", session=" + session +
                '}';
    }
}
