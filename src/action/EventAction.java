package action;

import java.util.List;
import java.util.Map;

import logic.logicInterface.EventService;
import model.Event;
import model.User;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 负责事件查询的Action
 * @author luMinO
 *
 */
public class EventAction extends ActionSupport implements RequestAware,SessionAware{
	private EventService eventService;
	
	private Map<String, Object> requestMap;
	private Map<String, Object> sessionMap;
	
	

	public String getAllEventsForUser(){
		User user = (User)sessionMap.get("user");
		
		//实际获取的是当前还在进行的活动（因为还有已完结的活动）
		List<Event> events = eventService.getAllActiveEvents(user.getAccount());
		
		requestMap.put("allEvents", events);
		
		return "toAllEvents";
	}
	
	

	public String getAllEventsForServant(){
		List<Event> events = eventService.getAllActiveEvents();
		
		requestMap.put("allEvents", events);
		
		return "toAllEvents";
	}
	
	
	/**
	 * 获取预定过的活动
	 * @return
	 */
	public String getReservedActiveEvents(){
		User user = (User)sessionMap.get("user");
		
		List<Event> events = eventService.getReservedEvents(user.getAccount());
		
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

	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

}
