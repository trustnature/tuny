package xyz.tuny.fpcy.fpcx.domain;

import xyz.tuny.framework.web.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 操作日志记录 oper_log
 * 
 */
@Entity
@Table(name="t_fpzb")
public class Fpzb extends BaseEntity
{
    //发票通用信息
    @Id
    private String id;
    private String fpdm;
    private String fphm;
    private String kprq;
    private String gfsh;
    private String xfsh;
    private Double je;
    private Double se;
    private Double jshj;
    private String fplx;
    private String gfmc;
    private String xfmc;
    private String zfbz;
    private String lrrymc;
    private String lrrydm;
    private String bz;
    private String gfdzdh;
    private String gfyhzh;
    private String xfdzdh;
    private String xfyhzh;
    private String jym;
    private String jqbm;
    private String jecn;//金额大写
    private String slv;
    private String userid;//微信用户名
    private String ewm;//二维码
    private String czsj;//操作时间
    private String status;//发票状态
    private String shy;//收货员 卷票
    private String cycs;//查验次数
    private Long imgCount;//图片张数

    //货运发票  没明细
    private String cyr; //承运人
    private String cyrsh;//承运人纳税识别号
    private String spf;//实际受票方
    private String spfsh;//实际受票方纳税人识别号
    private String shr;//收货人
    private String shrsh;//收货人纳税识别号
    private String fhr;//发货人
    private String fhrsh;//发货人纳税识别号
    private String yshwxx;//运输货物信息
    private String qyd;//起运地
    private String czch;//车种车号
    private String dw;//车船吨位

    //机动车发票
    private String cllx;//车辆类型
    private String cpxh;//车牌型号
    private String cd;//产地
    private String hgzh;//合格证号
    private String jkzmsh;//进口证明书号
    private String sjdh;//商检单号
    private String fdjhm;//发动机号码
    private String clsbdh;//车辆识别代号
    private String dz;//地址
    private String dh;//电话
    private String khyh;//开户银行
    private String zh;//账号
    private String xcrs;//限乘人数
    private String wspzhm;//完税凭证号码
    private String zgswjg;//主管税务机关名称;
    private String zgswjgdm;//主管税务机关代码
    private String sfz;//身份证;

    //通行费
    private String txfse;  //通信费税额  有可能为 *** 特殊字符 单独处理

    //二手车
    private String gfdw;//买方单位、个人
    private String gfdwdm;//买方单位代码 身份证号码
    private String gfdwdz;//买方地址
    private String gfdwdh;//买方电话
    private String mfdw;//卖方单位、个人
    private String mfdwdm;//卖方单位代码 身份证号码
    private String mfdwdz;//卖方地址
    private String mfdwdh;//卖方电话
    private String cpzh;//车牌照号
    private String djzh;//登记证号
    private String cjh;//车架号车辆识别代码
    private String zrdclgls;//转入地车辆管理所
    private String cjhj;//车价合计
    private String jydw;//经营 单位
    private String jydwdz;//经营单位地址
    private String jydwnsrsbh;//经营纳税人识别号
    private String jydwkhyhzh;//经营开户银行账号
    private String jydwdh;//经营电话
    private String escsc;//二手车市场
    private String escnsrsbh;//二手车纳税人识别号
    private String escdz;//二手车地址
    private String esckhyhzh;//二手车 开户银行账号
    private String escdh;//二手车电话

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFpdm() {
        return fpdm;
    }

    public void setFpdm(String fpdm) {
        this.fpdm = fpdm;
    }

    public String getFphm() {
        return fphm;
    }

    public void setFphm(String fphm) {
        this.fphm = fphm;
    }

    public String getKprq() {
        return kprq;
    }

    public void setKprq(String kprq) {
        this.kprq = kprq;
    }

    public String getGfsh() {
        return gfsh;
    }

    public void setGfsh(String gfsh) {
        this.gfsh = gfsh;
    }

    public String getXfsh() {
        return xfsh;
    }

    public void setXfsh(String xfsh) {
        this.xfsh = xfsh;
    }

    public Double getJe() {
        return je;
    }

    public void setJe(Double je) {
        this.je = je;
    }

    public Double getSe() {
        return se;
    }

    public void setSe(Double se) {
        this.se = se;
    }

    public Double getJshj() {
        return jshj;
    }

    public void setJshj(Double jshj) {
        this.jshj = jshj;
    }

    public String getFplx() {
        return fplx;
    }

    public void setFplx(String fplx) {
        this.fplx = fplx;
    }

    public String getGfmc() {
        return gfmc;
    }

    public void setGfmc(String gfmc) {
        this.gfmc = gfmc;
    }

    public String getXfmc() {
        return xfmc;
    }

    public void setXfmc(String xfmc) {
        this.xfmc = xfmc;
    }

    public String getZfbz() {
        return zfbz;
    }

    public void setZfbz(String zfbz) {
        this.zfbz = zfbz;
    }

    public String getLrrymc() {
        return lrrymc;
    }

    public void setLrrymc(String lrrymc) {
        this.lrrymc = lrrymc;
    }

    public String getLrrydm() {
        return lrrydm;
    }

    public void setLrrydm(String lrrydm) {
        this.lrrydm = lrrydm;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getGfdzdh() {
        return gfdzdh;
    }

    public void setGfdzdh(String gfdzdh) {
        this.gfdzdh = gfdzdh;
    }

    public String getGfyhzh() {
        return gfyhzh;
    }

    public void setGfyhzh(String gfyhzh) {
        this.gfyhzh = gfyhzh;
    }

    public String getXfdzdh() {
        return xfdzdh;
    }

    public void setXfdzdh(String xfdzdh) {
        this.xfdzdh = xfdzdh;
    }

    public String getXfyhzh() {
        return xfyhzh;
    }

    public void setXfyhzh(String xfyhzh) {
        this.xfyhzh = xfyhzh;
    }

    public String getJym() {
        return jym;
    }

    public void setJym(String jym) {
        this.jym = jym;
    }

    public String getJqbm() {
        return jqbm;
    }

    public void setJqbm(String jqbm) {
        this.jqbm = jqbm;
    }

    public String getJecn() {
        return jecn;
    }

    public void setJecn(String jecn) {
        this.jecn = jecn;
    }

    public String getSlv() {
        return slv;
    }

    public void setSlv(String slv) {
        this.slv = slv;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getEwm() {
        return ewm;
    }

    public void setEwm(String ewm) {
        this.ewm = ewm;
    }

    public String getCzsj() {
        return czsj;
    }

    public void setCzsj(String czsj) {
        this.czsj = czsj;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShy() {
        return shy;
    }

    public void setShy(String shy) {
        this.shy = shy;
    }

    public String getCycs() {
        return cycs;
    }

    public void setCycs(String cycs) {
        this.cycs = cycs;
    }

    public Long getImgCount() {
        return imgCount;
    }

    public void setImgCount(Long imgCount) {
        this.imgCount = imgCount;
    }

    public String getCyr() {
        return cyr;
    }

    public void setCyr(String cyr) {
        this.cyr = cyr;
    }

    public String getCyrsh() {
        return cyrsh;
    }

    public void setCyrsh(String cyrsh) {
        this.cyrsh = cyrsh;
    }

    public String getSpf() {
        return spf;
    }

    public void setSpf(String spf) {
        this.spf = spf;
    }

    public String getSpfsh() {
        return spfsh;
    }

    public void setSpfsh(String spfsh) {
        this.spfsh = spfsh;
    }

    public String getShr() {
        return shr;
    }

    public void setShr(String shr) {
        this.shr = shr;
    }

    public String getShrsh() {
        return shrsh;
    }

    public void setShrsh(String shrsh) {
        this.shrsh = shrsh;
    }

    public String getFhr() {
        return fhr;
    }

    public void setFhr(String fhr) {
        this.fhr = fhr;
    }

    public String getFhrsh() {
        return fhrsh;
    }

    public void setFhrsh(String fhrsh) {
        this.fhrsh = fhrsh;
    }

    public String getYshwxx() {
        return yshwxx;
    }

    public void setYshwxx(String yshwxx) {
        this.yshwxx = yshwxx;
    }

    public String getQyd() {
        return qyd;
    }

    public void setQyd(String qyd) {
        this.qyd = qyd;
    }

    public String getCzch() {
        return czch;
    }

    public void setCzch(String czch) {
        this.czch = czch;
    }

    public String getDw() {
        return dw;
    }

    public void setDw(String dw) {
        this.dw = dw;
    }

    public String getCllx() {
        return cllx;
    }

    public void setCllx(String cllx) {
        this.cllx = cllx;
    }

    public String getCpxh() {
        return cpxh;
    }

    public void setCpxh(String cpxh) {
        this.cpxh = cpxh;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getHgzh() {
        return hgzh;
    }

    public void setHgzh(String hgzh) {
        this.hgzh = hgzh;
    }

    public String getJkzmsh() {
        return jkzmsh;
    }

    public void setJkzmsh(String jkzmsh) {
        this.jkzmsh = jkzmsh;
    }

    public String getSjdh() {
        return sjdh;
    }

    public void setSjdh(String sjdh) {
        this.sjdh = sjdh;
    }

    public String getFdjhm() {
        return fdjhm;
    }

    public void setFdjhm(String fdjhm) {
        this.fdjhm = fdjhm;
    }

    public String getClsbdh() {
        return clsbdh;
    }

    public void setClsbdh(String clsbdh) {
        this.clsbdh = clsbdh;
    }

    public String getDz() {
        return dz;
    }

    public void setDz(String dz) {
        this.dz = dz;
    }

    public String getDh() {
        return dh;
    }

    public void setDh(String dh) {
        this.dh = dh;
    }

    public String getKhyh() {
        return khyh;
    }

    public void setKhyh(String khyh) {
        this.khyh = khyh;
    }

    public String getZh() {
        return zh;
    }

    public void setZh(String zh) {
        this.zh = zh;
    }

    public String getXcrs() {
        return xcrs;
    }

    public void setXcrs(String xcrs) {
        this.xcrs = xcrs;
    }

    public String getWspzhm() {
        return wspzhm;
    }

    public void setWspzhm(String wspzhm) {
        this.wspzhm = wspzhm;
    }

    public String getZgswjg() {
        return zgswjg;
    }

    public void setZgswjg(String zgswjg) {
        this.zgswjg = zgswjg;
    }

    public String getZgswjgdm() {
        return zgswjgdm;
    }

    public void setZgswjgdm(String zgswjgdm) {
        this.zgswjgdm = zgswjgdm;
    }

    public String getSfz() {
        return sfz;
    }

    public void setSfz(String sfz) {
        this.sfz = sfz;
    }

    public String getTxfse() {
        return txfse;
    }

    public void setTxfse(String txfse) {
        this.txfse = txfse;
    }

    public String getGfdw() {
        return gfdw;
    }

    public void setGfdw(String gfdw) {
        this.gfdw = gfdw;
    }

    public String getGfdwdm() {
        return gfdwdm;
    }

    public void setGfdwdm(String gfdwdm) {
        this.gfdwdm = gfdwdm;
    }

    public String getGfdwdz() {
        return gfdwdz;
    }

    public void setGfdwdz(String gfdwdz) {
        this.gfdwdz = gfdwdz;
    }

    public String getGfdwdh() {
        return gfdwdh;
    }

    public void setGfdwdh(String gfdwdh) {
        this.gfdwdh = gfdwdh;
    }

    public String getMfdw() {
        return mfdw;
    }

    public void setMfdw(String mfdw) {
        this.mfdw = mfdw;
    }

    public String getMfdwdm() {
        return mfdwdm;
    }

    public void setMfdwdm(String mfdwdm) {
        this.mfdwdm = mfdwdm;
    }

    public String getMfdwdz() {
        return mfdwdz;
    }

    public void setMfdwdz(String mfdwdz) {
        this.mfdwdz = mfdwdz;
    }

    public String getMfdwdh() {
        return mfdwdh;
    }

    public void setMfdwdh(String mfdwdh) {
        this.mfdwdh = mfdwdh;
    }

    public String getCpzh() {
        return cpzh;
    }

    public void setCpzh(String cpzh) {
        this.cpzh = cpzh;
    }

    public String getDjzh() {
        return djzh;
    }

    public void setDjzh(String djzh) {
        this.djzh = djzh;
    }

    public String getCjh() {
        return cjh;
    }

    public void setCjh(String cjh) {
        this.cjh = cjh;
    }

    public String getZrdclgls() {
        return zrdclgls;
    }

    public void setZrdclgls(String zrdclgls) {
        this.zrdclgls = zrdclgls;
    }

    public String getCjhj() {
        return cjhj;
    }

    public void setCjhj(String cjhj) {
        this.cjhj = cjhj;
    }

    public String getJydw() {
        return jydw;
    }

    public void setJydw(String jydw) {
        this.jydw = jydw;
    }

    public String getJydwdz() {
        return jydwdz;
    }

    public void setJydwdz(String jydwdz) {
        this.jydwdz = jydwdz;
    }

    public String getJydwnsrsbh() {
        return jydwnsrsbh;
    }

    public void setJydwnsrsbh(String jydwnsrsbh) {
        this.jydwnsrsbh = jydwnsrsbh;
    }

    public String getJydwkhyhzh() {
        return jydwkhyhzh;
    }

    public void setJydwkhyhzh(String jydwkhyhzh) {
        this.jydwkhyhzh = jydwkhyhzh;
    }

    public String getJydwdh() {
        return jydwdh;
    }

    public void setJydwdh(String jydwdh) {
        this.jydwdh = jydwdh;
    }

    public String getEscsc() {
        return escsc;
    }

    public void setEscsc(String escsc) {
        this.escsc = escsc;
    }

    public String getEscnsrsbh() {
        return escnsrsbh;
    }

    public void setEscnsrsbh(String escnsrsbh) {
        this.escnsrsbh = escnsrsbh;
    }

    public String getEscdz() {
        return escdz;
    }

    public void setEscdz(String escdz) {
        this.escdz = escdz;
    }

    public String getEsckhyhzh() {
        return esckhyhzh;
    }

    public void setEsckhyhzh(String esckhyhzh) {
        this.esckhyhzh = esckhyhzh;
    }

    public String getEscdh() {
        return escdh;
    }

    public void setEscdh(String escdh) {
        this.escdh = escdh;
    }
}
