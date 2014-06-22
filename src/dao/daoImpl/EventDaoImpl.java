package dao.daoImpl;

import java.util.Calendar;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.orm.jpa.JpaTemplate;


import org.springframework.transaction.annotation.Transactional;

import model.Coach;
import model.Event;
import model.Occasion;
import model.Place;
import model.User;
import dao.daoInterface.EventDao;

public class EventDaoImpl implements EventDao{
	private EntityManagerFactory emf;
	private JpaTemplate jt;
	
	/**
	 * 接受Spring容器的注入
	 * @param daoHelper
	 */
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}
	public EntityManagerFactory getEmf() {
		return emf;
	}
	
	
	/**
	 * 获取JpaTemplate
	 * @return
	 */
	private JpaTemplate getJpaTemplate(){
		if( jt == null ) return new JpaTemplate(emf);
		return jt;
	}

	

	@Override
	public List<Event> getAllActiveEvents(String account) {
		List<Event> events = getAllActiveEvents();
		
		for( Event event: events ){
			Set<User> users = event.getUsers();
			
			for( User user: users){
				if( user.getAccount().equals(account) ){
					event.setReserved(true);
				}
			}
		}
		
		return events;
	}
	
	

	@Override
	public List<Event> getAllActiveEvents() {
		@SuppressWarnings("unchecked")
		List<Event> events = (List<Event>)getJpaTemplate()
				.find("select event from Event as event");
		
		List<Event> activeEvents = new LinkedList<Event>();
		
		for( Event event: events ){
			if( !event.isClosed() ){
				activeEvents.add(event);
			}
		}
		
		return activeEvents;
	}

	
	@Override
	public List<Event> getReservedEvents(String account) {
		List<Event> reservedEvents = new LinkedList<Event>();
		
		@SuppressWarnings("unchecked")
		List<Event> events = (List<Event>)getJpaTemplate()
				.find("select user.events from User user where user.account=?1", account);
		

		//由于查询出来的活动里面是没有预约的信息的，因此需要手工设置
		//同时排除已经过期的活动
		for(Event event: events){
			if( !event.isClosed() ){
				event.setReserved(true);
				reservedEvents.add(event);
			}
		}
		
		return reservedEvents;
	}
	
	
	@Override
	public void reserve(String account, int eventId, boolean reserveOption) {
		//防止返回空List后调用出错
		try{
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			
			Query query = em.createQuery("select user from User user where user.account=?1");
			query.setParameter(1, account);
			
			List<User> users = query.getResultList();
			User user = users.get(0);
			
			
			query = em.createQuery("select event from Event event where event.id=?1");
			query.setParameter(1, eventId);
			
			List<Event> events = query.getResultList();
			Event event = events.get(0);
			
			//至此相关的User 和 Event都已经获取到了
			
			if( reserveOption == true ){
				event.getUsers().add(user);
			}else{
				Set<User> usersInvolved = event.getUsers();
				
				//如果不包含就表示该用户根本就没预定
				if( !usersInvolved.contains(user) ){
					return;
				}
				
				usersInvolved.remove(user);
			}
			
			
			em.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	public List<Event> getClosedEvents(String account) {
		@SuppressWarnings("unchecked")
		List<Event> events = (List<Event>)getJpaTemplate()
				.find("select user.events from User as user where user.account = ?1", account);
		
		List<Event> closedEvents = new LinkedList<Event>();
		
		for( Event event: events ){
			if( event.isClosed() ){
				closedEvents.add(event);
			}
		}
		
		return closedEvents;
	}
	
	
	@Override
	public List<Event> getAllClosedEvents() {
		@SuppressWarnings("unchecked")
		List<Event> events = (List<Event>)getJpaTemplate()
				.find("select event from Event event");
		
		List<Event> closedEvents = new LinkedList<Event>();
		
		for( Event event: events ){
			if( event.isClosed() ){
				closedEvents.add(event);
			}
		}
		
		return closedEvents;
	}
	
	@Override
	public List<Coach> getCoaches() {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("select coach from Coach coach");
		
		List<Coach> coaches = query.getResultList();
		
		return coaches;
	}
	@Override
	public List<Place> getPlaces() {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("select place from Place place");
		
		List<Place> places = query.getResultList();
		
		return places;
	}
	
	@Override
	public void addEvent(Event rawEvent) {
		Set<Occasion> rawOccasions = rawEvent.getOccasions();
		
		Set<Occasion> realOccasions = new HashSet<Occasion>();
		
		for(Occasion rawOccasion: rawOccasions){
			Calendar cal = rawOccasion.getTime();
			
			Coach rawCouch = rawOccasion.getCoach();
			Place rawPlace = rawOccasion.getPlace();
			
			Coach realCoach = getCoach(rawCouch.getName());
			Place realPlace = getPlace(rawPlace.getPlace());
			
			Occasion realOccasion = new Occasion();
			realOccasion.setCoach(realCoach);
			realOccasion.setPlace(realPlace);
			realOccasion.setTime(cal);
			
			realOccasions.add(realOccasion);
		}
		
		rawEvent.setOccasions(realOccasions);
		
		Event realEvent = rawEvent;

		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(realEvent);
		
		em.getTransaction().commit();
	}
	
	private Coach getCoach(String name){
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("select coach from Coach coach where coach.name=?1");
		query.setParameter(1, name);
		
		List<Coach> coaches = query.getResultList();
		
		if( coaches.size()> 0 ){
			return coaches.get(0);
		}
		
		return null;
	}
	
	private Place getPlace(String place){
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("select place from Place place where place.place=?1");
		query.setParameter(1, place);
		
		List<Place> places = query.getResultList();
		
		if( places.size()> 0 ){
			return places.get(0);
		}
		
		return null;
	}
	
	@Override
	public Event getEvent(Integer eventId) {
		EntityManager em = emf.createEntityManager();

		Event event = em.find(Event.class, eventId);
		
		return event;
	}
	
	
	
	
	@Override
	public void submitEvent(int eventId, String title, String detail, String tags,
			List<String> coachesStr, List<String> placesStr, List<String> timesStr) {
		
		boolean isNew = true;
		if( eventId > 0 ){
			isNew = false;
		}
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Event event = null;
		
		if( !isNew ){
			event = em.find(Event.class, eventId);
		}else{
			event = new Event();
		}
		
		event.setTitle(title);
		event.setDetail(detail);
		event.setTags(splitToSet(tags));
		
		
		if( !isNew ){
			em.flush();
		}else{
			Set<Occasion> occasions = makeOccasions(coachesStr, placesStr, timesStr, event);
			event.setOccasions(occasions);
			em.merge(event);
			em.flush();
		}

		em.getTransaction().commit();
	}
	
	
	/**
	 * 根据传入的列表创建occasion组
	 * @param coachesStr
	 * @param placesStr
	 * @param timesStr
	 * @param event 
	 * @return
	 */
	private Set<Occasion> makeOccasions(List<String> coachesStr, 
			List<String> placesStr, List<String> timesStr, Event event){
		
		Set<Occasion> occasions = new HashSet<Occasion>();

		// 以下依此添加各个occasion内容
		int occasionNumber = coachesStr.size();

		for (int i = 0; i < occasionNumber; i++) {
			Occasion occasion = new Occasion();

			// 获取已经存在的教练与地点
			String coachStr = coachesStr.get(i);
			String placeStr = placesStr.get(i);
			
			Coach coach = getCoach(coachStr);
			Place place = getPlace(placeStr);

			occasion.setCoach(coach);
			occasion.setPlace(place);
			occasion.setTime(parseTime(timesStr.get(i)));
			
			occasion.setEvent(event);

			occasions.add(occasion);
		}

		return occasions;
	}
	
	
	
	private Set<String> splitToSet(String tags) {
		String[] splits = tags.split(" ");
		
		Set<String> tagSet = new HashSet<String>();
		
		for(String str: splits){
			tagSet.add(str);
		}
		
		return tagSet;
	}
	
	/**
	 * 把字符串类型的时间转化成时间类
	 * @param string
	 * @return
	 */
	public Calendar parseTime(String timeStr) {
		String[] dateAndTime = timeStr.split(" ");
		
		String date = dateAndTime[0];
		String time = dateAndTime[1];
		
		String[] datePiece = date.split("/");
		int year = Integer.parseInt(datePiece[0]);
		int month = Integer.parseInt(datePiece[1])-1;
		int day = Integer.parseInt(datePiece[2]);
		
		String[] timePiece = time.split(":");
		int hour = Integer.parseInt(timePiece[0]);
		int minute = Integer.parseInt(timePiece[1]);
		
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day, hour, minute);
		
		return cal;
	}
	

	

}
