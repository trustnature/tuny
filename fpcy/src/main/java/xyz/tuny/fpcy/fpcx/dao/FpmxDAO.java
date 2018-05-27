package xyz.tuny.fpcy.fpcx.dao;

import org.springframework.stereotype.Repository;
import xyz.tuny.fpcy.fpcx.domain.Fpmx;
import xyz.tuny.framework.web.dao.BaseDao;
import xyz.tuny.fpcy.fpcx.domain.Fpmx;

import java.util.List;

@Repository
public interface FpmxDAO extends BaseDao<Fpmx, String> {
    List<Fpmx> findByCheadguid(String fpzbId);
}
