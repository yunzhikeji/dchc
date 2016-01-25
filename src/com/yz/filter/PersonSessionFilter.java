package com.yz.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yz.model.UserRole;

public class PersonSessionFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse res=(HttpServletResponse)response;
		res.setContentType("text/html;charset=utf-8");         
		req.setCharacterEncoding("utf-8");
		UserRole ps=(UserRole) req.getSession().getAttribute("user");
		// 得到用户请求的URI        
		String request_uri = req.getRequestURI();         
		// 得到web应用程序的上下文路径         
		String ctxPath = req.getContextPath();         
		// 去除上下文路径，得到剩余部分的路径         
		String uri = request_uri.substring(ctxPath.length()); 
//		if(ps!=null||uri.startsWith("/login")||uri.startsWith("/skin")||uri.startsWith("/js")||uri.endsWith("/")){
		if(ps==null){
			System.out.println("会话失效:"+uri);
			String inval="会话失效请重新登录";
			req.setAttribute("loginFail", inval);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
			
		}else{
			System.out.println("通过:"+uri);
			chain.doFilter(request, response);
		}
		
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
