package action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import logic.logicInterface.EventService;
import logic.logicInterface.UserService;
import model.Event;
import model.ModifiedUser;
import model.User;

import com.opensymphony.xwork2.ActionSupport;

public class UserInfoAction extends ActionSupport implements SessionAware, RequestAware{
	private UserService userService;
	private EventService eventService;
	
	private Map<String, Object> session;
	private Map<String, Object> request;
	
	
	private String requestUserCode;
	
	private ModifiedUser modifiedUser;

	
	public String getUserInfo(){
		User user = (User) session.get("user");
		
		List<Event> closedEvents = eventService.getClosedEvents(user.getAccount());
		
		request.put("closedEvents", closedEvents);
		
		return "toUserInfo";
	}
	
	
	public String getServantUserInfo(){
		//初次请求，直接返回
		if( requestUserCode == null ){
			return "toUserInfo";
		}
		
		//否则开始获取用户信息
		User user = userService.getUser(requestUserCode);
		
		request.put("user", user);
		
		List<Event> reservedEvents = eventService.getReservedEvents(user.getAccount());
		request.put("reservedEvents", reservedEvents);
		
		List<Event> closedEvents = eventService.getClosedEvents(user.getAccount());
		request.put("closedEvents", closedEvents);
		
		List<Event> activeEvents = eventService.getAllActiveEvents(user.getAccount());
		request.put("activeEvents", activeEvents);
		
		return "toUserInfo";
	}
	
	
	/**
	 * 用户资料修改
	 * @return
	 */
	public String modifyInfo(){
		User user = (User) session.get("user");
		
		userService.modifyUser(user.getAccount(), modifiedUser);
		
		return "toUserInfo";
	}
	
	public String cancelMembership(){
		User user = (User) session.get("user");
		
		userService.cancelUser(user.getAccount());
		
		User newUser = userService.getUser(user.getAccount(), user.getPassword());
		
		if( newUser != null ){
			session.put("user", newUser);
		}
		
		return "toUserInfo";
	}


	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	public String getRequestUserCode() {
		return requestUserCode;
	}

	public void setRequestUserCode(String requestUserCode) {
		this.requestUserCode = requestUserCode;
	}

	public ModifiedUser getModifiedUser() {
		return modifiedUser;
	}

	public void setModifiedUser(ModifiedUser modifiedUser) {
		this.modifiedUser = modifiedUser;
	}

}
