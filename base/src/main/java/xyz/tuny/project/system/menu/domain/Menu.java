package xyz.tuny.project.system.menu.domain;

import xyz.tuny.framework.web.domain.BaseEntity;
import xyz.tuny.framework.web.domain.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色对象 sys_menu
 * 
 */
@Entity
@Table(name="sys_menu")
public class Menu extends BaseEntity
{
    /** 菜单ID */
    @Id
    @GeneratedValue
    @Column(name = "menu_id")
    private Long menuId;

    /** 菜单名称 */
    @Column(name = "menu_name")
    private String menuName;

    /** 父菜单名称 */
    @Column(name = "parent_name")
    private String parentName;

    /** 父菜单ID */
    @Column(name = "parent_id")
    private Long parentId;

    /** 显示顺序 */
    @Column(name = "order_num")
    private String orderNum;

    /** 菜单URL */
    @Column(name = "url")
    private String url;

    /** 类型:0目录,1菜单,2按钮 */
    @Column(name = "menu_type")
    private String menuType;

    /** 菜单状态:0显示,1隐藏 */
    @Column(name = "visible")
    private int visible;

    /** 权限字符串 */
    @Column(name = "perms")
    private String perms;

    /** 菜单图标 */
    @Column(name = "icon")
    private String icon;

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

    /** 子菜单 */
    @Transient
    private List<Menu> children = new ArrayList<Menu>();

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    @Override
    public String toString()
    {
        return "Menu [menuId=" + menuId + ", menuName=" + menuName + ", parentName=" + parentName + ", parentId="
                + parentId + ", orderNum=" + orderNum + ", url=" + url + ", menuType=" + menuType + ", visible="
                + visible + ", perms=" + perms + ", icon=" + icon + ", createBy=" + createBy + ", createTime="
                + createTime + ", updateTime=" + updateTime + ", updateBy=" + updateBy + ", remark=" + remark
                + ", children=" + children + "]";
    }
}
