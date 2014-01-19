package lab.uue.auth.service;

import lab.uue.auth.dao.UserLogonLogDao;
import lab.uue.auth.entity.UserLogonLog;
import lab.uue.core.dao.BaseDao;
import lab.uue.core.service.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserLogonLogService extends BaseService<UserLogonLog,String>{
    
    @Autowired
    private UserLogonLogDao userLogonLogDao;

    @Override
    protected BaseDao<UserLogonLog, String> getEntityDao() {
        return userLogonLogDao;
    }
    
    public UserLogonLog findBySessionId(String httpSessionId){
        return userLogonLogDao.findByHttpSessionId(httpSessionId);
    }
}
