package logic.logicImpl;

import java.util.Calendar;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import dao.daoInterface.EventDao;
import dao.daoInterface.UserDao;
import logic.logicInterface.EventService;
import model.Coach;
import model.Event;
import model.Occasion;
import model.Place;

public class EventServiceImpl implements EventService {
	private EventDao eventDao;
	
	/**
	 * Spring注入
	 * @param eventDao
	 */
	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}
	
	@Override
	public List<Event> getAllActiveEvents() {
		List<Event> events = eventDao.getAllActiveEvents();
		
		return events;
	}

	@Override
	public List<Event> getAllActiveEvents(String account) {
		List<Event> events = eventDao.getAllActiveEvents(account);
		
		return events;
	}

	@Override
	public List<Event> getReservedEvents(String account) {
		List<Event> events = eventDao.getReservedEvents(account);
		
		return events;
	}
	

	@Override
	public List<Event> getReservableEvents(String account) {
		List<Event> allActiveEvents = getAllActiveEvents(account);
		List<Event> reservableEvents = new LinkedList<Event>();
		
		for( Event event: allActiveEvents ){
			if( !event.isReserved()  ){
				reservableEvents.add(event);
			}
		}
		
		return reservableEvents;
	}

	@Override
	public void reserve(String account, int eventId, boolean reserveOption) {
		eventDao.reserve( account, eventId, reserveOption );
	}

	@Override
	public void settleEventState() {
		eventDao.settleEventState();
	}

	@Override
	public List<Event> getClosedEvents(String account) {
		return eventDao.getClosedEvents(account);
	}

	@Override
	public List<Coach> getCoaches() {
		return eventDao.getCoaches();
	}

	@Override
	public List<Place> getPlaces() {
		return eventDao.getPlaces();
	}

	
	
	private Calendar parseTime(String string) {
		Calendar cal = Calendar.getInstance();
		
		return cal;
	}

	@Override
	public Event getEvent(Integer eventId) {
		return eventDao.getEvent(eventId);
	}

	@Override
	public void submitEvent(int eventId, String title, String detail, String tags,
			List<String> coaches, List<String> places, List<String> times) {
		eventDao.submitEvent(eventId, title, detail, tags, coaches, places, times);
	}

	@Override
	public List<Event> getAllClosedEvents() {
		return eventDao.getAllClosedEvents();
	}
}
