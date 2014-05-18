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
@Table(name="coach")
public class Coach {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="coach_id")
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="coach")
	private Set<Occasion> occasions;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Occasion> getOccasions() {
		return occasions;
	}
	public void setOccasions(Set<Occasion> occasions) {
		this.occasions = occasions;
	}
}
