package action;

import java.util.List;
import java.util.Map;

import logic.logicInterface.IEventService;
import model.Event;
import model.User;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class EventAction extends ActionSupport implements RequestAware,SessionAware{
	private IEventService eventService;
	
	private Map<String, Object> requestMap;
	private Map<String, Object> sessionMap;
	
	
	/**
	 * 获取用户得到的所有活动列表
	 * @return
	 */
	public String getUserAllEvents(){
		User user = (User)sessionMap.get("user");
		
		List<Event> events = eventService.getAllActiveEvents(user.getAccount());
		
		requestMap.put("allEvents", events);
		
		return "toAllEvents";
	}
	
	
	/**
	 * 获取服务员获得的所有活动列表
	 * @return
	 */
	public String getServantAllEvents(){
		List<Event> events = eventService.getAllActiveEvents();
		
		requestMap.put("allEvents", events);
		
		return "toAllEvents";
	}
	
	
	/**
	 * 获取未完结的、预定过的活动
	 * @return
	 */
	public String getReservedActiveEvents(){
		User user = (User)sessionMap.get("user");
		
		List<Event> events = eventService.getReservedActiveEvents(user.getAccount());
		
		requestMap.put("reservedEvents", events);
		
		return "toReservedEvents";
	}
	
	/**
	 * 获取所有完结的活动
	 * @return
	 */
	public String getClosedEvents(){
		List<Event> events = eventService.getAllClosedEvents();
		
		requestMap.put("closedEvents", events);
		
		return "toClosedEvents";
	}
	
	
	/**
	 * 前往预定新活动界面，准备数据
	 * @return
	 */
	public String reserveNewEvents(){
		User user = (User)sessionMap.get("user");
		
		List<Event> events = eventService.getReservableEvents(user.getAccount());
		
		requestMap.put("reservableEvents", events);
		
		return "toReservableEvents";
	}
	
	
	

	@Override
	public void setRequest(Map<String, Object> requestMap) {
		this.requestMap = requestMap;
	}
	
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public IEventService getEventService() {
		return eventService;
	}

	public void setEventService(IEventService eventService) {
		this.eventService = eventService;
	}

}
