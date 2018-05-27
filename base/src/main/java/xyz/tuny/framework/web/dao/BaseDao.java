package xyz.tuny.framework.web.dao;

import xyz.tuny.framework.web.domain.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import xyz.tuny.framework.web.domain.BaseEntity;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseDao<T extends BaseEntity, ID extends Serializable>
        extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

}
