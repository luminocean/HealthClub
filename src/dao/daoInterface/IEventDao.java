package dao.daoInterface;

import java.util.List;
import java.util.Set;

import model.Coach;
import model.Event;
import model.Place;

public interface IEventDao {
	public List<Event> getAllActiveEvents();

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
