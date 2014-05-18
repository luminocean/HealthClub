package action;

import java.util.Map;

import logic.logicInterface.IUserService;
import model.User;

import org.apache.struts2.interceptor.SessionAware;

import util.UserType;

import com.opensymphony.xwork2.ActionSupport;

public class PaymentAction extends ActionSupport implements SessionAware{
	private String creditCardAccount;
	private Integer months;
	
	private IUserService userService;
	private Map<String, Object> session;
	
	

	public String recoverMembership(){
		return payMembership();
	}
	
	
	public String activateMembership(){
		return payMembership();
	}
	
	
	/**
	 * 支付会员或激活费用
	 * @return
	 */
	public String payMembership(){
		User user = (User) session.get("user");
		
		//银行扣款
		boolean isSuccess = userService.payForMembership(user, creditCardAccount);
		
		if( !isSuccess ){
			addFieldError("msg", "金额不足！");
			return "toUserInfo";
		}
		
		//扣款完成后进行用户激活或恢复
		userService.recoverMembership(user.getAccount());
		User currentUser = (User)session.get("user");
		
		User newUser = userService.getUser(currentUser.getAccount(), currentUser.getPassword());
		
		if( newUser != null ){
			session.put("user", newUser);
		}
		
		return "toUserInfo";
	}
	
	

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String getCreditCardAccount() {
		return creditCardAccount;
	}

	public void setCreditCardAccount(String creditCardAccount) {
		this.creditCardAccount = creditCardAccount;
	}

	public Integer getMonths() {
		return months;
	}

	public void setMonths(Integer months) {
		this.months = months;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
}
