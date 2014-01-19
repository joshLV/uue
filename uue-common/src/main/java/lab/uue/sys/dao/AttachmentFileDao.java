package lab.uue.sys.dao;

import java.util.List;

import lab.uue.core.dao.BaseDao;
import lab.uue.sys.entity.AttachmentFile;

import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentFileDao extends BaseDao<AttachmentFile, String> {
    List<AttachmentFile> findByEntityClassNameAndEntityId(String entityClassName, String entityId);
}
