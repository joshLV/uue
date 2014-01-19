package lab.uue.sys.service;

import java.util.List;

import lab.uue.auth.entity.User;
import lab.uue.core.dao.BaseDao;
import lab.uue.core.service.BaseService;
import lab.uue.sys.dao.PubPostReadDao;
import lab.uue.sys.entity.PubPost;
import lab.uue.sys.entity.PubPostRead;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PubPostReadService extends BaseService<PubPostRead,String>{
    
    @Autowired
    private PubPostReadDao pubPostReadDao;

    @Override
    protected BaseDao<PubPostRead, String> getEntityDao() {
        return pubPostReadDao;
    }
    
    public List<PubPostRead> findReaded(User readUser,List<PubPost> pubPosts){
        return pubPostReadDao.findReaded(readUser, pubPosts);
    }
    
    public PubPostRead findReaded(User readUser,PubPost pubPost){
        return pubPostReadDao.findByReadUserAndPubPost(readUser, pubPost);
    }
}
