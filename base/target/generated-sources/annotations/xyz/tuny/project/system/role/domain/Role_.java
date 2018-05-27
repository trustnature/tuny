package xyz.tuny.project.system.role.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Role.class)
public abstract class Role_ extends xyz.tuny.framework.web.domain.BaseEntity_ {

	public static volatile SingularAttribute<Role, String> createBy;
	public static volatile SingularAttribute<Role, Date> createTime;
	public static volatile SingularAttribute<Role, String> updateBy;
	public static volatile SingularAttribute<Role, Long> roleId;
	public static volatile SingularAttribute<Role, String> roleName;
	public static volatile SingularAttribute<Role, String> roleKey;
	public static volatile SingularAttribute<Role, Date> updateTime;
	public static volatile SingularAttribute<Role, String> remark;
	public static volatile SingularAttribute<Role, Long[]> menuIds;
	public static volatile SingularAttribute<Role, String> roleSort;
	public static volatile SingularAttribute<Role, Integer> status;

}

