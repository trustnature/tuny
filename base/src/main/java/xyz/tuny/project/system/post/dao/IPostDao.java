package xyz.tuny.project.system.post.dao;

import java.util.List;

import xyz.tuny.framework.web.dao.BaseDao;
import xyz.tuny.project.system.post.domain.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import xyz.tuny.framework.web.dao.BaseDao;
import xyz.tuny.project.system.post.domain.Post;

/**
 * 岗位信息 数据层
 * 
 * @author tuny
 */
@Repository
public interface IPostDao  extends BaseDao<Post, Long>
{

    /**
     * 根据用户ID查询岗位
     * 
     * @param userId 用户ID
     * @return 岗位列表
     */
    @Query(value = "SELECT p.* " +
            "FROM sys_user u " +
            "LEFT JOIN sys_user_post up ON u.user_id = up.user_id " +
            "LEFT JOIN sys_post p ON up.post_id = p.post_id " +
            "WHERE up.user_id = ?1",nativeQuery = true)
    public List<Post> selectPostsByUserId(Long userId);

}
