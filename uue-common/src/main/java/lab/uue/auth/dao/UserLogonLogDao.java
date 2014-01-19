package lab.uue.auth.dao;


import lab.uue.auth.entity.UserLogonLog;
import lab.uue.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

@Repository
public interface UserLogonLogDao extends BaseDao<UserLogonLog, String> {

    UserLogonLog findByHttpSessionId(String httpSessionId);
}