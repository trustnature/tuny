package xyz.tuny.project.system.user.domain;

import xyz.tuny.framework.web.domain.BaseEntity;
import xyz.tuny.framework.web.domain.BaseEntity;

import javax.persistence.*;

/**
 * 用户和岗位关联 sys_user_post
 * 
 */

@Entity
@Table(name="sys_user_post")
public class UserPost extends BaseEntity
{
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    /** 用户ID */
    @Column(name = "user_id")
    private Long userId;

    /** 岗位ID */
    @Column(name = "post_id")
    private Long postId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return "UserPost{" +
                "userId=" + userId +
                ", postId=" + postId +
                '}';
    }
}
