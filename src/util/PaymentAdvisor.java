package util;

import java.util.LinkedList;
import java.util.List;

import model.User;
import model.UserDetail;

public class PaymentAdvisor {
	public static double advice(User user){
		double sum = 0.0;
		
		UserStateType state = user.getState();
		UserType type = user.getUserType();
		
		//如果需要激活
		if( state == UserStateType.INACTIVE ){
			if( type == UserType.SINGLE ){
				sum += 75;
			}else{
				//家庭
				sum += 100;
			}
		}
		
		//支付月费用
		if( type == UserType.SINGLE ){
			sum += 40;
		}
		
		if( type == UserType.FAMILY ){
			sum += 55;
			
			List<UserDetail> details = new LinkedList<UserDetail>();
			
			details.addAll(user.getDetails());
			
			int teenagers=0;
			
			for( UserDetail detail: details ){
				int age = detail.getAge();
				
				if( age >= 10 && age <= 18){
					teenagers += 1;
				}
			}
			
			sum += (10*teenagers);
		}
		
		return sum;
	}
}
