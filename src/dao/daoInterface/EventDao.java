package dao.daoInterface;

import java.util.List;
import java.util.Set;

import model.Coach;
import model.Event;
import model.Place;

public interface EventDao {
	public List<Event> getAllActiveEvents();


	/**
	 * 获取所有当前未失效的活动
	 * @param account 标记对于该用户是否已经预约该活动
	 * @return
	 */
	public List<Event> getAllActiveEvents(String account);

	public List<Event> getReservedEvents(String account);

	public void reserve(String account, int eventId, boolean reserveOption);

	public void settleEventState();

	public List<Event> getClosedEvents(String account);

	public List<Coach> getCoaches();

	public List<Place> getPlaces();

	public void addEvent(Event rawEvent);
	
	public Event getEvent(Integer eventId);

	public void submitEvent(int eventId, String title, String detail, String tags, List<String> coaches,
			List<String> places, List<String> times);

	public List<Event> getAllClosedEvents();
}
