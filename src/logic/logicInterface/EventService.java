package logic.logicInterface;

import java.util.List;
import java.util.Set;

import model.Coach;
import model.Event;
import model.Place;

public interface EventService {
	/**
	 * 获取活动相关
	 * @return
	 */
	public List<Event> getAllActiveEvents();
	
	public List<Event> getAllActiveEvents(String account);

	public List<Event> getReservedEvents(String account);

	public List<Event> getReservedActiveEvents(String account);

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
	 * @param reserveOption
	 */
	public void reserve(String account, int eventId, boolean reserveOption);
	
	

	public void submitEvent(int eventId, String title, String detail, String tags,
			List<String> coaches, List<String> places, List<String> times);	
	/**
	 * 活动状态结算
	 */
	public void settleEventState();

	
}
