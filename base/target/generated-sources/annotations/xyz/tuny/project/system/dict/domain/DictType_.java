package xyz.tuny.project.system.dict.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DictType.class)
public abstract class DictType_ extends xyz.tuny.framework.web.domain.BaseEntity_ {

	public static volatile SingularAttribute<DictType, String> createBy;
	public static volatile SingularAttribute<DictType, String> createTime;
	public static volatile SingularAttribute<DictType, String> updateBy;
	public static volatile SingularAttribute<DictType, String> dictName;
	public static volatile SingularAttribute<DictType, String> updateTime;
	public static volatile SingularAttribute<DictType, String> remark;
	public static volatile SingularAttribute<DictType, Long> dictId;
	public static volatile SingularAttribute<DictType, String> dictType;
	public static volatile SingularAttribute<DictType, Integer> status;

}

