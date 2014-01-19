package lab.uue.sys.web.action;

import lab.uue.core.annotation.MetaData;
import lab.uue.core.service.BaseService;
import lab.uue.core.web.BaseController;
import lab.uue.sys.entity.PubPostRead;
import lab.uue.sys.service.PubPostReadService;

import org.apache.struts2.rest.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;

@MetaData(title = "公告阅读记录")
public class PubPostReadController extends BaseController<PubPostRead,String> {

    @Autowired
    private PubPostReadService pubPostReadService;

    @Override
    protected BaseService<PubPostRead, String> getEntityService() {
        return pubPostReadService;
    }
    
    @Override
    protected void checkEntityAclPermission(PubPostRead entity) {
        // TODO Add acl check code logic
    }

    @Override
    @MetaData(title = "查询")
    public HttpHeaders findByPage() {
        return super.findByPage();
    }
}