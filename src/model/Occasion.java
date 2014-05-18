package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="occasion")
public class Occasion implements Comparable<Occasion>{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="occasion_id")
	private int id;
	
	@Column(name="time")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar time;
	
	
	@ManyToOne(optional=false, cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="place_id", nullable=false)
	private Place place;
	

	@ManyToOne(optional=false, cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="coach_id", nullable=false)
	private Coach coach;
	
	
	@ManyToOne(optional=false, cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="event_id", nullable=true)
	private Event event;
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Calendar getTime() {
		return time;
	}

	public void setTime(Calendar time) {
		this.time = time;
	}


	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public Coach getCoach() {
		return coach;
	}

	public void setCoach(Coach coach) {
		this.coach = coach;
	}

	@Override
	public int compareTo(Occasion o2) {
		return time.compareTo(o2.time);
	}
	
	public String getStringTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		return format.format(time.getTime());
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
}
