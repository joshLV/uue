package lab.uue.pub.web.action;

import lab.uue.core.context.KernelConfigParameters;

import org.apache.struts2.rest.RestActionSupport;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 公共数据服务处理
 */
public class DataController extends RestActionSupport {

    @Autowired
    private KernelConfigParameters kernelConfigParameters;


}
