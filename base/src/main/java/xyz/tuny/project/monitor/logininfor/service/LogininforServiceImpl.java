package xyz.tuny.project.monitor.logininfor.service;

import java.util.ArrayList;
import java.util.List;

import xyz.tuny.framework.web.page.TableDataInfo;
import xyz.tuny.project.monitor.operlog.domain.OperLog;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import xyz.tuny.project.monitor.logininfor.dao.ILogininforDao;
import xyz.tuny.project.monitor.logininfor.domain.Logininfor;
import xyz.tuny.framework.web.page.TableDataInfo;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 系统访问日志情况信息 服务层处理
 * 
 * @author tuny
 */
@Service("logininforService")
public class LogininforServiceImpl implements ILogininforService
{

    @Autowired
    private ILogininforDao logininforDao;

    /**
     * 新增系统登录日志
     * 
     * @param logininfor 访问日志对象
     */
    @Override
    public void insertLogininfor(Logininfor logininfor)
    {
        logininforDao.save(logininfor);
    }

    /**
     * 查询系统登录日志集合
     * 
     * @param logininfor 访问日志对象
     * @return 登录记录集合
     */
    @Override
    public TableDataInfo selectLogininforList(PageRequest pageRequest, Logininfor logininfor)
    {
        String keyword = logininfor.getSearchValue();
        Specification<Logininfor> spec = new Specification<Logininfor>() {
            @Override
            public Predicate toPredicate(Root<Logininfor> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
                List<Predicate> predicates =new ArrayList();
                if (StringUtils.isNotEmpty(keyword)) {
                    Predicate predicateT = builder.like(root.<String> get("login_name"), "%/" + keyword + "%", '/');
                    predicates.add(predicateT);
                }
                // 将所有条件用 and 联合起来
                if (predicates.size() > 0) {
                    return builder.and(predicates.toArray(new Predicate[predicates.size()]));
                }
                return builder.conjunction();
            }
        };
        Page<Logininfor> pageLoginInfo = logininforDao.findAll(spec,pageRequest);
        TableDataInfo rspData = new TableDataInfo();
        rspData.setRows(pageLoginInfo.getContent());
        rspData.setTotal(pageLoginInfo.getTotalElements());
        return rspData;

    }

    /**
     * 批量删除系统登录日志
     * 
     * @param ids 需要删除的数据
     * @return
     */
    @Override
    public boolean batchDeleteLogininfor(Long[] ids)
    {
        List<Logininfor> ll = new ArrayList<>();
        for (Long id : ids) {
            Logininfor logininfor = new Logininfor();
            logininfor.setInfoId(id);
            ll.add(logininfor);
        }
        logininforDao.deleteInBatch(ll);
        return true;
    }
}
