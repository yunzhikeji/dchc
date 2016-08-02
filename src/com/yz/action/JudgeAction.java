package com.yz.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.yz.model.Clue;
import com.yz.model.Injurycase;
import com.yz.model.Judge;
import com.yz.model.Person;
import com.yz.model.Unit;
import com.yz.model.UserRole;
import com.yz.service.IClueService;
import com.yz.service.IInjurycaseService;
import com.yz.service.IJudgeService;
import com.yz.service.IPersonService;
import com.yz.service.IUnitService;
import com.yz.vo.AjaxMsgVO;
import com.yz.vo.UnitVO;

@Component("judgeAction")
@Scope("prototype")
public class JudgeAction extends ActionSupport implements RequestAware,
		SessionAware, ServletResponseAware, ServletRequestAware {

	private static final long serialVersionUID = 1L;
	Map<String, Object> request;
	Map<String, Object> session;
	private javax.servlet.http.HttpServletResponse response;
	private javax.servlet.http.HttpServletRequest req;

	// 分页显示
	private String[] arg = new String[2];
	private int page;
	private final int size = 10;
	private int pageCount;
	private int totalCount;

	// 条件
	private int id;
	private int pid;// 人员id
	private int inid; // 案件id
	private int cid;// 刑侦线索

	private int jid;// 研判id
	private int jtype;// 发起类型 1:研判信息 2：部门查证 3：上报情况

	// service层对象
	@Resource
	private IPersonService personService;
	@Resource
	private IInjurycaseService injurycaseService;
	@Resource
	private IClueService clueService;
	@Resource
	private IJudgeService judgeService;
	@Resource
	private IUnitService unitService;

	// 单个表对象
	private Person person;
	private Injurycase injurycase;
	private Clue clue;

	private Judge judge;
	private UnitVO unitVO;
	private Unit unit;

	// list表对象
	private List<Person> persons;
	private List<Judge> judges;
	private List<UnitVO> unitVOs;
	private List<Unit> units;

	/**
	 * 发起研判模块
	 */
	public String goToAdd() {

		if (pid != 0) {
			person = personService.loadById(pid);
		}
		if (inid != 0) {
			injurycase = injurycaseService.loadById(inid);
		}
		if (cid != 0) {
			clue = clueService.loadById(cid);
		}

		return "add";
	}

	// 处理报送部门
	public void getUnitVOs() {
		// TODO Auto-generated method stub
		unitVOs = new ArrayList<UnitVO>();
		units = unitService.getUnits();
		if (units.size() > 0) {
			for (Unit unit : units) {
				
				String number = unit.getNumber().replace(" ", "");
				
				if(number.equals("DC001")||number.equals("DC002")||number.equals("DC003")||number.equals("DC004"))
				{
					unitVO = new UnitVO();
					unitVO.setId(unit.getId());
					unitVO.setName(unit.getName());
					unitVOs.add(unitVO);
				}
			}
		}
		JSONArray jsonArray = JSONArray.fromObject(unitVOs);
		PrintWriter out;
		try {
			response.setContentType("text/html;charset=UTF-8");
			out = response.getWriter();
			out.print(jsonArray.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String add() throws Exception {
		UserRole userRoleo = (UserRole) session.get("userRoleo");
		if (userRoleo == null) {
			return "opsessiongo_child";
		}
		if (judge.getPerson() != null) {
			changePersonHandleState(judge.getPerson().getId());
			handlePersonJudgeIndex(judge.getJtype());
			judge.getReportUnit();
			setUnitPids(userRoleo, judge);

		}
		if (judge.getInjurycase() != null) {
			changeInjurycaseHandleState(judge.getInjurycase().getId());
			handleInjurycaseJudgeIndex(judge.getJtype());
			setUnitInids(userRoleo, judge);

		}
		if (judge.getClue() != null) {
			changeClueHandleState(judge.getClue().getId());
			handleClueJudgeIndex(judge.getJtype());
			setUnitCids(userRoleo, judge);

		}
		judgeService.add(judge);
		return "success_child";
	}

	// 设置报送部门的pids
	private void setUnitPids(UserRole userRoleo, Judge judge) {
		// TODO Auto-generated method stub
		// 报送的部门
		if (judge.getReportUnit() != null
				&& judge.getReportUnit().replace(" ", "") != "") {
			String reportUnit = judge.getReportUnit();
			Set<String> unitNames = new HashSet<String>();
			String[] arrayUnitNames = reportUnit.split(",");
			for (int i = 0; i < arrayUnitNames.length; i++) {
				unitNames.add(arrayUnitNames[i]);
			}
			for (String uname : unitNames) {

				Unit unit = unitService.getUnitByName(uname);
				if (unit != null) {
					if (unit.getPids() != null && unit.getPids() != "") {
						unit.setPids(handleIDs(unit.getPids(), judge
								.getPerson().getId()
								+ ""));
					} else {
						unit.setPids(judge.getPerson().getId() + ",");
					}
				}
			}

		}
	}

	// 设置报送部门的inids
	private void setUnitInids(UserRole userRoleo, Judge judge) {
		// TODO Auto-generated method stub
		// 报送的部门
		if (judge.getReportUnit() != null
				&& judge.getReportUnit().replace(" ", "") != "") {
			String reportUnit = judge.getReportUnit();
			Set<String> unitNames = new HashSet<String>();
			String[] arrayUnitNames = reportUnit.split(",");
			for (int i = 0; i < arrayUnitNames.length; i++) {
				unitNames.add(arrayUnitNames[i]);
			}
			for (String uname : unitNames) {

				Unit unit = unitService.getUnitByName(uname);
				if (unit != null) {
					if (unit.getInids() != null && unit.getInids() != "") {
						unit.setInids(handleIDs(unit.getInids(), judge
								.getInjurycase().getId()
								+ ""));
					} else {
						unit.setInids(judge.getInjurycase().getId() + ",");
					}
				}
			}

		}
	}

	// 设置报送部门的cids
	private void setUnitCids(UserRole userRoleo, Judge judge) {
		// TODO Auto-generated method stub
		// 报送的部门
		if (judge.getReportUnit() != null
				&& judge.getReportUnit().replace(" ", "") != "") {
			String reportUnit = judge.getReportUnit();
			Set<String> unitNames = new HashSet<String>();
			String[] arrayUnitNames = reportUnit.split(",");
			for (int i = 0; i < arrayUnitNames.length; i++) {
				unitNames.add(arrayUnitNames[i]);
			}
			for (String uname : unitNames) {

				Unit unit = unitService.getUnitByName(uname);
				if (unit != null) {
					if (unit.getCids() != null && unit.getCids() != "") {
						unit.setCids(handleIDs(unit.getCids(), judge
								.getClue().getId()
								+ ""));
					} else {
						unit.setCids(judge.getClue().getId() + ",");
					}
				}
			}

		}
	}

	// 处理ids
	private String handleIDs(String objIDs, String objID) {
		// TODO Auto-generated method stub
		Set<String> ids = new HashSet<String>();
		String newIDs = "";
		String[] arrayIDs = objIDs.split(",");
		for (int i = 0; i < arrayIDs.length; i++) {
			ids.add(arrayIDs[i]);
		}
		ids.add(objID);

		for (String id : ids) {
			newIDs = newIDs + id + ",";
		}
		return newIDs;
	}

	// 改变人员当前处理状态
	private void changePersonHandleState(int perid) {

		Person per = personService.loadById(perid);
		if (per != null) {
			if (per.getHandleState() == 1) {
				per.setHandleState(2);
				personService.update(per);
			}
		}

	}

	private void changeInjurycaseHandleState(Integer inid) {

		Injurycase injurycase = injurycaseService.loadById(inid);
		if (injurycase != null) {
			if (injurycase.getHandleState() == 1) {
				injurycase.setHandleState(2);
				injurycaseService.update(injurycase);
			}
		}
	}

	private void changeClueHandleState(int clid) {

		Clue clue = clueService.loadById(clid);
		if (clue != null) {
			if (clue.getHandleState() == 1) {
				clue.setHandleState(2);
				clueService.update(clue);
			}
		}

	}

	// 新增研判(查证)信息设置研判(查证)顺序
	private void handlePersonJudgeIndex(int currentJtype) {
		// TODO Auto-generated method stub

		judges = judgeService.loadByTypeAndPid(currentJtype, judge.getPerson()
				.getId());

		if (judges != null) {
			judge.setIndexNumber(judges.size() + 1);
		}
	}

	private void handleInjurycaseJudgeIndex(int currentJtype) {
		// TODO Auto-generated method stub

		judges = judgeService.loadInjurycaseByTypeAndPid(currentJtype, judge
				.getInjurycase().getId());

		if (judges != null) {
			judge.setIndexNumber(judges.size() + 1);
		}
	}

	private void handleClueJudgeIndex(int currentJtype) {
		// TODO Auto-generated method stub

		judges = judgeService.loadClueByTypeAndPid(currentJtype, judge
				.getClue().getId());

		if (judges != null) {
			judge.setIndexNumber(judges.size() + 1);
		}
	}

	public String deleteJudge() throws Exception {
		judge = judgeService.loadById(jid);
		judgeService.delete(judge);
		AjaxMsgVO msgVO = new AjaxMsgVO();
		msgVO.setMessage("删除成功.");
		JSONObject jsonObj = JSONObject.fromObject(msgVO);
		PrintWriter out;
		try {
			response.setContentType("text/html;charset=UTF-8");
			out = response.getWriter();
			out.print(jsonObj.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String load() {
		judge = judgeService.loadById(jid);
		return "load";
	}

	public String update() {
		judgeService.update(judge);
		return "success_child";
	}

	// get、set-------------------------------------------

	// 获得HttpServletResponse对象
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setServletRequest(HttpServletRequest req) {
		this.req = req;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String[] getArg() {
		return arg;
	}

	public void setArg(String[] arg) {
		this.arg = arg;
	}

	public IPersonService getPersonService() {
		return personService;
	}

	public void setPersonService(IPersonService personService) {
		this.personService = personService;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public javax.servlet.http.HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(javax.servlet.http.HttpServletResponse response) {
		this.response = response;
	}

	public javax.servlet.http.HttpServletRequest getReq() {
		return req;
	}

	public void setReq(javax.servlet.http.HttpServletRequest req) {
		this.req = req;
	}

	public int getJid() {
		return jid;
	}

	public void setJid(int jid) {
		this.jid = jid;
	}

	public IJudgeService getJudgeService() {
		return judgeService;
	}

	public void setJudgeService(IJudgeService judgeService) {
		this.judgeService = judgeService;
	}

	public Judge getJudge() {
		return judge;
	}

	public void setJudge(Judge judge) {
		this.judge = judge;
	}

	public int getSize() {
		return size;
	}

	public IUnitService getUnitService() {
		return unitService;
	}

	public void setUnitService(IUnitService unitService) {
		this.unitService = unitService;
	}

	public UnitVO getUnitVO() {
		return unitVO;
	}

	public void setUnitVO(UnitVO unitVO) {
		this.unitVO = unitVO;
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

	public void setUnitVOs(List<UnitVO> unitVOs) {
		this.unitVOs = unitVOs;
	}

	public List<Judge> getJudges() {
		return judges;
	}

	public void setJudges(List<Judge> judges) {
		this.judges = judges;
	}

	public int getJtype() {
		return jtype;
	}

	public void setJtype(int jtype) {
		this.jtype = jtype;
	}

	public IInjurycaseService getInjurycaseService() {
		return injurycaseService;
	}

	public void setInjurycaseService(IInjurycaseService injurycaseService) {
		this.injurycaseService = injurycaseService;
	}

	public int getInid() {
		return inid;
	}

	public void setInid(int inid) {
		this.inid = inid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public IClueService getClueService() {
		return clueService;
	}

	public void setClueService(IClueService clueService) {
		this.clueService = clueService;
	}

	public Injurycase getInjurycase() {
		return injurycase;
	}

	public void setInjurycase(Injurycase injurycase) {
		this.injurycase = injurycase;
	}

	public Clue getClue() {
		return clue;
	}

	public void setClue(Clue clue) {
		this.clue = clue;
	}

}
