package lab.s2jh.sys.service.test;

import lab.s2jh.core.test.SpringTransactionalTestCase;
import lab.s2jh.core.test.TestObjectUtils;
import lab.uue.auth.entity.User;
import lab.uue.auth.service.UserService;
import lab.uue.sys.entity.PubPost;
import lab.uue.sys.service.PubPostReadService;
import lab.uue.sys.service.PubPostService;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;


public class PubPostReadServiceTest extends SpringTransactionalTestCase {

    @Autowired
    private PubPostReadService pubPostReadService;

    @Autowired
    private PubPostService pubPostService;

    @Autowired
    private UserService userService;

    @Test
    public void findReaded() {
        PubPost entity = TestObjectUtils.buildMockObject(PubPost.class);
        pubPostService.save(entity);

        User user = TestObjectUtils.buildMockObject(User.class);
        userService.save(user);

        pubPostReadService.findReaded(user, Lists.newArrayList(entity));
    }
}