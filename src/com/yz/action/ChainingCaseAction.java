package com.yz.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.yz.model.Case;
import com.yz.service.CaseService;

@Component("chainingCaseAction")
@Scope("prototype")
public class ChainingCaseAction extends ActionSupport implements ModelDriven<Case>{
	@Resource
	private CaseService caseService;
	
	private Case model = new Case();
	/** 列表 */
	public String list() throws Exception {
		List<Case> caseList = caseService.findAll();
		ActionContext.getContext().put("caseList", caseList);
		
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

	public Case getModel() {
		// TODO Auto-generated method stub
		return model;
	}


}
