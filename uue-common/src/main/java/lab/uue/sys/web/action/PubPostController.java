package lab.uue.sys.web.action;

import lab.uue.core.annotation.MetaData;
import lab.uue.core.service.BaseService;
import lab.uue.core.web.BaseController;
import lab.uue.sys.entity.PubPost;
import lab.uue.sys.service.AttachmentFileService;
import lab.uue.sys.service.PubPostService;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.rest.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;

@MetaData(title = "公告管理")
public class PubPostController extends BaseController<PubPost, String> {

    @Autowired
    private PubPostService pubPostService;

    @Autowired
    private AttachmentFileService attachmentFileService;

    @Override
    protected BaseService<PubPost, String> getEntityService() {
        return pubPostService;
    }

    @Override
    protected void checkEntityAclPermission(PubPost entity) {
        // TODO Add acl check code logic
    }

    @Override
    @MetaData(title = "创建")
    public HttpHeaders doCreate() {
        String fileId = this.getParameter("r2FileId");
        if (StringUtils.isNotBlank(fileId)) {
            bindingEntity.setR2File(attachmentFileService.findOne(fileId));
        }
        return super.doCreate();
    }

    public boolean isDisallowUpdate() {
        if (bindingEntity.getReadUserCount() != null && bindingEntity.getReadUserCount() > 0) {
            return true;
        }
        return false;
    }

    @Override
    @MetaData(title = "更新")
    public HttpHeaders doUpdate() {
        String fileId = this.getParameter("r2FileId");
        if (StringUtils.isNotBlank(fileId)) {
            bindingEntity.setR2File(attachmentFileService.findOne(fileId));
        } else {
            bindingEntity.setR2File(null);
        }
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
