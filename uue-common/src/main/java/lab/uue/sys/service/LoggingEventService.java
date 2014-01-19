package lab.uue.sys.service;

import lab.uue.core.dao.BaseDao;
import lab.uue.core.service.BaseService;
import lab.uue.sys.dao.LoggingEventDao;
import lab.uue.sys.entity.LoggingEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoggingEventService extends BaseService<LoggingEvent,Long>{
    
    @Autowired
    private LoggingEventDao loggingEventDao;

    @Override
    protected BaseDao<LoggingEvent, Long> getEntityDao() {
        return loggingEventDao;
    }
}
