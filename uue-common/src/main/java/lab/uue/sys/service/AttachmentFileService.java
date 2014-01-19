package lab.uue.sys.service;

import lab.uue.core.dao.BaseDao;
import lab.uue.core.service.BaseService;
import lab.uue.sys.dao.AttachmentFileDao;
import lab.uue.sys.entity.AttachmentFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AttachmentFileService extends BaseService<AttachmentFile, String> {

    @Autowired
    private AttachmentFileDao attachmentFileDao;

    @Override
    protected BaseDao<AttachmentFile, String> getEntityDao() {
        return attachmentFileDao;
    }
}
