package  com.zihexin.business_interface.WebService;


import javax.jws.WebParam;
import javax.jws.WebService;


@WebService 
public interface PosFrontWebService  
{
	
	
    /**
     * 处理pos前置请求
     * @param marketCode
     * @param optUser
     * @param data
     * @param marketSign
     * @return
     */
    public  String  posFrontReq(@WebParam(name="marketCode") String marketCode,  @WebParam(name="optUser") String optUser,  @WebParam(name="data") String data,@WebParam(name="marketSign") String marketSign); 
    
   
}