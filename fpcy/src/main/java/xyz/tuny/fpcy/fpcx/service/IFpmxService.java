package xyz.tuny.fpcy.fpcx.service;

import org.springframework.data.domain.PageRequest;
import xyz.tuny.framework.web.page.TableDataInfo;
import xyz.tuny.fpcy.fpcx.domain.Fpmx;

import java.util.List;

/**
 * 发票明细操作
 * 
 */
public interface IFpmxService
{

    /**
     * 查询发票明细集合
     * @param fpmx
     * @return
     */
    public TableDataInfo selectFpmxList(PageRequest pageRequest, Fpmx fpmx);

    /**
     * 根据ID查询发票明细
     * @param fpmxId
     * @return
     */
    public Fpmx selectFpmxById(String fpmxId);

    /**
     * 根据发票主表ID查询明细
     * @param fpzbId
     * @return
     */
    public List<Fpmx> selectFpmxDetailByZbid(String fpzbId);

}
