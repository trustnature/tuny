package xyz.tuny.project.monitor.online.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserOnline.class)
public abstract class UserOnline_ extends xyz.tuny.framework.web.domain.BaseEntity_ {

	public static volatile SingularAttribute<UserOnline, String> deptName;
	public static volatile SingularAttribute<UserOnline, Date> lastAccessTime;
	public static volatile SingularAttribute<UserOnline, Long> expireTime;
	public static volatile SingularAttribute<UserOnline, String> os;
	public static volatile SingularAttribute<UserOnline, String> loginName;
	public static volatile SingularAttribute<UserOnline, String> browser;
	public static volatile SingularAttribute<UserOnline, String> sessionId;
	public static volatile SingularAttribute<UserOnline, String> ipaddr;
	public static volatile SingularAttribute<UserOnline, Date> startTimestamp;

}

