package xyz.tuny.project.monitor.job.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Job.class)
public abstract class Job_ extends xyz.tuny.framework.web.domain.BaseEntity_ {

	public static volatile SingularAttribute<Job, String> jobName;
	public static volatile SingularAttribute<Job, String> cronExpression;
	public static volatile SingularAttribute<Job, Long> jobId;
	public static volatile SingularAttribute<Job, String> createBy;
	public static volatile SingularAttribute<Job, Date> createTime;
	public static volatile SingularAttribute<Job, String> updateBy;
	public static volatile SingularAttribute<Job, String> methodName;
	public static volatile SingularAttribute<Job, Date> updateTime;
	public static volatile SingularAttribute<Job, String> remark;
	public static volatile SingularAttribute<Job, String> jobGroup;
	public static volatile SingularAttribute<Job, String> params;
	public static volatile SingularAttribute<Job, Integer> status;

}

