package xyz.tuny.framework.web.domain;

import xyz.tuny.framework.web.page.PageDomain;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseEntity extends PageDomain implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -250118731239275742L;

}