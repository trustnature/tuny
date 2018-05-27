package xyz.tuny.project.system.dept.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Dept.class)
public abstract class Dept_ extends xyz.tuny.framework.web.domain.BaseEntity_ {

	public static volatile SingularAttribute<Dept, String> deptName;
	public static volatile SingularAttribute<Dept, String> leader;
	public static volatile SingularAttribute<Dept, Long> deptId;
	public static volatile SingularAttribute<Dept, String> orderNum;
	public static volatile SingularAttribute<Dept, String> updateTime;
	public static volatile SingularAttribute<Dept, Long> parentId;
	public static volatile SingularAttribute<Dept, String> parentName;
	public static volatile SingularAttribute<Dept, String> createBy;
	public static volatile SingularAttribute<Dept, String> phone;
	public static volatile SingularAttribute<Dept, String> createTime;
	public static volatile SingularAttribute<Dept, String> updateBy;
	public static volatile SingularAttribute<Dept, String> email;
	public static volatile SingularAttribute<Dept, Integer> status;

}

