package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import util.SexType;

@Entity
@Table(name="user_detail")
public class UserDetail {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="detail_id")
	private Integer id;
	
	@Column(name="real_name")
	private String realName;
	
	@Column(name="age")
	private Integer age;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="sex")
	private SexType sex = SexType.MALE;
	
	@Column(name="living_place")
	private String livingPlace;
	
	
	
	public String getStringSex(){
		switch(this.sex){
		case MALE:
			return "male";
		case FEMALE:
			return "female";
		}
		
		return "未知性别";
	}
	
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public SexType getSex() {
		return sex;
	}
	public void setSex(SexType sex) {
		this.sex = sex;
	}
	public String getLivingPlace() {
		return livingPlace;
	}
	public void setLivingPlace(String livingPlace) {
		this.livingPlace = livingPlace;
	}
	
}
