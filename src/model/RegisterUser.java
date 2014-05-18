package model;

import java.util.ArrayList;
import java.util.List;

import util.SexType;

public class RegisterUser {
	private String email;
	private String password;
	private String name;
	private String passwordConfirm;
	private List<String> guestNames = new ArrayList<String>();
	private List<Integer> ages = new ArrayList<Integer>();
	private List<String> sexes = new ArrayList<String>();
	private List<String> livingPlaces = new ArrayList<String>();
	
	
	public boolean isMatch() {
		if( password.equals(passwordConfirm) ){
			return true;
		}
		
		return false;
	}

	public List<UserDetail> getAssembledDetails() {
		List<UserDetail> details = new ArrayList<UserDetail>();
		
		for( int i=0; i<guestNames.size(); i++ ){
			UserDetail detail = new UserDetail();
			detail.setRealName(guestNames.get(i));
			detail.setAge(ages.get(i));
			detail.setLivingPlace(livingPlaces.get(i));
			
			if( sexes.get(i).equals("male")){
				detail.setSex(SexType.MALE);
			}else{
				detail.setSex(SexType.FEMALE);
			}
			
			details.add(detail);
		}
		
		return details;
	}
	
	public List<String> getGuestNames() {
		return guestNames;
	}

	public void setGuestNames(List<String> guestNames) {
		this.guestNames = guestNames;
	}

	public List<Integer> getAges() {
		return ages;
	}

	public void setAges(List<Integer> ages) {
		this.ages = ages;
	}

	public List<String> getLivingPlaces() {
		return livingPlaces;
	}

	public void setLivingPlaces(List<String> livingPlaces) {
		this.livingPlaces = livingPlaces;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public List<String> getSexes() {
		return sexes;
	}

	public void setSexes(List<String> sexes) {
		this.sexes = sexes;
	}
}
