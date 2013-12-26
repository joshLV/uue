package lab.s2jh.pub.web.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import lab.s2jh.auth.service.UserService;
import lab.s2jh.core.security.AuthContextHolder;
import lab.s2jh.core.security.AuthUserDetails;
import lab.s2jh.sys.service.MenuService;
import lab.s2jh.sys.vo.NavMenuVO;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.apache.struts2.rest.RestActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 全局布局处理
 */
@Namespace("/")
public class LayoutController extends RestActionSupport implements ModelDriven<Object> {

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserService userService;

    @Value("${cfg.system.title}")
    private String systemTitle;

    private ObjectMapper mapper = new ObjectMapper();

    private Object model;

    public String getSystemTitle() {
        return systemTitle;
    }

    public AuthUserDetails getAuthUserDetails() {
        return AuthContextHolder.getAuthUserDetails();
    }

    public HttpHeaders index() {
        return new DefaultHttpHeaders("/layout/index").disableCaching();
    }

    public HttpHeaders menu() throws JsonProcessingException {
        HttpServletRequest request = ServletActionContext.getRequest();
        List<NavMenuVO> menus = menuService.authUserMenu(AuthContextHolder.getAuthUserDetails().getAuthorities(),
                request.getContextPath());
        request.setAttribute("rootMenus", menus);
        request.setAttribute("menuJsonData", mapper.writeValueAsString(menus));
        return new DefaultHttpHeaders("/layout/menu").disableCaching();
    }

    public HttpHeaders welcome() {
        return new DefaultHttpHeaders("/layout/welcome").disableCaching();
    }

    @Override
    public Object getModel() {
        return model;
    }
    
    public void setModel(Object model) {
		this.model = model;
	}

	/**
	 * 获取菜单数据
	 * @return
	 * @throws JsonProcessingException
	 */
	public HttpHeaders menuNew() throws JsonProcessingException{
		HttpServletRequest request = ServletActionContext.getRequest();
		Set<GrantedAuthority> authories = new HashSet<GrantedAuthority>();
		authories.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
		List<NavMenuVO> menus = menuService.authUserMenu(authories, request.getContextPath());
		request.setAttribute("rootMenus", menus);
		request.setAttribute("menuJsonData", mapper.writeValueAsString(menus));
		setModel(menus);
		return new DefaultHttpHeaders().disableCaching();
	}

}
