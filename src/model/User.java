package model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import util.UserStateType;
import util.UserType;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer id;
	
	@Column(name="code")
	private String code;

	@Column(name = "account")
	private String account;

	@Column(name = "password")
	private String password;
	
	@Column(name ="name")
	private String name;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="state")
	private UserStateType state;
	
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="type")
	private UserType userType;
	
	
	@ManyToMany(cascade=CascadeType.ALL, mappedBy="users", fetch=FetchType.LAZY)
	private Set<Event> events = new HashSet<Event>();
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private Set<UserDetail> details = new HashSet<UserDetail>();
	
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private Set<Payment> payments = new HashSet<Payment>();

	
	
	public String getStringState(){
		switch(this.state){
		case NORMAL:
			return "normal";
		case INACTIVE:
			return "unactivated";
		case OVERDUE:
			return "unpaid";
		default:
			return "unknown";
		}
	}
	
	public String getStringType(){
		switch(this.userType){
		case SINGLE:
			return "personal";
		case FAMILY:
			return "family";
		default:
			return "unknown";
		}
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		if( !(obj instanceof User) ){
			return false;
		}
		
		int oid = ((User)obj).getId();
		if( this.id == oid ){
			return true;
		}
		
		return false;
	}

	@Override
	public int hashCode() {
		char[] chars = code.toCharArray();
		int sum = 0;
		
		for(char c: chars){
			sum += (int)c;
		}
		
		return id*31 + sum;
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserStateType getState() {
		return state;
	}

	public void setState(UserStateType state) {
		this.state = state;
	}
	

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Set<UserDetail> getDetails() {
		return details;
	}

	public void setDetails(Set<UserDetail> details) {
		this.details = details;
	}

	public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	public Set<Payment> getPayments() {
		return payments;
	}

	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}
	
	public int getPeopleNumber(){
		return details.size();
	}
}
