package xyz.tuny.project.system.user.service;

import java.util.ArrayList;
import java.util.List;

import xyz.tuny.framework.web.page.TableDataInfo;
import xyz.tuny.project.system.dept.dao.IDeptDao;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;

import xyz.tuny.common.constant.UserConstants;
import xyz.tuny.common.utils.StringUtils;
import xyz.tuny.common.utils.security.ShiroUtils;
import xyz.tuny.framework.shiro.service.PasswordService;
import xyz.tuny.project.system.user.dao.IUserDao;
import xyz.tuny.project.system.user.dao.IUserPostDao;
import xyz.tuny.project.system.user.dao.IUserRoleDao;
import xyz.tuny.project.system.user.domain.User;
import xyz.tuny.project.system.user.domain.UserPost;
import xyz.tuny.project.system.user.domain.UserRole;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import xyz.tuny.framework.web.page.TableDataInfo;
import xyz.tuny.project.system.user.dao.IUserDao;
import xyz.tuny.project.system.user.dao.IUserPostDao;
import xyz.tuny.project.system.user.dao.IUserRoleDao;
import xyz.tuny.project.system.user.domain.User;
import xyz.tuny.project.system.user.domain.UserRole;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transaction;

/**
 * 用户 业务层处理
 * 
 * @author tuny
 */
@Service("userService")
public class UserServiceImpl implements IUserService,ApplicationContextAware
{

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IDeptDao deptDao;
    
    @Autowired
    private IUserPostDao userPostDao;
    
    @Autowired
    private IUserRoleDao userRoleDao;

    /**
     * 根据条件分页查询用户对象
     * 
     * @param user 用户信息
     * 
     * @return 用户信息集合信息
     */
    @Override
    public TableDataInfo selectUserList(PageRequest pageRequest, User user)
    {
        TableDataInfo rspData = new TableDataInfo();
        Page<User> pageUser = null;
        int type = 0; //未带有查询条件
        if(StringUtils.isNotEmpty(user.getSearchValue())){
            if(null != user.getDeptId() && null != user.getParentId() && user.getParentId() != 0L){
                type = 1; //带有部门限制
            }else{
                type = 2;//无部门限制
            }
        }else{
            if(null != user.getDeptId() && null != user.getParentId() && user.getParentId() != 0L){
                type = 3; //带有部门限制
            }
        }
        if(0 == type){
            pageUser = userDao.findAll(pageRequest);
            pageUser.getContent();
            rspData.setRows(pageUser.getContent());
            rspData.setTotal(pageUser.getTotalElements());
        }else if(2 == type){
            Specification<User> spec = new Specification<User>() {
                @Override
                public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
                    List<Predicate> predicates =new ArrayList();
                    if (StringUtils.isNotEmpty(user.getSearchValue())) {
                        Predicate predicateT = builder.equal(root.<String> get("loginName"), user.getSearchValue());
                        predicates.add(predicateT);
                    }
                    if (predicates.size() > 0) {
                        return builder.and(predicates.toArray(new Predicate[predicates.size()]));
                    }
                    return builder.conjunction();
                }
            };
            pageUser = userDao.findAll(spec,pageRequest);
            rspData.setRows(pageUser.getContent());
            rspData.setTotal(pageUser.getTotalElements());
        }else if (3 == type){
            List<User> lu = userDao.selectUserListInDeptA(user.getParentId());
            rspData.setRows(lu);
            rspData.setTotal(lu.size());
        } else{
            List<User> lu = userDao.selectUserListInDept(user.getSearchValue(),user.getParentId());
            rspData.setRows(lu);
            rspData.setTotal(lu.size());
        }
        return rspData;
    }

    /**
     * 通过用户名查询用户
     * 
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Override
    public User selectUserByName(String userName)
    {
        User user =  userDao.selectUserByName(userName);
        user.setDept(deptDao.findOne(user.getDeptId()));
        return user;
    }

    /**
     * 通过用户ID查询用户
     * 
     * @param userId 用户ID
     * @return 用户对象信息
     */
    @Override
    public User selectUserById(Long userId)
    {
        User user = userDao.selectUserById(userId);
        user.setDept(deptDao.findOne(user.getDeptId()));
        return user;
    }

    /**
     * 通过用户ID删除用户
     * 
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public boolean deleteUserById(Long userId)
    {
        if(TransactionSynchronizationManager.isActualTransactionActive()){
            System.out.println("deleteUserById has tx");
        }
        // 删除用户与角色关联
        userRoleDao.delUserRoleByUserId(userId);
        userDao.delete(userId);
        return true;
    }

    /**
     * 批量删除用户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public boolean batchDeleteUser(Long[] ids)
    {
        List<User> lu = new ArrayList<>();
        for(Long id: ids){
            User user = new User();
            user.setUserId(id);
            lu.add(user);
        }
        userDao.delete(lu);
        return true;
    }

    /**
     * 保存用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public boolean saveUser(User user)
    {
        int count = 0;
        Long userId = user.getUserId();
        String password = new PasswordService().encryptPassword(user.getLoginName(), user.getPassword(), "");
        user.setPassword(password);
        if (StringUtils.isNotNull(userId))
        {
            user.setUpdateBy(ShiroUtils.getLoginName());
            // 修改用户信息
            userDao.save(user);
            // 删除用户与角色关联
            userRoleDao.delUserRoleByUserId(userId);
            // 新增用户与角色管理
            insertUserRole(user);
            // 删除用户与岗位关联
            userPostDao.deleteUserPostByUserId(userId);
            // 新增用户与岗位管理
            insertUserPost(user);

        } else {
            user.setCreateBy(ShiroUtils.getLoginName());
            // 新增用户信息
            userDao.save(user);
            // 新增用户岗位关联
            insertUserPost(user);
            // 新增用户与角色管理
            insertUserRole(user);
        }
        return true;
    }

    /**
     * 修改用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public boolean updateUser(User user)
    {
        String password = new PasswordService().encryptPassword(user.getLoginName(), user.getPassword(), "");
        user.setPassword(password);
        userDao.save(user);
        return true;
    }

    /**
     * 新增用户角色信息
     * 
     * @param user 用户对象
     */
    public void insertUserRole(User user)
    {
        // 新增用户与角色管理
        List<UserRole> list = new ArrayList<UserRole>();
        for (Long roleId : user.getRoleIds())
        {
            UserRole ur = new UserRole();
            ur.setUserId(user.getUserId());
            ur.setRoleId(roleId);
            list.add(ur);
        }
        if (list.size() > 0)
        {
            userRoleDao.save(list);
        }
    }

    /**
     * 新增用户岗位信息
     * 
     * @param user 用户对象
     */
    public void insertUserPost(User user)
    {
        // 新增用户与岗位管理
        List<UserPost> list = new ArrayList<UserPost>();
        for (Long postId : user.getPostIds())
        {
            UserPost up = new UserPost();
            up.setUserId(user.getUserId());
            up.setPostId(postId);
            list.add(up);
        }
        if (list.size() > 0)
        {
            userPostDao.save(list);
        }
    }

    /**
     * 校验用户名称是否唯一
     * 
     * @return
     */
    @Override
    public String checkNameUnique(String loginName)
    {
        Specification<User> spec = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
                List<Predicate> predicates =new ArrayList();
                if (org.apache.commons.lang3.StringUtils.isNotEmpty(loginName)) {
                    Predicate predicateT = builder.equal(root.<String> get("loginName"), loginName);
                    predicates.add(predicateT);
                }
                if (predicates.size() > 0) {
                    return builder.and(predicates.toArray(new Predicate[predicates.size()]));
                }
                return builder.conjunction();
            }
        };
        Long count = userDao.count(spec);
        if (count > 0L)
        {
            return UserConstants.NAME_NOT_UNIQUE;
        }
        return UserConstants.NAME_UNIQUE;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
       // System.out.println("====");
    }
}
