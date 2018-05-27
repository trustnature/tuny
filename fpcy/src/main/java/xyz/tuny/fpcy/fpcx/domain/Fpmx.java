package xyz.tuny.fpcy.fpcx.domain;

import xyz.tuny.framework.web.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_fpmx")
public class Fpmx extends BaseEntity {
    //通用信息
    @Id
    private String id;
    private String cheadguid;
    private Integer xh;//序号
    private String spmc;//名称
    private String jldw;//单位
    private String ggxh;//规格
    private Double sl;//数量
    private Double se;//税额
    private String slv;//税率
    private Double dj;//单价
    private Double je;//不含税价

    //通行费明细
    private String txfslv;
    private String txfse;
    private String xmmc;
    private String cph;
    private String lx;
    private String txrqq;
    private String txrqz;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCheadguid() {
        return cheadguid;
    }

    public void setCheadguid(String cheadguid) {
        this.cheadguid = cheadguid;
    }

    public Integer getXh() {
        return xh;
    }

    public void setXh(Integer xh) {
        this.xh = xh;
    }

    public String getSpmc() {
        return spmc;
    }

    public void setSpmc(String spmc) {
        this.spmc = spmc;
    }

    public String getJldw() {
        return jldw;
    }

    public void setJldw(String jldw) {
        this.jldw = jldw;
    }

    public String getGgxh() {
        return ggxh;
    }

    public void setGgxh(String ggxh) {
        this.ggxh = ggxh;
    }

    public Double getSl() {
        return sl;
    }

    public void setSl(Double sl) {
        this.sl = sl;
    }

    public Double getSe() {
        return se;
    }

    public void setSe(Double se) {
        this.se = se;
    }

    public String getSlv() {
        return slv;
    }

    public void setSlv(String slv) {
        this.slv = slv;
    }

    public Double getDj() {
        return dj;
    }

    public void setDj(Double dj) {
        this.dj = dj;
    }

    public Double getJe() {
        return je;
    }

    public void setJe(Double je) {
        this.je = je;
    }

    public String getTxfslv() {
        return txfslv;
    }

    public void setTxfslv(String txfslv) {
        this.txfslv = txfslv;
    }

    public String getTxfse() {
        return txfse;
    }

    public void setTxfse(String txfse) {
        this.txfse = txfse;
    }

    public String getXmmc() {
        return xmmc;
    }

    public void setXmmc(String xmmc) {
        this.xmmc = xmmc;
    }

    public String getCph() {
        return cph;
    }

    public void setCph(String cph) {
        this.cph = cph;
    }

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    public String getTxrqq() {
        return txrqq;
    }

    public void setTxrqq(String txrqq) {
        this.txrqq = txrqq;
    }

    public String getTxrqz() {
        return txrqz;
    }

    public void setTxrqz(String txrqz) {
        this.txrqz = txrqz;
    }
}
