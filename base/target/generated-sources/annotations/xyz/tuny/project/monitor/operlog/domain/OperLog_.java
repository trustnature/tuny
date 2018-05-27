package xyz.tuny.project.monitor.operlog.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OperLog.class)
public abstract class OperLog_ extends xyz.tuny.framework.web.domain.BaseEntity_ {

	public static volatile SingularAttribute<OperLog, String> deptName;
	public static volatile SingularAttribute<OperLog, String> method;
	public static volatile SingularAttribute<OperLog, String> channel;
	public static volatile SingularAttribute<OperLog, Long> operId;
	public static volatile SingularAttribute<OperLog, String> title;
	public static volatile SingularAttribute<OperLog, String> errorMsg;
	public static volatile SingularAttribute<OperLog, String> operIp;
	public static volatile SingularAttribute<OperLog, String> loginName;
	public static volatile SingularAttribute<OperLog, String> operUrl;
	public static volatile SingularAttribute<OperLog, String> action;
	public static volatile SingularAttribute<OperLog, String> operParam;
	public static volatile SingularAttribute<OperLog, Integer> status;
	public static volatile SingularAttribute<OperLog, Date> operTime;

}

