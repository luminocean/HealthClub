package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="payment")
public class Payment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="user_id")
	private Integer userId;
	
	@Column(name="amount")
	private Double amount;
	
	@Column(name="time")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar time;

	
	
	public String getStringTime() {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		return format.format(time.getTime());
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Calendar getTime() {
		return time;
	}

	public void setTime(Calendar time) {
		this.time = time;
	}
}
