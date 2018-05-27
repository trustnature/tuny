package xyz.tuny.project.monitor.job.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(JobLog.class)
public abstract class JobLog_ extends xyz.tuny.framework.web.domain.BaseEntity_ {

	public static volatile SingularAttribute<JobLog, String> jobName;
	public static volatile SingularAttribute<JobLog, String> jobMessage;
	public static volatile SingularAttribute<JobLog, Date> createTime;
	public static volatile SingularAttribute<JobLog, Integer> isException;
	public static volatile SingularAttribute<JobLog, String> methodName;
	public static volatile SingularAttribute<JobLog, String> jobGroup;
	public static volatile SingularAttribute<JobLog, Long> jobLogId;
	public static volatile SingularAttribute<JobLog, String> params;
	public static volatile SingularAttribute<JobLog, String> exceptionInfo;

}

