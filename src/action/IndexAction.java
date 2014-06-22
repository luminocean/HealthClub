package action;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import logic.logicInterface.EventService;
import logic.logicInterface.UserService;
import model.Event;
import model.User;

import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport implements RequestAware,SessionAware{
	private UserService userService;
	private EventService eventService;
	
	private Map<String, Object> request;
	private Map<String, Object> session;
	
	/**
	 * 准备用户主页数据
	 * @return
	 */
	public String getUserIndex(){
		User user = (User)session.get("user");

		List<Event> events = eventService.getAllActiveEvents(user.getAccount());
		if( events.size() == 0 ){
			events = eventService.getAllClosedEvents();
		}
						
		Random rand = new Random();
		int randNum = rand.nextInt(events.size());
		
		Event randomEvent = events.get(randNum);
		request.put("event", randomEvent);
		
		return SUCCESS;
	}
	
	
	/**
	 * 准备服务员主页数据
	 * @return
	 */
	public String getServantIndex(){
		return SUCCESS;
	}
	
	
	
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public EventService getEventService() {
		return eventService;
	}
	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}
}
