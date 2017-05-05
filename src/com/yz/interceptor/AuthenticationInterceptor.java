package com.yz.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.yz.model.UserRole;
import com.yz.service.UserRoleAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by Administrator on 2017/4/21.
 */

@Component("authenticationInterceptor")
public class AuthenticationInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {

	
		Map session = actionInvocation.getInvocationContext().getSession();
		UserRole userRole = (UserRole) session.get("currentUserRole");
		if ( null != userRole) {
			Object object = actionInvocation.getAction();
			if (object instanceof UserRoleAware) {
				UserRoleAware action = (UserRoleAware) object;
				action.setCurrentUserRole(userRole);
			}
			return actionInvocation.invoke();
		} else {
			return "adminLogin";
		}
	}
}
