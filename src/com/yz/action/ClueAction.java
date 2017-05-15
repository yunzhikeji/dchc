package com.yz.action;

import com.yz.model.Clue;
import com.yz.model.UserRole;
import com.yz.service.ClueService;
import com.yz.util.AjaxMsgUtil;
import com.yz.util.ConvertUtil;
import com.yz.util.DateTimeKit;
import com.yz.vo.AjaxMsgVO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component("clueAction")
@Scope("prototype")
public class ClueAction extends BaseAction {

	@Resource
	private ClueService clueService;
	private int ctype;// 线索类型 1：刑侦线索 2：普通线索
	private Clue clue;
	private List<Clue> clues;


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

		pageTileName = selectTileName(ctype);

		// 总记录数
		totalCount = clueService.getTotalCount(con, convalue, currentUserRole, ctype,
				queryState, starttime, endtime);
		// 总页数
		pageCount = clueService.getPageCount(totalCount, size);
		if (page > pageCount && pageCount != 0) {
			page = pageCount;
		}
		// 所有当前页记录对象
		clues = clueService.queryList(con, convalue, currentUserRole, page, size,
				ctype, queryState, starttime, endtime);

		return "list";
	}


	public String goToAdd() {
		pageTileName = selectTileName(ctype);
		return "add";
	}

	public String add() throws Exception {

		clue.setUserRole(currentUserRole);// 设置录入人员
		clue.setJoinDate(DateTimeKit.getLocalDate());// 设置录入时间
		clue.setHandleState(1);// 初始化处理状态
		clue.setIsOutOfTime(0);
		clue.setIsNew(1);
		clueService.add(clue);

		arg[0] = "clueAction!list?ctype=" + clue.getCtype();
		arg[1] = "线索管理";
		return "success_child";
	}

	public String delete() throws Exception {

		clue = clueService.loadById(id);
		ctype = clue.getCtype();
		clueService.delete(clue);
		arg[0] = "clueAction!list?ctype=" + ctype;
		arg[1] = "线索管理";
		return SUCCESS;
	}

	public String deleteClues() throws Exception {

		int[] ids = ConvertUtil.StringtoInt(checkedIDs);
		for (int i = 0; i < ids.length; i++) {
			clue = clueService.loadById(ids[i]);
			clueService.delete(clue);
		}
		AjaxMsgUtil.outputJSONObjectToAjax(response, new AjaxMsgVO("删除成功."));
		return null;
	}

	public String load() throws Exception {

		pageTileName = selectTileName(ctype);
		clue = clueService.loadById(id);// 当前修改人员的id
		return "load";
	}

	public String update() throws Exception {


		if(clue.getHandleState()!=3)
		{
			clue.setHandleState(2);
		}

		if (clue.getEndSituation() != null && clue.getEndSituation() != "") {
			clue.setHandleState(3);// 完结
		}
		clueService.update(clue);
		arg[0] = "clueAction!list?ctype=" + clue.getCtype();
		arg[1] = "线索管理";
		return "success_child";
	}

	public String view() {
		clue = clueService.loadById(id);
		return "view";
	}

	public String getNewClues() {

		currentUserRole = (UserRole) session.get("currentUserRole");

		List<Clue> clues = clueService.getNewClueByUserRole(currentUserRole);

		List<AjaxMsgVO> ajaxMsgVOList = new ArrayList<AjaxMsgVO>();

		if (clues != null && clues.size() > 0) {
			for (Clue clue : clues) {
				AjaxMsgVO clueVO = new AjaxMsgVO();
				clueVO.setId(clue.getId());
				clueVO.setName(clue.getTitle());
				clueVO.setJoinDate(clue.getJoinDate());
				clueVO.setType(0);
				ajaxMsgVOList.add(clueVO);
			}
		}

		AjaxMsgUtil.outputJSONArrayToAjax(response, ajaxMsgVOList);
		return null;
	}


	// 选择页面名称
	private String selectTileName(int ctype) {
		String pageName = "线索信息";
		switch (ctype) {
			case 0:
				pageName = "线索";
				break;
			case 1:
				pageName = "刑侦线索";
				break;
			case 2:
				pageName = "普通线索";
				break;
			default:
				break;
		}
		return pageName;
	}


	// get、set-------------------------------------------
	public ClueService getClueService() {
		return clueService;
	}

	public void setClueService(ClueService clueService) {
		this.clueService = clueService;
	}

	public Clue getClue() {
		return clue;
	}

	public void setClue(Clue clue) {
		this.clue = clue;
	}

	public List<Clue> getClues() {
		return clues;
	}

	public void setClues(List<Clue> clues) {
		this.clues = clues;
	}

	public int getCtype() {
		return ctype;
	}

	public void setCtype(int ctype) {
		this.ctype = ctype;
	}


}
