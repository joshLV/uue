package lab.uue.rpt.service;

import lab.uue.core.dao.BaseDao;
import lab.uue.core.service.BaseService;
import lab.uue.rpt.dao.ReportParamDao;
import lab.uue.rpt.entity.ReportParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReportParamService extends BaseService<ReportParam,String>{
    
    @Autowired
    private ReportParamDao reportParamDao;

    @Override
    protected BaseDao<ReportParam, String> getEntityDao() {
        return reportParamDao;
    }
}
