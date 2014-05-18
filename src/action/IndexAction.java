package action;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import logic.logicInterface.IEventService;
import logic.logicInterface.IUserService;
import model.Event;
import model.User;

import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport implements RequestAware,SessionAware{
	private IUserService userService;
	private IEventService eventService;
	
	private Map<String, Object> request;
	private Map<String, Object> session;
	
	/**
	 * 准备用户主页数据
	 * @return
	 */
	public String getUserIndex(){
		User user = (User)session.get("user");

		List<Event> events = eventService.getReservableEvents(user.getAccount());
						
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
	
	
	public IUserService getUserService() {
		return userService;
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public IEventService getEventService() {
		return eventService;
	}
	public void setEventService(IEventService eventService) {
		this.eventService = eventService;
	}
}
