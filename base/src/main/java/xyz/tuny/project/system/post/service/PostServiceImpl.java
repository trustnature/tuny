package xyz.tuny.project.system.post.service;

import java.util.ArrayList;
import java.util.List;

import xyz.tuny.framework.web.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import xyz.tuny.common.utils.StringUtils;
import xyz.tuny.common.utils.security.ShiroUtils;
import xyz.tuny.project.system.post.dao.IPostDao;
import xyz.tuny.project.system.post.domain.Post;
import xyz.tuny.framework.web.page.TableDataInfo;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 岗位信息 服务层处理
 * 
 * @author tuny
 */
@Service("postService")
public class PostServiceImpl implements IPostService
{
    @Autowired
    private IPostDao postDao;

    /**
     * 查询岗位信息集合
     * 
     * @param post 岗位信息
     * @return 岗位信息集合
     */
    @Override
    public TableDataInfo selectPostList(PageRequest pageRequest, Post post)
    {
        String keyword = post.getSearchValue();
        Specification<Post> spec = new Specification<Post>() {
            @Override
            public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
                List<Predicate> predicates =new ArrayList();
                if (StringUtils.isNotEmpty(keyword)) {
                    Predicate predicateT = builder.like(root.<String> get("postCode"), "%/" + keyword + "%", '/');
                    Predicate predicateP = builder.like(root.<String> get("postName"), "%/" + keyword + "%", '/');
                    predicates.add(builder.or(predicateT, predicateP));
                }
                if (predicates.size() > 0) {
                    return builder.and(predicates.toArray(new Predicate[predicates.size()]));
                }
                return builder.conjunction();
            }
        };

        Page<Post> pagePost = postDao.findAll(spec,pageRequest);
        TableDataInfo rspData = new TableDataInfo();
        rspData.setRows(pagePost.getContent());
        rspData.setTotal(pagePost.getTotalElements());
        return rspData;
    }

    /**
     * 查询所有岗位
     * 
     * @return 岗位列表
     */
    @Override
    public List<Post> selectPostAll()
    {
        return postDao.findAll();
    }

    /**
     * 根据用户ID查询岗位
     * 
     * @param userId 用户ID
     * @return 岗位列表
     */
    @Override
    public List<Post> selectPostsByUserId(Long userId)
    {
        List<Post> userPosts = postDao.selectPostsByUserId(userId);
        List<Post> posts = postDao.findAll();
        for (Post post : posts)
        {
            for (Post userRole : userPosts)
            {
                if (post.getPostId() == userRole.getPostId())
                {
                    post.setFlag(true);
                    break;
                }
            }
        }
        return posts;
    }
    
    

    /**
     * 通过岗位ID查询岗位信息
     * 
     * @param postId 岗位ID
     * @return 角色对象信息
     */
    @Override
    public Post selectPostById(Long postId)
    {
        return postDao.findOne(postId);
    }

    /**
     * 通过岗位ID删除岗位信息
     * 
     * @param postId 岗位ID
     * @return 结果
     */
    @Override
    public boolean deletePostById(Long postId)
    {
        postDao.delete(postId);
        return true;
    }

    /**
     * 批量删除岗位信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public boolean batchDeletePost(Long[] ids)
    {
        List<Post> lp = new ArrayList<>();
        for(Long id : ids){
            Post post = new Post();
            post.setPostId(id);
            lp.add(post);
        }
        postDao.deleteInBatch(lp);
        return true;
    }

    /**
     * 保存岗位信息
     * 
     * @param post 岗位信息
     * @return 结果
     */
    @Override
    public boolean savePost(Post post)
    {
        Long postId = post.getPostId();
        int count = 0;
        if (StringUtils.isNotNull(postId))
        {
            post.setUpdateBy(ShiroUtils.getLoginName());
            // 修改岗位信息
            postDao.save(post);
        }
        else
        {
            post.setCreateBy(ShiroUtils.getLoginName());
            // 新增岗位信息
            postDao.save(post);
        }
        return true;
    }

}
