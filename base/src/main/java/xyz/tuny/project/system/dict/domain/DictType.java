package xyz.tuny.project.system.dict.domain;

import xyz.tuny.framework.web.domain.BaseEntity;
import xyz.tuny.framework.web.domain.BaseEntity;

import javax.persistence.*;

/**
 * 字典类型对象 sys_dict_type
 * 
 */
@Entity
@Table(name="sys_dict_type")
public class DictType extends BaseEntity
{
    /** 字典主键 */
    @Id
    @GeneratedValue
    @Column(name = "dict_id")
    private Long dictId;

    /** 字典名称 */
    @Column(name = "dict_name")
    private String dictName;

    /** 字典类型 */
    @Column(name = "dict_type")
    private String dictType;

    /** 状态（0正常 1禁用） */
    @Column(name = "status",insertable = false,updatable = false)
    private int status;

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

    /** 备注 */
    @Column(name = "remark")
    private String remark;

    public Long getDictId() {
        return dictId;
    }

    public void setDictId(Long dictId) {
        this.dictId = dictId;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "DictType{" +
                "dictId=" + dictId +
                ", dictName='" + dictName + '\'' +
                ", dictType='" + dictType + '\'' +
                ", status=" + status +
                ", createBy='" + createBy + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
