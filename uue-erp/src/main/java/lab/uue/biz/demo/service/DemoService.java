package lab.uue.biz.demo.service;

import lab.uue.biz.demo.dao.DemoDao;
import lab.uue.biz.demo.entity.Demo;
import lab.uue.core.dao.BaseDao;
import lab.uue.core.service.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DemoService extends BaseService<Demo,String>{
    
    @Autowired
    private DemoDao demoDao;

    @Override
    protected BaseDao<Demo, String> getEntityDao() {
        return demoDao;
    }
}
