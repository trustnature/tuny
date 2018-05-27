package xyz.tuny.project.system.user.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ extends xyz.tuny.framework.web.domain.BaseEntity_ {

	public static volatile SingularAttribute<User, String> salt;
	public static volatile SingularAttribute<User, Long> deptId;
	public static volatile SingularAttribute<User, String> phonenumber;
	public static volatile SingularAttribute<User, String> refuseDes;
	public static volatile SingularAttribute<User, String> updateTime;
	public static volatile SingularAttribute<User, String> userName;
	public static volatile SingularAttribute<User, Long> userId;
	public static volatile SingularAttribute<User, Long> parentId;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> createBy;
	public static volatile SingularAttribute<User, String> createTime;
	public static volatile SingularAttribute<User, String> updateBy;
	public static volatile SingularAttribute<User, String> loginName;
	public static volatile SingularAttribute<User, String> userType;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, Integer> status;

}

