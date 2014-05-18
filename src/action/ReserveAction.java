package action;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.usertype.UserType;

import util.UserStateType;
import logic.logicInterface.IEventService;
import model.User;

import com.opensymphony.xwork2.ActionSupport;

public class ReserveAction extends ActionSupport implements RequestAware,SessionAware{
	private IEventService eventService;
	
	private Map<String, Object> requestMap;
	private Map<String, Object> sessionMap;
	
	private boolean toReserve = false;
	private Integer eventId;
	
	/**
	 * 活动的预约/取消预约
	 * @return
	 */
	public String reserve(){
		User user = (User)sessionMap.get("user");
		
		if( user.getState() == UserStateType.INACTIVE ){
			addActionError("对不起请先激活您的账户！");
			return "toReservedEventsAlert";
		}
		
		if( user.getState() == UserStateType.OVERDUE ){
			addActionError("对不起您已欠费，请先续费！");
			return "toReservedEventsAlert";
		}
		
		eventService.reserve( user.getAccount(), eventId, toReserve );
		
		
		return "toReservedEvents";
	}
	
	
	public boolean isToReserve() {
		return toReserve;
	}

	public void setToReserve(boolean toReserve) {
		this.toReserve = toReserve;
	}
	
	@Override
	public void setRequest(Map<String, Object> requestMap) {
		this.requestMap = requestMap;
	}
	
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public void setEventService(IEventService eventService) {
		this.eventService = eventService;
	}

}
