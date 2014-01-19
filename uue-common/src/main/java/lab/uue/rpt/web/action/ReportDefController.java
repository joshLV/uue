package lab.uue.rpt.web.action;

import java.util.List;
import java.util.Set;

import lab.uue.auth.entity.Role;
import lab.uue.auth.service.RoleService;
import lab.uue.core.annotation.MetaData;
import lab.uue.core.pagination.GroupPropertyFilter;
import lab.uue.core.service.BaseService;
import lab.uue.core.service.R2OperationEnum;
import lab.uue.core.web.BaseController;
import lab.uue.core.web.annotation.SecurityControllIgnore;
import lab.uue.core.web.view.OperationResult;
import lab.uue.rpt.entity.ReportDef;
import lab.uue.rpt.entity.ReportDefR2Role;
import lab.uue.rpt.service.ReportDefService;
import lab.uue.sys.service.AttachmentFileService;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.rest.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

@MetaData(title = "报表定义管理")
public class ReportDefController extends BaseController<ReportDef, String> {

    @Autowired
    private ReportDefService reportDefService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AttachmentFileService attachmentFileService;

    @Override
    protected BaseService<ReportDef, String> getEntityService() {
        return reportDefService;
    }

    @Override
    protected void checkEntityAclPermission(ReportDef entity) {
        // According to URL access control logic
    }

    @Override
    public void prepareCreate() {
        super.prepareCreate();
        bindingEntity.setCode("RPT_" + RandomStringUtils.randomNumeric(6));
    }

    @Override
    @MetaData(title = "创建")
    public HttpHeaders doCreate() {
        String templateFileId = this.getParameter("templateFileId");
        if (StringUtils.isNotBlank(templateFileId)) {
            bindingEntity.setTemplateFile(attachmentFileService.findOne(templateFileId));
        }
        return super.doCreate();
    }

    @Override
    @MetaData(title = "更新")
    public HttpHeaders doUpdate() {
        String templateFileId = this.getParameter("templateFileId");
        if (StringUtils.isNotBlank(templateFileId)) {
            bindingEntity.setTemplateFile(attachmentFileService.findOne(templateFileId));
        } else {
            bindingEntity.setTemplateFile(null);
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

    public List<String> getCategories() {
        return reportDefService.findCategories();
    }

    @MetaData(title = "计算显示角色关联数据")
    @SecurityControllIgnore
    public HttpHeaders findRelatedRoles() {
        GroupPropertyFilter groupFilter = GroupPropertyFilter.buildGroupFilterFromHttpRequest(Role.class, getRequest());
        List<Role> roles = roleService.findByFilters(groupFilter, new Sort(Direction.DESC, "aclType", "code"));
        List<ReportDefR2Role> r2s = reportDefService.findRelatedRoleR2s(this.getId());
        for (Role role : roles) {
            role.addExtraAttribute("related", false);
            for (ReportDefR2Role r2 : r2s) {
                if (r2.getRole().equals(role)) {
                    role.addExtraAttribute("r2CreatedDate", r2.getCreatedDate());
                    role.addExtraAttribute("related", true);
                    break;
                }
            }
        }
        setModel(buildPageResultFromList(roles));
        return buildDefaultHttpHeaders();
    }

    @MetaData(title = "更新角色关联")
    public HttpHeaders doUpdateRelatedRoleR2s() {
        Set<String> roleIds = this.getParameterIds("r2ids");
        R2OperationEnum op = Enum.valueOf(R2OperationEnum.class, getParameter("op", R2OperationEnum.update.name()));
        reportDefService.updateRelatedRoleR2s(this.getId(), roleIds, op);
        setModel(OperationResult.buildSuccessResult(op.getLabel() + "操作完成"));
        return buildDefaultHttpHeaders();
    }
}