package xyz.tuny.fpcy.fpcx.service;

import org.springframework.data.domain.PageRequest;
import xyz.tuny.framework.web.page.TableDataInfo;
import xyz.tuny.fpcy.fpcx.domain.Fpzb;

/**
 * 发票主表操作
 * 
 */
public interface IFpzbService
{

    /**
     * 查询发票集合
     * @param fpzb
     * @return
     */
    public TableDataInfo selectFpcxList(PageRequest pageRequest, Fpzb fpzb);

    /**
     * 根据ID查询发票
     * @param fpzbId
     * @return
     */
    public Fpzb selectFpzbById(String fpzbId);

}
