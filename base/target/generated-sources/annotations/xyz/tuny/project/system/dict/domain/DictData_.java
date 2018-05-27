package xyz.tuny.project.system.dict.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DictData.class)
public abstract class DictData_ extends xyz.tuny.framework.web.domain.BaseEntity_ {

	public static volatile SingularAttribute<DictData, String> dictLabel;
	public static volatile SingularAttribute<DictData, String> dictValue;
	public static volatile SingularAttribute<DictData, String> createBy;
	public static volatile SingularAttribute<DictData, Long> dictCode;
	public static volatile SingularAttribute<DictData, String> createTime;
	public static volatile SingularAttribute<DictData, String> updateBy;
	public static volatile SingularAttribute<DictData, Long> dictSort;
	public static volatile SingularAttribute<DictData, String> updateTime;
	public static volatile SingularAttribute<DictData, String> remark;
	public static volatile SingularAttribute<DictData, String> dictType;
	public static volatile SingularAttribute<DictData, Integer> status;

}

