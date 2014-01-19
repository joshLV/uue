package lab.uue.sys.dao;

import java.util.List;

import lab.uue.auth.entity.User;
import lab.uue.core.dao.BaseDao;
import lab.uue.sys.entity.PubPost;
import lab.uue.sys.entity.PubPostRead;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PubPostReadDao extends BaseDao<PubPostRead, String> {

    @Query("from PubPostRead t where t.readUser=:readUser and t.pubPost in (:pubPosts)")
    List<PubPostRead> findReaded(@Param("readUser") User readUser, @Param("pubPosts") List<PubPost> pubPosts);

    PubPostRead findByReadUserAndPubPost(User readUser, PubPost pubPost);
}