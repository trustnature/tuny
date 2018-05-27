package xyz.tuny.project.monitor.logininfor.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Logininfor.class)
public abstract class Logininfor_ extends xyz.tuny.framework.web.domain.BaseEntity_ {

	public static volatile SingularAttribute<Logininfor, String> msg;
	public static volatile SingularAttribute<Logininfor, Long> infoId;
	public static volatile SingularAttribute<Logininfor, Date> loginTime;
	public static volatile SingularAttribute<Logininfor, String> os;
	public static volatile SingularAttribute<Logininfor, String> loginName;
	public static volatile SingularAttribute<Logininfor, String> browser;
	public static volatile SingularAttribute<Logininfor, String> ipaddr;
	public static volatile SingularAttribute<Logininfor, String> status;

}

