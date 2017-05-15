package com.yz.action;

import com.yz.model.Unit;
import com.yz.model.UserRole;
import com.yz.service.UnitService;
import com.yz.util.AjaxMsgUtil;
import com.yz.util.ConvertUtil;
import com.yz.vo.AjaxMsgVO;
import com.yz.vo.UnitVO;
import net.sf.json.JSONArray;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component("unitAction")
@Scope("prototype")
public class UnitAction extends BaseAction {


	public static final String OPERTION_UNIT_NUMBER = "371402020000";
	private int id;
	@Resource
	private UnitService unitService;
	private Unit unit;
	private List<Unit> units;
	private List<UnitVO> unitVOs;
	private int uid;
	private String unitName;
	private String unitNumber;

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
		totalCount = unitService.getTotalCount(con, convalue, currentUserRole);
		// 总页数
		pageCount = unitService.getPageCount(totalCount, size);
		if (page > pageCount && pageCount != 0) {
			page = pageCount;
		}
		// 所有当前页记录对象
		units = unitService.queryList(con, convalue, currentUserRole, page, size);

		return "list";
	}


	public String goToAdd() {

		return "add";
	}


	public String add() throws Exception {

		unitService.add(unit);

		arg[0] = "unitAction!list";
		arg[1] = "机构管理";
		return "success_child";
	}


	public String checkUnitNumber() {
		if (isExistOtherSameUnit(unitService.getUnitByName(unitNumber), uid)) {
			AjaxMsgUtil.outputJSONObjectToAjax(response, new AjaxMsgVO("该组织机构编号已经存在,请重新输入."));
		}
		return null;
	}


	public String checkUnitName() {
		if (isExistOtherSameUnit(unitService.getUnitByName(unitName), uid)) {
			AjaxMsgUtil.outputJSONObjectToAjax(response, new AjaxMsgVO("该组织机构已经存在,请重新输入."));
		}
		return null;
	}


	public String delete() {

		unitService.deleteById(id);
		arg[0] = "unitAction!list";
		arg[1] = "机构管理";
		return SUCCESS;
	}


	public String deleteUnits() {

		int[] ids = ConvertUtil.StringtoInt(checkedIDs);
		for (int i = 0; i < ids.length; i++) {
			unitService.deleteById(ids[i]);
		}
		AjaxMsgUtil.outputJSONObjectToAjax(response, new AjaxMsgVO("删除成功."));
		return null;
	}


	public String load() throws Exception {

		unit = unitService.loadById(id);

		return "load";
	}


	public String update() throws Exception {

		unitService.update(unit);
		arg[0] = "unitAction!list";
		arg[1] = "机构管理";
		return "success_child";
	}


	public String view() {

		unit = unitService.loadById(id);
		return "view";
	}


	public void getOpertionUnit() {
		unitVOs = new ArrayList<UnitVO>();
		unit = unitService.getUnitByNumber(UnitAction.OPERTION_UNIT_NUMBER);
		if (unit != null) {
			UnitVO unitVO = new UnitVO();
			unitVO.setId(unit.getId());
			unitVO.setName(unit.getName());
			unitVOs.add(unitVO);
		}
		AjaxMsgUtil.outputJSONOToAjax(response, JSONArray.fromObject(unitVOs).toString());
	}


	public void getUnitVOs() {
		unitVOs = new ArrayList<UnitVO>();
		units = unitService.getUnits();
		for(Unit unit :units)
		{
			if (unit != null) {
				UnitVO unitVO = new UnitVO();
				unitVO.setId(unit.getId());
				unitVO.setName(unit.getName());
				unitVOs.add(unitVO);
			}
		}
		AjaxMsgUtil.outputJSONOToAjax(response, JSONArray.fromObject(unitVOs).toString());
	}


	private boolean isExistOtherSameUnit(Unit unit, Integer currentUnitId) {
		if (unit != null && unit.getId() != currentUnitId) {
			return true;
		}
		return false;
	}

	// get、set-------------------------------------------
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public UnitService getUnitService() {
		return unitService;
	}

	public void setUnitService(UnitService unitService) {
		this.unitService = unitService;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public List<Unit> getUnits() {
		return units;
	}

	public void setUnits(List<Unit> units) {
		this.units = units;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getUnitNumber() {
		return unitNumber;
	}

	public void setUnitNumber(String unitNumber) {
		this.unitNumber = unitNumber;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public void setCurrentUserRole(UserRole sessionCurrentUserRole) {
		this.currentUserRole = sessionCurrentUserRole;
	}
}
