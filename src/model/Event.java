package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="event")
public class Event {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="event_id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	
	@Column(name="detail")
	private String detail;
	
	@Transient
	private Boolean isReserved = false;
	
	@Column(name="closed")
	private Boolean isClosed = false;
	
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="event")
	private Set<Occasion> occasions = new HashSet<Occasion>();

	@ElementCollection
	@CollectionTable(name = "tag", joinColumns = @JoinColumn(name = "event_id"))
	@Column(name="tag", nullable=true)  
	private Set<String> tags = new HashSet<String>();
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name="reserve", joinColumns=@JoinColumn(name="event_id"), inverseJoinColumns=@JoinColumn(name="user_id"))
	private Set<User> users = new HashSet<User>();
	
	
	/**
	 * 获得最近的活动时间
	 */
	public String getLatestTime(){
		ArrayList<Occasion> list = new ArrayList<Occasion>();
		
		list.addAll(occasions);
		
		Collections.sort(list);
		
		Calendar now = Calendar.getInstance();
		
		for(Occasion occasion: list){
			if( occasion.getTime().compareTo(now) > 0 ){
				return occasion.getStringTime();
			}
		}
		
		return null;
	}
	
	
	public Calendar getNewestTime(){
		ArrayList<Occasion> list = new ArrayList<Occasion>();
		
		list.addAll(occasions);
		
		Collections.sort(list);
		
		Occasion newestOccasion = list.get(list.size()-1);
		
		return newestOccasion.getTime();
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public int getCurrentPeople() {
		int counter = 0;
		
		for(User user: users){
			counter += user.getDetails().size();
		}
		
		return counter;
	}

	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Set<Occasion> getOccasions() {
		return occasions;
	}
	public void setOccasions(Set<Occasion> occasions) {
		this.occasions = occasions;
	}
	public Set<String> getTags() {
		return tags;
	}
	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	public boolean isReserved() {
		return isReserved;
	}

	public void setReserved(boolean isReserved) {
		this.isReserved = isReserved;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Boolean isClosed() {
		return isClosed;
	}

	public void setClosed(Boolean isClosed) {
		this.isClosed = isClosed;
	}
	
}
