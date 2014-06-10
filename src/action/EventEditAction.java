package action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import logic.logicInterface.EventService;
import model.Coach;
import model.Event;
import model.Place;

import com.opensymphony.xwork2.ActionSupport;

public class EventEditAction extends ActionSupport implements RequestAware{
	private EventService eventService;
	
	private Map<String, Object> request;
	
	private String title;
	private String detail;
	private String tags;
	private List<String> coaches = new ArrayList<String>();
	private List<String> places = new ArrayList<String>();
	private List<String> times = new ArrayList<String>();
	private Integer eventId = 0;
	
	
	public String editEvent(){
		//准备活动需要显示的教练和场地
		List<Coach> coaches = eventService.getCoaches();
		request.put("coaches", coaches);
		
		List<Place> places = eventService.getPlaces();
		request.put("places", places);
		
		Event eventToEdit = eventService.getEvent(eventId);
		request.put("event", eventToEdit);
		
		return "toEventEdit";
	}
	
	
	/**
	 * 提交活动
	 * @return
	 */
	public String submitEvent(){
		//若eventid=0,表示为新的活动； 否则则是更新活动内容
		eventService.submitEvent(eventId, title, detail, tags, coaches, places, times);
		
		System.out.println(detail);
		
		return "toAllEvents";
	}
	

	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}
	
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public List<String> getCoaches() {
		return coaches;
	}

	public void setCoaches(List<String> coaches) {
		this.coaches = coaches;
	}

	public List<String> getPlaces() {
		return places;
	}

	public void setPlaces(List<String> places) {
		this.places = places;
	}

	public List<String> getTimes() {
		return times;
	}

	public void setTimes(List<String> times) {
		this.times = times;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}


	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
	
}
