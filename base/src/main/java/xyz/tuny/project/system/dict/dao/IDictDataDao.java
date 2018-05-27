package xyz.tuny.project.system.dict.dao;

import java.util.List;

import xyz.tuny.framework.web.dao.BaseDao;
import xyz.tuny.project.system.dict.domain.DictData;
import org.springframework.stereotype.Repository;
import xyz.tuny.framework.web.dao.BaseDao;
import xyz.tuny.project.system.dict.domain.DictData;

/**
 * 字典表 数据层
 * 
 * @author tuny
 */
@Repository
public interface IDictDataDao  extends BaseDao<DictData, Long>
{

    /**
     * 根据字典数据ID查询信息
     * 
     * @param dictCode 字典数据ID
     * @return 字典数据
     */
    public DictData findDictDataByDictCode(Long dictCode);


}
