package xyz.tuny.project.system.post.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Post.class)
public abstract class Post_ extends xyz.tuny.framework.web.domain.BaseEntity_ {

	public static volatile SingularAttribute<Post, String> postSort;
	public static volatile SingularAttribute<Post, String> createBy;
	public static volatile SingularAttribute<Post, String> createTime;
	public static volatile SingularAttribute<Post, String> updateBy;
	public static volatile SingularAttribute<Post, String> postName;
	public static volatile SingularAttribute<Post, String> postCode;
	public static volatile SingularAttribute<Post, String> updateTime;
	public static volatile SingularAttribute<Post, String> remark;
	public static volatile SingularAttribute<Post, Long> postId;
	public static volatile SingularAttribute<Post, Integer> status;

}

