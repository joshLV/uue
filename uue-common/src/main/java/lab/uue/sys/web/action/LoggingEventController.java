package lab.uue.sys.web.action;

import lab.uue.core.annotation.MetaData;
import lab.uue.core.service.BaseService;
import lab.uue.core.web.PersistableController;
import lab.uue.sys.entity.LoggingEvent;
import lab.uue.sys.service.LoggingEventService;

import org.apache.struts2.rest.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;

@MetaData(title = "日志处理")
public class LoggingEventController extends PersistableController<LoggingEvent,Long> {

    @Autowired
    private LoggingEventService loggingEventService;

    @Override
    protected BaseService<LoggingEvent, Long> getEntityService() {
        return loggingEventService;
    }
    
    @Override
    protected void checkEntityAclPermission(LoggingEvent entity) {
        // TODO Add acl check code logic
    }

    @Override
    @MetaData(title = "更新")
    public HttpHeaders doUpdate() {
        return super.doUpdate();
    }

    @Override
    @MetaData(title = "删除")
    public HttpHeaders doDelete() {
        return super.doDelete();
    }

    @Override
    @MetaData(title = "查询")
    public HttpHeaders findByPage() {
        return super.findByPage();
    }
}