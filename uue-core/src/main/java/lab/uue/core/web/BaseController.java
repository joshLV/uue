package lab.uue.core.web;

import java.io.Serializable;

import lab.uue.core.entity.BaseEntity;

public abstract class BaseController<T extends BaseEntity<ID>, ID extends Serializable> extends
        PersistableController<T, ID> {

}
