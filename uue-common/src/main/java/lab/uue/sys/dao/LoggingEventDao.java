package lab.uue.sys.dao;

import lab.uue.core.dao.BaseDao;
import lab.uue.sys.entity.LoggingEvent;

import org.springframework.stereotype.Repository;

@Repository
public interface LoggingEventDao extends BaseDao<LoggingEvent, Long> {

}