package com.yz.action;

import com.yz.model.Pnotice;
import com.yz.model.UserRole;
import com.yz.service.PnoticeService;
import com.yz.util.AjaxMsgUtil;
import com.yz.util.ConvertUtil;
import com.yz.vo.AjaxMsgVO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("pnoticeAction")  
@Scope("prototype")
public class PnoticeAction extends BaseAction {

	private int id;
	@Resource
	private PnoticeService pnoticeService;
	private Pnotice pnotice;
	private List<Pnotice> pnotices;

	public String list() throws Exception {

		if (isNotBlankString(convalue)) {
			convalue = decodeAndReplaceBlank(convalue);
		}
		if (isNotBlankString(starttime)) {
			starttime = decodeAndReplaceBlank(starttime);
		}
		if (isNotBlankString(endtime)) {
			endtime = decodeAndReplaceBlank(endtime);
		}
		if (page < 1) {
			page = 1;
		}
		// 总记录数
		totalCount = pnoticeService.getTotalCount(con, convalue, currentUserRole,starttime,endtime);
		// 总页数
		pageCount = pnoticeService.getPageCount(totalCount, size);
		if (page > pageCount && pageCount != 0) {
			page = pageCount;
		}
		// 所有当前页记录对象
		pnotices = pnoticeService.queryList(con, convalue, currentUserRole, page, size,starttime,endtime);
		return "list";
	}


	public String goToAdd() {

		return "add";
	}

	public String add() throws Exception {

		pnotice.setUserRole(currentUserRole);
		pnoticeService.add(pnotice);

		arg[0] = "pnoticeAction!list";
		arg[1] = "通知管理";
		return "success_child";
	}

	public String delete() {

		pnoticeService.deleteById(id);
		arg[0] = "pnoticeAction!list";
		arg[1] = "通知管理";
		return SUCCESS;
	}
	
	
	public String deletePnotices() {
		
		int[] ids = ConvertUtil.StringtoInt(checkedIDs);
		for(int i=0;i<ids.length;i++)
		{
			pnoticeService.deleteById(ids[i]);
		}
		AjaxMsgUtil.outputJSONObjectToAjax(response,new AjaxMsgVO("删除成功."));
		return null;
	}
	
	
	public String load() throws Exception {

		pnotice = pnoticeService.loadById(id);// 当前修改通知的id
		/*
		 * 当前操作通知权限划分
		 */
		return "load";
	}

	public String update() throws Exception {

		pnotice.setUserRole(currentUserRole);
		pnoticeService.update(pnotice);
		arg[0] = "pnoticeAction!list";
		arg[1] = "通知管理";
		return "success_child";
	}


	public String view() {
		pnotice = pnoticeService.loadById(id);
		return "view";
	}


	// get、set-------------------------------------------
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PnoticeService getPnoticeService() {
		return pnoticeService;
	}

	public void setPnoticeService(PnoticeService pnoticeService) {
		this.pnoticeService = pnoticeService;
	}

	public Pnotice getPnotice() {
		return pnotice;
	}

	public void setPnotice(Pnotice pnotice) {
		this.pnotice = pnotice;
	}

	public List<Pnotice> getPnotices() {
		return pnotices;
	}

	public void setPnotices(List<Pnotice> pnotices) {
		this.pnotices = pnotices;
	}

	public void setCurrentUserRole(UserRole sessionCurrentUserRole) {
		this.currentUserRole = sessionCurrentUserRole;
	}
	
}
