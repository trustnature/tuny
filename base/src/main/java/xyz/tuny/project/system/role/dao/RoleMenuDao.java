package xyz.tuny.project.system.role.dao;

import java.util.List;

import xyz.tuny.framework.web.dao.BaseDao;
import xyz.tuny.project.system.role.domain.RoleMenu;
import org.springframework.stereotype.Repository;
import xyz.tuny.framework.web.dao.BaseDao;

/**
 * 角色与菜单关联表 数据层
 * 
 * @author tuny
 */
@Repository
public interface RoleMenuDao extends BaseDao<RoleMenu, Long>
{

}
