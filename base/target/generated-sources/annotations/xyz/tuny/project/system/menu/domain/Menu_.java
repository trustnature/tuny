package xyz.tuny.project.system.menu.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Menu.class)
public abstract class Menu_ extends xyz.tuny.framework.web.domain.BaseEntity_ {

	public static volatile SingularAttribute<Menu, Integer> visible;
	public static volatile SingularAttribute<Menu, String> icon;
	public static volatile SingularAttribute<Menu, String> orderNum;
	public static volatile SingularAttribute<Menu, String> menuName;
	public static volatile SingularAttribute<Menu, String> updateTime;
	public static volatile SingularAttribute<Menu, String> remark;
	public static volatile SingularAttribute<Menu, Long> parentId;
	public static volatile SingularAttribute<Menu, String> url;
	public static volatile SingularAttribute<Menu, String> parentName;
	public static volatile SingularAttribute<Menu, String> createBy;
	public static volatile SingularAttribute<Menu, String> createTime;
	public static volatile SingularAttribute<Menu, String> updateBy;
	public static volatile SingularAttribute<Menu, Long> menuId;
	public static volatile SingularAttribute<Menu, String> menuType;
	public static volatile SingularAttribute<Menu, String> perms;

}

