package action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import util.StaffType;
import logic.logicInterface.IUserService;
import model.Staff;
import model.User;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware{
	private IUserService userService;
	private Map<String, Object> session;
	
	private String email;
	private String password;
	
	
	@Override
	public String execute() throws Exception {
		User user = userService.getUser(email, password);
		
		if( user != null ){
			session.put("user", user);
			
			return "userSuccess";
		}
		
		Staff staff = userService.getStaff(email, password);
		
		if( staff != null ){
			session.put("staff", "");
			
			if( staff.getType() == StaffType.SERVANT ){
				return "servantSuccess";
			}
			if( staff.getType() == StaffType.MANAGER ){
				return "managerSuccess";
			}
		}
		
		//如果验证都没有过
		addFieldError("msg", "用户名或密码错误！");
		return LOGIN;
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
	public IUserService getUserService() {
		return userService;
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}


	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
