package xyz.tuny.project.system.dept.dao;

import java.util.List;

import xyz.tuny.framework.web.dao.BaseDao;
import xyz.tuny.project.system.dept.domain.Dept;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import xyz.tuny.framework.web.dao.BaseDao;
import xyz.tuny.project.system.dept.domain.Dept;

/**
 * 部门管理 数据层
 * 
 * @author tuny
 */
@Repository
public interface IDeptDao extends BaseDao<Dept, Long>
{
    /**
     * 查询部门是否存在用户
     * 
     * @param deptId 部门ID
     * @return 结果
     */
    @Query(value="select count(*) from sys_user where dept_id = ?1",nativeQuery = true)
    public int checkDeptExistUser(Long deptId);


    /**
     * 根据部门ID查询信息
     * 
     * @param deptId 部门ID
     * @return 部门信息
     */
    @Query(value="select t.dept_id, t.parent_id, t.dept_name, t.order_num, t.leader, t.phone, t.email, t.status,t.create_by,t.create_time,t.update_by,t.update_time," +
            "(select dept_name from sys_dept where dept_id = t.parent_id) parent_name " +
            "from sys_dept t " +
            "where t.dept_id = ?1",nativeQuery = true)
    public Dept selectDeptById(Long deptId);
}
