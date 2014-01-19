package lab.uue.sys.dao;

import lab.uue.core.dao.BaseDao;
import lab.uue.sys.entity.ConfigProperty;

import org.springframework.stereotype.Repository;

@Repository
public interface ConfigPropertyDao extends BaseDao<ConfigProperty, String> {

}