package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import util.StaffType;

@Entity
@Table(name="staff")
public class Staff {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="staff_id")
	private Integer id;
	
	@Column(name="account")
	private String account;
	
	@Column(name="password")
	private String password;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="type")
	private StaffType type;
	
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
	public StaffType getType() {
		return type;
	}
	public void setType(StaffType type) {
		this.type = type;
	}
}
