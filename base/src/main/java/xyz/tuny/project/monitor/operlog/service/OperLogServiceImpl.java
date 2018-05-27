package xyz.tuny.project.monitor.operlog.service;

import java.util.ArrayList;
import java.util.List;

import xyz.tuny.framework.web.page.TableDataInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import xyz.tuny.project.monitor.operlog.dao.IOperLogDao;
import xyz.tuny.project.monitor.operlog.domain.OperLog;
import xyz.tuny.framework.web.page.TableDataInfo;
import xyz.tuny.project.monitor.operlog.dao.IOperLogDao;
import xyz.tuny.project.monitor.operlog.domain.OperLog;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 操作日志 服务层处理
 * 
 * @author tuny
 */
@Service("operLogService")
public class OperLogServiceImpl implements IOperLogService
{
    @Autowired
    private IOperLogDao operLogDao;

    /**
     * 新增操作日志
     * 
     * @param operLog 操作日志对象
     */
    @Override
    public void insertOperlog(OperLog operLog)
    {
        operLogDao.save(operLog);
    }

    /**
     * 查询系统操作日志集合
     * 
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
    @Override
    public TableDataInfo selectOperLogList(PageRequest pageRequest, OperLog operLog)
    {
        String keyword = operLog.getSearchValue();
        Specification<OperLog> spec = new Specification<OperLog>() {
            @Override
            public Predicate toPredicate(Root<OperLog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
                List<Predicate> predicates =new ArrayList();
                if (StringUtils.isNotEmpty(keyword)) {
                    Predicate predicateT = builder.like(root.<String> get("loginName"), "%/" + keyword + "%", '/');
                    predicates.add(predicateT);
                }
                // 将所有条件用 and 联合起来
                if (predicates.size() > 0) {
                    return builder.and(predicates.toArray(new Predicate[predicates.size()]));
                }
                return builder.conjunction();
            }
        };
        Page<OperLog> pageOperLog = operLogDao.findAll(spec,pageRequest);
        TableDataInfo rspData = new TableDataInfo();
        rspData.setRows(pageOperLog.getContent());
        rspData.setTotal(pageOperLog.getTotalElements());
        return rspData;
    }

    /**
     * 批量删除系统操作日志
     * 
     * @param ids 需要删除的数据
     * @return
     */
    @Override
    public boolean batchDeleteOperLog(Long[] ids)
    {
        List<OperLog> lo = new ArrayList<>();
        for (Long id : ids) {
            OperLog operLog = new OperLog();
            operLog.setOperId(id);
            lo.add(operLog);
        }
        operLogDao.deleteInBatch(lo);
        return true;
    }

    /**
     * 查询操作日志详细
     * 
     * @param operId 操作ID
     * @return 操作日志对象
     */
    @Override
    public OperLog selectOperLogById(Long operId)
    {
        return operLogDao.findOperLogByOperId(operId);
    }
}
