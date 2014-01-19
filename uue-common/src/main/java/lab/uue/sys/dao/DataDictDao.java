package lab.uue.sys.dao;

import java.util.List;

import javax.persistence.QueryHint;

import lab.uue.core.dao.BaseDao;
import lab.uue.sys.entity.DataDict;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

@Repository
public interface DataDictDao extends BaseDao<DataDict, String> {

    @Query("select distinct d.category from DataDict d order by d.category asc")
    @QueryHints({ @QueryHint(name = org.hibernate.ejb.QueryHints.HINT_CACHEABLE, value = "true") })
    public List<String> findDistinctCategories();

    @QueryHints({ @QueryHint(name = org.hibernate.ejb.QueryHints.HINT_CACHEABLE, value = "true") })
    public List<DataDict> findByCategoryOrderByOrderRankDesc(String category);
}