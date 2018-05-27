package xyz.tuny.project.system.dept.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xyz.tuny.project.system.role.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import xyz.tuny.common.utils.StringUtils;
import xyz.tuny.common.utils.security.ShiroUtils;
import xyz.tuny.project.system.dept.dao.IDeptDao;
import xyz.tuny.project.system.dept.domain.Dept;
import xyz.tuny.project.system.dept.domain.Dept;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 部门管理 服务实现
 * 
 * @author tuny
 */
@Repository("deptService")
public class DeptServiceImpl implements IDeptService
{
    @Autowired
    private IDeptDao deptDao;

    /**
     * 查询部门管理集合
     * 
     * @return 所有部门信息
     */
    @Override
    public List<Dept> selectDeptAll()
    {
        return deptDao.findAll();
    }

    /**
     * 查询部门管理树
     * 
     * @return 所有部门信息
     */
    @Override
    public List<Map<String, Object>> selectDeptTree()
    {
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        List<Dept> deptList = deptDao.findAll();

        for (Dept dept : deptList)
        {
            Map<String, Object> deptMap = new HashMap<String, Object>();
            deptMap.put("id", dept.getDeptId());
            deptMap.put("pId", dept.getParentId());
            deptMap.put("name", dept.getDeptName());
            trees.add(deptMap);
        }
        return trees;
    }

    /**
     * 查询部门人数
     * 
     * @param parentId 部门ID
     * @return 结果
     */
    @Override
    public boolean selectDeptCount(Long parentId)
    {
        Specification<Dept> spec = new Specification<Dept>() {
            @Override
            public Predicate toPredicate(Root<Dept> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
                List<Predicate> predicates =new ArrayList();
                if (null != parentId) {
                    Predicate predicateP = builder.equal(root.<Long> get("parentId"), parentId);
                    predicates.add(predicateP);
                }
                if (predicates.size() > 0) {
                    return builder.and(predicates.toArray(new Predicate[predicates.size()]));
                }
                return builder.conjunction();
            }
        };
        return deptDao.count(spec) > 0L ? true : false;
    }

    /**
     * 查询部门是否存在用户
     * 
     * @param deptId 部门ID
     * @return 结果 true 存在 false 不存在
     */
    @Override
    public boolean checkDeptExistUser(Long deptId)
    {
        int result = deptDao.checkDeptExistUser(deptId);
        return result > 0 ? true : false;
    }

    /**
     * 删除部门管理信息
     * 
     * @param deptId 部门ID
     * @return 结果
     */
    @Override
    public boolean deleteDeptById(Long deptId)
    {
        deptDao.delete(deptId);
        return true;
    }

    /**
     * 保存部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public boolean saveDept(Dept dept)
    {
        if (StringUtils.isNotNull(dept.getDeptId()))
        {
            dept.setUpdateBy(ShiroUtils.getLoginName());
             deptDao.save(dept);
        }
        else
        {
            dept.setCreateBy(ShiroUtils.getLoginName());
            deptDao.save(dept);
        }
        return true;
    }

    /**
     * 根据部门ID查询信息
     * 
     * @param deptId 部门ID
     * @return 部门信息
     */
    @Override
    public Dept selectDeptById(Long deptId)
    {
        return deptDao.selectDeptById(deptId);
    }
}
