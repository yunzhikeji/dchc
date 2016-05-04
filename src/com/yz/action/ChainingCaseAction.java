package com.yz.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.yz.model.Injurycase;
import com.yz.service.IInjurycaseService;

@Component("chainingCaseAction")
@Scope("prototype")
public class ChainingCaseAction extends ActionSupport implements RequestAware,
SessionAware, ServletResponseAware, ServletRequestAware{
	@Resource
	private IInjurycaseService injurycaseService;
	
	private Injurycase injurycase;
	/** 列表 */
	public String list() throws Exception {
		List<Injurycase> injurycaselist  = injurycaseService.getInjurycases();
		
		
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		return "toList";
	}

	/** 添加页面 */
	public String addPage() throws Exception {
		return "addPage";
	}

	/** 添加 */
	public String add() throws Exception {
		return "toList";
	}

	/** 修改页面 */
	public String editPage() throws Exception {
		return "editPage";
	}

	/** 修改 */
	public String edit() throws Exception {
		return "toList";
	}

	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}

	public Injurycase getInjurycase() {
		return injurycase;
	}

	public void setInjurycase(Injurycase injurycase) {
		this.injurycase = injurycase;
	}


}
