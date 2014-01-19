package lab.uue.sys.service;

import lab.uue.core.dao.BaseDao;
import lab.uue.core.service.BaseService;
import lab.uue.sys.dao.ConfigPropertyDao;
import lab.uue.sys.entity.ConfigProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ConfigPropertyService extends BaseService<ConfigProperty,String>{
    
    @Autowired
    private ConfigPropertyDao configPropertyDao;

    @Override
    protected BaseDao<ConfigProperty, String> getEntityDao() {
        return configPropertyDao;
    }
}
