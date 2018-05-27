package xyz.tuny.project.monitor.logininfor.service;

import java.util.List;

import xyz.tuny.framework.web.page.TableDataInfo;
import xyz.tuny.project.monitor.logininfor.domain.Logininfor;
import org.springframework.data.domain.PageRequest;
import xyz.tuny.framework.web.page.TableDataInfo;
import xyz.tuny.project.monitor.logininfor.domain.Logininfor;

/**
 * 系统访问日志情况信息 服务层
 * 
 * @author tuny
 */
public interface ILogininforService
{

    /**
     * 新增系统登录日志
     * 
     * @param logininfor 访问日志对象
     */
    public void insertLogininfor(Logininfor logininfor);

    /**
     * 查询系统登录日志集合
     * 
     * @param logininfor 访问日志对象
     * @return 登录记录集合
     */
    public TableDataInfo selectLogininforList(PageRequest pageRequest, Logininfor logininfor);

    /**
     * 批量删除系统登录日志
     * 
     * @param ids 需要删除的数据
     * @return
     */
    public boolean batchDeleteLogininfor(Long[] ids);
}
