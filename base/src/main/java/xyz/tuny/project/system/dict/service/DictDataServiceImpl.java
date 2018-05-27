package xyz.tuny.project.system.dict.service;

import java.util.ArrayList;
import java.util.List;

import xyz.tuny.framework.web.page.TableDataInfo;
import xyz.tuny.project.system.dict.domain.DictType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import xyz.tuny.common.utils.StringUtils;
import xyz.tuny.common.utils.security.ShiroUtils;
import xyz.tuny.project.system.dict.dao.IDictDataDao;
import xyz.tuny.project.system.dict.domain.DictData;
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
@Service("dictDataService")
public class DictDataServiceImpl implements IDictDataService
{
    @Autowired
    private IDictDataDao dictDao;

    /**
     * 根据条件分页查询字典数据
     * 
     * @param dictData 字典数据信息
     * @return 字典数据集合信息
     */
    @Override
    public TableDataInfo selectDictDataList(PageRequest pageRequest, DictData dictData)
    {
        String keyword = dictData.getSearchValue();
        String dictType = dictData.getDictType();
        Specification<DictData> spec = new Specification<DictData>() {
            @Override
            public Predicate toPredicate(Root<DictData> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
                List<Predicate> predicates =new ArrayList();
                if (StringUtils.isNotEmpty(keyword)) {
                    Predicate predicateT = builder.like(root.<String> get("dict_label"), "%/" + keyword + "%", '/');
                    if (StringUtils.isNotEmpty(dictType)) {
                        Predicate predicateP = builder.equal(root.<String> get("dictType"), dictType);
                        predicates.add(builder.or(predicateT, predicateP));
                    }
                }else{
                    if (StringUtils.isNotEmpty(dictType)) {
                        Predicate predicateP = builder.equal(root.<String> get("dictType"), dictType);
                        predicates.add(predicateP);
                    }
                }
                // 将所有条件用 and 联合起来
                if (predicates.size() > 0) {
                    return builder.and(predicates.toArray(new Predicate[predicates.size()]));
                }
                return builder.conjunction();
            }
        };
        Page<DictData> pageDictType = dictDao.findAll(spec,pageRequest);
        TableDataInfo rspData = new TableDataInfo();
        rspData.setRows(pageDictType.getContent());
        rspData.setTotal(pageDictType.getTotalElements());
        return rspData;
    }

    /**
     * 根据字典数据ID查询信息
     * 
     * @param dictCode 字典数据ID
     * @return 字典数据
     */
    @Override
    public DictData selectDictDataById(Long dictCode)
    {
        return dictDao.findDictDataByDictCode(dictCode);
    }

    /**
     * 批量删除字典数据
     * 
     * @param ids 需要删除的数据
     * @return 结果
     */
    @Override
    public boolean batchDeleteDictData(Long[] ids)
    {
        List<DictData> ld = new ArrayList<>();
        for (Long id : ids) {
            DictData dictData = new DictData();
            dictData.setDictCode(id);
            ld.add(dictData);
        }
        dictDao.deleteInBatch(ld);
        return true;
    }

    /**
     * 保存字典数据信息
     * 
     * @param dictData 字典数据信息
     * @return 结果
     */
    @Override
    public boolean saveDictData(DictData dictData)
    {
        Long dictCode = dictData.getDictCode();
        if (StringUtils.isNotNull(dictCode))
        {
            dictData.setUpdateBy(ShiroUtils.getLoginName());
            dictDao.save(dictData);
        }
        else
        {
            dictData.setCreateBy(ShiroUtils.getLoginName());
            dictDao.save(dictData);
        }
        return true;
    }

}
