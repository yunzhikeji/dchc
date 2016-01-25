package com.yz.filter;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.yz.model.UserRole;
/** session过期、登录有效性及操作的权限验证拦截器 */
public class LoginedCheckInterceptor extends AbstractInterceptor {
	/** 拦截请求并进行登录有效性验证 */
	public String intercept(ActionInvocation ai) throws Exception {
		//取得请求的URL
		String url = ServletActionContext.getRequest().getRequestURL().toString();
//		System.out.println("url:"+url);
		HttpServletResponse response=ServletActionContext.getResponse();
//		response.setHeader("Pragma","No-cache");          
//		response.setHeader("Cache-Control","no-cache");   
//		response.setHeader("Cache-Control", "no-store");   
//		response.setDateHeader("Expires",0);
		UserRole pubclient = null;
		//对登录与注销请求直接放行,不予拦截
		if (url.indexOf("front")!=-1 ||url.indexOf("jsp")!=-1 ||url.indexOf("toolkitAction!validateCode")!=-1 || url.indexOf("userAction!login")!=-1){
//			System.out.println("pass");
			return ai.invoke();
		}
		else{
			//验证Session是否过期
			if(!ServletActionContext.getRequest().isRequestedSessionIdValid()){
				//session过期,转向session过期提示页,最终跳转至登录页面
//				System.out.println("过期");
				return "opsessiongo";
			}
			else{
				pubclient = (UserRole)ServletActionContext.getRequest().getSession().getAttribute("user");
				//验证是否已经登录
				if (pubclient==null){
					//尚未登录,跳转至登录页面
//					System.out.println("过期null");
					return "opsessiongo";
				}else{
//					System.out.println("通过");
					return ai.invoke();
								
				}				
			}			
		}
	}
}

