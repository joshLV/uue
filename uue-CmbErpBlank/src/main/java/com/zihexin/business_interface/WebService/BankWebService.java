package  com.zihexin.business_interface.WebService;


import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 发布接口服务类
 *
 *
 */
@WebService 
public interface BankWebService  
{
	
	
    /**
     * 发布接口服务
     * @param marketCode
     * @param optUser
     * @param data
     * @param marketSign
     * @return
     */
    public  String  WebService(@WebParam(name="marketCode") String marketCode,  @WebParam(name="optUser") String optUser,  @WebParam(name="data") String data,@WebParam(name="marketSign") String marketSign); 
    
    
}