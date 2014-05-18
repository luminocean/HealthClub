package model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="place")
public class Place {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="place_id")
	private Integer id;
	
	@Column(name="place")
	private String place;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="place")
	private Set<Occasion> occasions;
	
	
	public int getOccasionNumber(){
		return occasions.size();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public Set<Occasion> getOccasions() {
		return occasions;
	}
	public void setOccasions(Set<Occasion> occasions) {
		this.occasions = occasions;
	}
}
