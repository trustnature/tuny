package xyz.tuny.project.system.dict.service;

import java.util.List;

import xyz.tuny.framework.web.page.TableDataInfo;
import xyz.tuny.project.system.dict.domain.DictData;
import org.springframework.data.domain.PageRequest;
import xyz.tuny.framework.web.page.TableDataInfo;

/**
 * 字典 业务层
 * 
 * @author tuny
 */
public interface IDictDataService
{

    /**
     * 根据条件分页查询字典数据
     *
     * @param dictData 字典数据信息
     * @return 字典数据集合信息
     */
    public TableDataInfo selectDictDataList(PageRequest pageRequest, DictData dictData);
    /**
     * 根据字典数据ID查询信息
     * 
     * @param dictCode 字典数据ID
     * @return 字典数据
     */
    public DictData selectDictDataById(Long dictCode);

    /**
     * 批量删除字典数据
     * 
     * @param ids 需要删除的数据
     * @return 结果
     */
    public boolean batchDeleteDictData(Long[] ids);

    /**
     * 保存字典数据信息
     * 
     * @param dictData 字典数据信息
     * @return 结果
     */
    public boolean saveDictData(DictData dictData);

}
