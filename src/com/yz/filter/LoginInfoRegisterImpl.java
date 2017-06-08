package com.yz.filter;

import cn.com.hnisi.authentication.client.ILoginInfoRegister;
import cn.com.hnisi.authentication.client.filter.CASFilterExtend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/6/6.
 */
public class LoginInfoRegisterImpl implements ILoginInfoRegister {

	public void regist(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		String userid = (String) request.getSession().getAttribute(CASFilterExtend.CAS_FILTER_USER);
		String usersfz = (String) request.getSession().getAttribute(CASFilterExtend.SSO_FILTER_USER_SFZ);

		request.getSession().setAttribute("usersfz",usersfz);



	}
}
