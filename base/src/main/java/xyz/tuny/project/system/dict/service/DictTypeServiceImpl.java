package xyz.tuny.project.system.dict.service;

import java.util.ArrayList;
import java.util.List;

import xyz.tuny.framework.web.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import xyz.tuny.common.constant.UserConstants;
import xyz.tuny.common.utils.StringUtils;
import xyz.tuny.common.utils.security.ShiroUtils;
import xyz.tuny.project.system.dict.dao.IDictTypeDao;
import xyz.tuny.project.system.dict.domain.DictType;
import xyz.tuny.framework.web.page.TableDataInfo;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 字典 业务层处理
 * 
 * @author tuny
 */
@Service("dictTypeService")
public class DictTypeServiceImpl implements IDictTypeService
{
    @Autowired
    private IDictTypeDao dictTypeDao;

    /**
     * 根据条件分页查询字典类型
     * 
     * @param dictType 字典类型信息
     * @return 字典类型集合信息
     */
    @Override
    public TableDataInfo selectDictTypeList(PageRequest pageRequest, DictType dictType)
    {
        String keyword = dictType.getSearchValue();
        Specification<DictType> spec = new Specification<DictType>() {
            @Override
            public Predicate toPredicate(Root<DictType> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
                List<Predicate> predicates =new ArrayList();
                if (org.apache.commons.lang3.StringUtils.isNotEmpty(keyword)) {
                    Predicate predicateT = builder.like(root.<String> get("dictName"), "%/" + keyword + "%", '/');
                    Predicate predicateP = builder.equal(root.<String> get("dictType"), keyword);
                    predicates.add(builder.or(predicateT, predicateP));
                }
                // 将所有条件用 and 联合起来
                if (predicates.size() > 0) {
                    return builder.and(predicates.toArray(new Predicate[predicates.size()]));
                }
                return builder.conjunction();
            }
        };
        Page<DictType> pageDictType = dictTypeDao.findAll(spec,pageRequest);
        TableDataInfo rspData = new TableDataInfo();
        rspData.setRows(pageDictType.getContent());
        rspData.setTotal(pageDictType.getTotalElements());
        return rspData;
    }

    /**
     * 根据字典类型ID查询信息
     * 
     * @param dictId 字典类型ID
     * @return 字典类型
     */
    @Override
    public DictType selectDictTypeById(Long dictId)
    {
        return dictTypeDao.findDictTypeByDictId(dictId);
    }

    /**
     * 批量删除字典类型
     * 
     * @param ids 需要删除的数据
     * @return 结果
     */
    @Override
    public boolean batchDeleteDictType(Long[] ids)
    {
        List<DictType> ld = new ArrayList<>();
        for(Long id : ids){
            DictType dictType = new DictType();
            dictType.setDictId(id);
            ld.add(dictType);
        }
        dictTypeDao.deleteInBatch(ld);
        return true;
    }

    /**
     * 保存字典类型信息
     * 
     * @param dictType 字典类型信息
     * @return 结果
     */
    @Override
    public boolean saveDictType(DictType dictType)
    {
        Long dictId = dictType.getDictId();
        if (StringUtils.isNotNull(dictId))
        {
            dictType.setUpdateBy(ShiroUtils.getLoginName());
             dictTypeDao.save(dictType);
        }
        else
        {
            dictType.setCreateBy(ShiroUtils.getLoginName());
            dictTypeDao.save(dictType);
        }
        return true;
    }

    /**
     * 校验字典类型称是否唯一
     * 
     * @param dictType 字典类型
     * @return 结果
     */
    @Override
    public String checkDictTypeUnique(DictType dict)
    {
        if (dict.getDictId() == null)
        {
            dict.setDictId(-1L);
        }
        Long dictId = dict.getDictId();
        DictType dictType = dictTypeDao.findDictTypeByDictType(dict.getDictType());
        if (StringUtils.isNotNull(dictType) && dictType.getDictId() != dictId)
        {
            return UserConstants.NAME_NOT_UNIQUE;
        }
        return UserConstants.NAME_UNIQUE;
    }
}
