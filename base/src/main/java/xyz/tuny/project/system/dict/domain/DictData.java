package xyz.tuny.project.system.dict.domain;

import xyz.tuny.framework.web.domain.BaseEntity;
import xyz.tuny.framework.web.page.PageDomain;
import xyz.tuny.framework.web.domain.BaseEntity;

import javax.persistence.*;

/**
 * 字典数据表 sys_dict_data
 * 
 */
@Entity
@Table(name="sys_dict_data")
public class DictData extends BaseEntity
{
    /** 字典编码 */
    @Id
    @GeneratedValue
    @Column(name = "dict_code")
    private Long dictCode;

    /** 字典排序 */
    @Column(name = "dict_sort")
    private Long dictSort;

    /** 字典标签 */
    @Column(name = "dict_label")
    private String dictLabel;

    /** 字典键值 */
    @Column(name = "dict_value")
    private String dictValue;

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

    public Long getDictCode() {
        return dictCode;
    }

    public void setDictCode(Long dictCode) {
        this.dictCode = dictCode;
    }

    public Long getDictSort() {
        return dictSort;
    }

    public void setDictSort(Long dictSort) {
        this.dictSort = dictSort;
    }

    public String getDictLabel() {
        return dictLabel;
    }

    public void setDictLabel(String dictLabel) {
        this.dictLabel = dictLabel;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
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
        return "DictData{" +
                "dictCode=" + dictCode +
                ", dictSort=" + dictSort +
                ", dictLabel='" + dictLabel + '\'' +
                ", dictValue='" + dictValue + '\'' +
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
