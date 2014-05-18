package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport implements ServletRequestAware{
	private HttpServletRequest request;

	@Override
	public String execute() throws Exception {
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(1);
		
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
}
