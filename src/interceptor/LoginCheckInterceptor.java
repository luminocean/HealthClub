package interceptor;

import java.util.Map;

import model.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LoginCheckInterceptor implements Interceptor {
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		Map<String, Object> sessionMap = actionInvocation.getInvocationContext().getSession();
		
		if( sessionMap.containsKey("user") ){
			User user = (User) sessionMap.get("user");
			if( user != null ){
				String result = actionInvocation.invoke();
				return result;
			}
		}
		
		if( sessionMap.containsKey("staff") ){
			String result = actionInvocation.invoke();
			return result;
		}
		
		Map<String, Object> request = (Map<String, Object>)actionInvocation.getInvocationContext().get("request");
		request.put("msg", "请先登录！");
		
		
		return "login";
	}
	

}
