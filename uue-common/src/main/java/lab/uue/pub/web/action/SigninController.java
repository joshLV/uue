package lab.uue.pub.web.action;

import lab.uue.core.context.KernelConfigParameters;

import org.apache.struts2.rest.RestActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 登录处理
 */
public class SigninController extends RestActionSupport {

    @Autowired
    private KernelConfigParameters kernelConfigParameters;

    public String execute() {
        return "/pub/signin";
    }

    public boolean isDevMode() {
        return kernelConfigParameters.isDevMode();
    }

}
