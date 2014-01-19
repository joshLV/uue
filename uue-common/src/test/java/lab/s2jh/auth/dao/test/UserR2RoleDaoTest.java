package lab.s2jh.auth.dao.test;

import lab.s2jh.core.test.SpringTransactionalTestCase;
import lab.uue.auth.dao.UserR2RoleDao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserR2RoleDaoTest extends SpringTransactionalTestCase {
    @Autowired
    private UserR2RoleDao userR2RoleDao;

    @Test
    public void findByUser_Id() {
        userR2RoleDao.findByUser_Id("MOCKID");
    }
}
