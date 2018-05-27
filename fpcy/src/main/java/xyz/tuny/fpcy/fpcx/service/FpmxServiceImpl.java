package xyz.tuny.fpcy.fpcx.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import xyz.tuny.framework.web.page.TableDataInfo;
import xyz.tuny.fpcy.fpcx.dao.FpmxDAO;
import xyz.tuny.fpcy.fpcx.domain.Fpmx;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service("fpmxService")
public class FpmxServiceImpl implements IFpmxService {
    @Autowired
    private FpmxDAO fpmxDao;

    @Override
    public TableDataInfo selectFpmxList(PageRequest pageRequest, Fpmx fpmx) {
        String keyword = fpmx.getSearchValue();
        Specification<Fpmx> spec = new Specification<Fpmx>() {
            @Override
            public Predicate toPredicate(Root<Fpmx> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
                List<Predicate> predicates =new ArrayList();
                if (StringUtils.isNotEmpty(keyword)) {
                    Predicate predicateT = builder.like(root.<String> get("fpdm"), "%/" + keyword + "%", '/');
                    Predicate predicateP = builder.like(root.<String> get("fphm"), "%/" + keyword + "%", '/');
                    predicates.add(builder.or(predicateT, predicateP));
                }
                // 将所有条件用 and 联合起来
                if (predicates.size() > 0) {
                    return builder.and(predicates.toArray(new Predicate[predicates.size()]));
                }
                return builder.conjunction();
            }
        };
        Page<Fpmx> pageFpzb = fpmxDao.findAll(spec,pageRequest);
        TableDataInfo rspData = new TableDataInfo();
        rspData.setRows(pageFpzb.getContent());
        rspData.setTotal(pageFpzb.getTotalElements());
        return rspData;
    }

    @Override
    public Fpmx selectFpmxById(String fpmxId) {
        return fpmxDao.findOne(fpmxId);
    }

    @Override
    public List<Fpmx> selectFpmxDetailByZbid(String fpzbId) {
        return fpmxDao.findByCheadguid(fpzbId);
    }

}
