package action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import util.RegisterType;
import logic.logicInterface.IUserService;
import model.RegisterUser;
import model.User;
import model.UserDetail;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

public class RegisterAction extends ActionSupport implements SessionAware{
	private IUserService userService;
	
	
	private RegisterUser registerUser;
	
	private String email;
	private String password;
	
	private Map<String, Object> session;
	
	@Override
	public String execute() throws Exception {
		//如果两次密码匹配
		if( registerUser.isMatch()){	
			RegisterType result = userService.register(registerUser);

			//如果登录成功转发登录请求
			if( result == RegisterType.SUCCESS ){
				email = registerUser.getEmail();
				password = registerUser.getPassword();
				
				return LOGIN;
			}
			
			if( result == RegisterType.EXISTED ){
				addFieldError("msg", "用户名已经存在！");
				return INPUT;
			}
			
			addFieldError("msg", "未知问题！");
			return INPUT;
		}
		
		//若两次密码并不匹配
		addFieldError("msg", "两次密码必须相同！");
		return INPUT;
	}
	


	/**
	 * 根据页面传来的参数组装用户详细类
	 * @return
	 */
	/*private List<UserDetail> assembleDetails() {
		List<UserDetail> details = new ArrayList<UserDetail>();
		
		for( int i=0; i<guestNames.size(); i++ ){
			UserDetail detail = new UserDetail();
			detail.setRealName(guestNames.get(i));
			detail.setAge(ages.get(i));
			detail.setLivingPlace(livingPlaces.get(i));
			
			details.add(detail);
		}
		
		return details;
	}*/

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	public Map<String, Object> getSession() {
		return session;
	}
	
	
	public IUserService getUserService() {
		return userService;
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public RegisterUser getRegisterUser() {
		return registerUser;
	}

	public void setRegisterUser(RegisterUser registerUser) {
		this.registerUser = registerUser;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
