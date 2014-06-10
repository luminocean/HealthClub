package logic.logicInterface;

import java.util.List;
import java.util.Set;

import model.Coach;
import model.Event;
import model.Place;

public interface EventService {
	
	public List<Event> getAllActiveEvents();
	
	/**
	 * @param account 用来标记该用户是否预约
	 * @return
	 */
	public List<Event> getAllActiveEvents(String account);

	public List<Event> getReservedEvents(String account);

	public List<Event> getReservableEvents(String account);
	
	public List<Event> getClosedEvents(String account);
	
	public List<Event> getAllClosedEvents();
	
	public Event getEvent(Integer eventId);
	
	
	/**
	 * 辅助接口
	 */
	public List<Coach> getCoaches();

	public List<Place> getPlaces();
	
	
	/**
	 * 预定活动
	 * @param account
	 * @param eventId
	 * @param reserveOption true为预订，false为取消
	 */
	public void reserve(String account, int eventId, boolean reserveOption);
	
	/**
	 * 提交活动！新建活动或者修改已有活动
	 * @param eventId
	 * @param title
	 * @param detail
	 * @param tags
	 * @param coaches
	 * @param places
	 * @param times
	 */
	public void submitEvent(int eventId, String title, String detail, String tags,
			List<String> coaches, List<String> places, List<String> times);	

}
