package dao.daoImpl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.hibernate.SQLQuery;

import model.Coach;
import model.Place;
import dao.daoInterface.IStatisticDao;

public class StatisticDaoImpl implements IStatisticDao {
	private EntityManagerFactory emf;

	
	public EntityManagerFactory getEmf() {
		return emf;
	}

	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public List<Place> getPlaceListInOrder() {
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Query query = em.createQuery("select place from Place place order by place.occasions.size desc");
		
		List<Place> places = query.getResultList();
		
		em.getTransaction().commit();
		
		
		return places;
	}

	@Override
	public List<Coach> getCoachListInOrder() {
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Query query = em.createQuery("select coach from Coach coach order by coach.occasions.size desc");
		
		List<Coach> coaches = query.getResultList();
		
		em.getTransaction().commit();
		
		return coaches;
	}

	@Override
	public Map<String, Integer> getLivingPlaceMap() {
		Map<String, Integer> placeMap = new HashMap<String, Integer>();
		
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Query query = em.createQuery("select ud.livingPlace, count(*) from UserDetail as ud group by ud.livingPlace order by count(*)");
		
		
		List rows = query.getResultList();
				
		for( int i=0; i<rows.size(); i++ ){
			Object row = rows.get(i);
			Object[] rowPieces = (Object[])row;
			
			String place = (String)rowPieces[0];
			
			long counter = (long)rowPieces[1];
			
			int value = (int)counter;
			
			placeMap.put(place, value);
		}
		
		em.getTransaction().commit();

		return placeMap;
	}

	@Override
	public List<Integer> getPeopleDataInMonths(int pastMonths) {
		List<Integer> pastData = new LinkedList<Integer>();
		
		
		Calendar cal = Calendar.getInstance();
		cal.set(2014, 1, 10);
		
		for( int i=0; i<pastMonths; i++ ){
			int lastMonthCount = countPeopleInLastMonth(cal);

			pastData.add(lastMonthCount);
			
			cal.add(Calendar.MONTH, 1);
		}
		

		return pastData;
	}
	
	
	private int countPeopleInLastMonth(Calendar c){
		Calendar current = c;
		Calendar lastMonth = (Calendar) current.clone();
		lastMonth.roll(Calendar.MONTH, false);
		
		String currentStr = timeFormat(current);
		String lastMonthStr = timeFormat(lastMonth);

		
		EntityManager em = emf.createEntityManager();

		long counter = 0;
		
		try{
			
			Query query = em.createQuery("select sum(o.event.users.size) from Occasion as o where o.time between ?1 and ?2");
			query.setParameter(1, lastMonth);
			query.setParameter(2, current);
			
			List list = query.getResultList();
			
			if( list.size()>0 && list.get(0) != null ){
				counter = (long)list.get(0);
			}
			
		}catch(Exception  e){
			e.printStackTrace();
		}
		
		em.close();
		
		int result = (int)counter;
		
		return result;
	}
	
	private String timeFormat(Calendar c){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(c.getTime());
	}

	@Override
	public String getHotTag() {
		String hotTag = null;
		
		EntityManager em = emf.createEntityManager();
		
		Query query = em.createNativeQuery("select tag from event e, tag t, occasion o "
				+ "where o.event_id=e.event_id and e.event_id = t.event_id "
				+ "group by tag order by count(*) desc;");
		
		List result = query.getResultList();

		if( result.size() > 0 ){
			String tag = (String)result.get(0);
			
			hotTag = tag;
		}
		
		return hotTag;
	}
	
}
