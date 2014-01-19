package lab.uue.rpt.dao;

import java.util.List;

import javax.persistence.QueryHint;

import lab.uue.core.dao.BaseDao;
import lab.uue.rpt.entity.ReportDef;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportDefDao extends BaseDao<ReportDef, String> {

    @Query("from ReportDef where disabled!=true order by category asc, orderRank desc")
    @QueryHints({ @QueryHint(name = org.hibernate.ejb.QueryHints.HINT_CACHEABLE, value = "true") })
    List<ReportDef> findDisplayItems();
    
    ReportDef findByCode(String code);
    
    @Query("select distinct category from ReportDef order by category asc")
    List<String> findCategories();
}