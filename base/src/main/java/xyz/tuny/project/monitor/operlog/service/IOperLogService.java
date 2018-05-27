package xyz.tuny.project.monitor.operlog.service;

import java.util.List;

import xyz.tuny.framework.web.page.TableDataInfo;
import xyz.tuny.project.monitor.operlog.domain.OperLog;
import org.springframework.data.domain.PageRequest;
import xyz.tuny.framework.web.page.TableDataInfo;
import xyz.tuny.project.monitor.operlog.domain.OperLog;

/**
 * 操作日志 服务层
 * 
 * @author tuny
 */
public interface IOperLogService
{
    /**
     * 新增操作日志
     *
     * @param operLog 操作日志对象
     */
    public void insertOperlog(OperLog operLog);

    /**
     * 查询系统操作日志集合
     * 
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
    public TableDataInfo selectOperLogList(PageRequest pageRequest, OperLog operLog);

    /**
     * 批量删除系统操作日志
     * 
     * @param ids 需要删除的数据
     * @return 结果
     */
    public boolean batchDeleteOperLog(Long[] ids);

    /**
     * 查询操作日志详细
     * 
     * @param operId 操作ID
     * @return 操作日志对象
     */
    public OperLog selectOperLogById(Long operId);
}
