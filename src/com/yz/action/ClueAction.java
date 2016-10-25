package com.yz.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.yz.model.Judge;
import com.yz.model.Lawcase;
import com.yz.model.Person;
import com.yz.model.Troubleshooting;
import com.yz.model.Unit;
import com.yz.model.UserRole;
import com.yz.service.IClueService;
import com.yz.service.IJudgeService;
import com.yz.service.ILawcaseService;
import com.yz.service.IPersonService;
import com.yz.service.ITroubleshootingService;
import com.yz.service.IUnitService;
import com.yz.service.IUserRoleService;
import com.yz.util.ConvertUtil;
import com.yz.util.DateTimeKit;
import com.yz.util.InfoType;
import com.yz.vo.AjaxMsgVO;
import com.yz.vo.ClueVO;
import com.yz.vo.UnitVO;

@Component("clueAction")
@Scope("prototype")
public class ClueAction extends ActionSupport implements RequestAware,
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
	private int pid;// 按用户id

	private int lawid;// 涉及案件
	private int troubid;// 疑难问题
	private int jid;// 研判情况
	private int con;
	private String convalue;
	private int status;// 按状态
	private int ctype;// 线索类型
	private int queryState;
	private String starttime;
	private String endtime;

	// 页面信息
	private String pageTileName;// 页面标题名称

	// 批量删除
	private String checkedIDs;

	// service层对象
	@Resource
	private IUnitService unitService;
	@Resource
	private IClueService clueService;
	@Resource
	private ILawcaseService lawcaseService;
	@Resource
	private ITroubleshootingService troubleshootingService;
	@Resource
	private IJudgeService judgeService;
	@Resource
	private IUserRoleService userRoleService;
	@Resource
	private IPersonService personService;

	// 单个表对象
	private Clue clue;

	private Lawcase lawcase;
	private Troubleshooting troubleshooting;
	private Judge judge;

	private UnitVO unitVO;
	private Unit unit;

	// list表对象
	private List<Clue> clues;
	private List<UnitVO> unitVOs;
	private List<Unit> units;

	// 权限
	private int ulimit;

	// 部门json
	private String jsonUnits;

	/**
	 * 人员管理
	 */
	public String list() throws Exception {

		// 登陆验证
		UserRole userRoleo = (UserRole) session.get("userRoleo");
		if (userRoleo == null) {
			return "opsessiongo";
		}

		UserRole userRole = userRoleService.getUserRoleById(userRoleo.getId());

		if (convalue != null && !convalue.equals("")) {
			convalue = URLDecoder.decode(convalue, "utf-8");
			convalue = convalue.replace(" ", "");
		}
		if (starttime != null && !starttime.equals("")) {
			starttime = URLDecoder.decode(starttime, "utf-8");
			starttime = starttime.replace(" ", "");
		}
		if (endtime != null && !endtime.equals("")) {
			endtime = URLDecoder.decode(endtime, "utf-8");
			endtime = endtime.replace(" ", "");
		}
		if (page < 1) {
			page = 1;
		}

		pageTileName = selectTileName(ctype);

		// 总记录数
		totalCount = clueService.getTotalCount(con, convalue, userRole, ctype,
				queryState, starttime, endtime);
		// 总页数
		pageCount = clueService.getPageCount(totalCount, size);
		if (page > pageCount && pageCount != 0) {
			page = pageCount;
		}
		// 所有当前页记录对象
		clues = clueService.queryList(con, convalue, userRole, page, size,
				ctype, queryState, starttime, endtime);

		return "list";
	}

	// 选择页面名称
	private String selectTileName(int ctype) {
		// TODO Auto-generated method stub
		String pageName = "线索信息";
		switch (ctype) {
		case 0:
			pageName = "线索";
			break;
		case 1:
			pageName = "刑侦线索";
			break;
		default:
			break;
		}
		return pageName;
	}

	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	public String goToAdd() {

		UserRole userRoleo = (UserRole) session.get("userRoleo");
		if (userRoleo == null) {
			return "opsessiongo";
		}
		pageTileName = selectTileName(ctype);
		return "add";
	}

	/**
	 * 添加
	 * 
	 * @return
	 * @throws Exception
	 */

	public String add() throws Exception {

		UserRole userRoleo = (UserRole) session.get("userRoleo");
		if (userRoleo == null) {
			return "opsessiongo_child";
		}

		UserRole userRole = userRoleService.getUserRoleById(userRoleo.getId());

		clue.setUserRole(userRole);// 设置录入人员
		clue.setJoinDate(DateTimeKit.getLocalDate());// 设置录入时间
		clue.setHandleState(1);// 初始化处理状态
		clue.setIsOutOfTime(0);
		clue.setIsNew(1);
		clueService.add(clue);

		// 设置部门cids
		unitService.updateUnitByUserRoleAndInfoType(userRole.getUnit(), clue
				.getId()
				+ "", InfoType.CLUE, 1);

		arg[0] = "clueAction!list?ctype=" + clue.getCtype();
		arg[1] = "线索管理";
		return "success_child";
	}

	/**
	 * 删除一
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		UserRole userRoleo = (UserRole) session.get("userRoleo");
		if (userRoleo == null) {
			return "opsessiongo";
		}

		UserRole userRole = userRoleService.getUserRoleById(userRoleo.getId());

		clue = clueService.loadById(id);

		int ctype = clue.getCtype();

		// 设置部门cids
		unitService.updateUnitByUserRoleAndInfoType(userRole.getUnit(),
				id + "", InfoType.CLUE, -1);

		clueService.delete(clue);
		arg[0] = "clueAction!list?ctype=" + ctype;
		arg[1] = "线索管理";
		return SUCCESS;
	}

	/**
	 * 删除二(批量删除)
	 * 
	 * @return
	 */
	public String deleteClues() throws Exception {

		int[] ids = ConvertUtil.StringtoInt(checkedIDs);
		for (int i = 0; i < ids.length; i++) {
			clue = clueService.loadById(ids[i]);
			clueService.delete(clue);
		}
		AjaxMsgVO msgVO = new AjaxMsgVO();
		msgVO.setMessage("批量删除成功.");
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

	/**
	 * 跳转到修改页面
	 * 
	 * @return
	 */
	public String load() throws Exception {
		UserRole userRoleo = (UserRole) session.get("userRoleo");
		if (userRoleo == null) {
			return "opsessiongo";
		}
		pageTileName = selectTileName(ctype);

		clue = clueService.loadById(id);// 当前修改人员的id

		return "load";
	}

	/**
	 * 修改
	 * 
	 * @return
	 */
	public String update() throws Exception {
		UserRole userRoleo = (UserRole) session.get("userRoleo");
		if (userRoleo == null) {
			return "opsessiongo_child";
		}

		if (clue.getUserRole() == null) {
			UserRole userRole = userRoleService.getUserRoleById(userRoleo
					.getId());
			clue.setUserRole(userRole);// 设置录入人员
		}

		if (clue.getEndSituation() != null && clue.getEndSituation() != "") {
			clue.setHandleState(3);// 完结
		}
		clueService.update(clue);

		arg[0] = "clueAction!list?ctype=" + clue.getCtype();
		arg[1] = "人员管理";
		return "success_child";
	}

	/**
	 * 查看信息
	 * 
	 * @return
	 */
	public String view() {
		UserRole userRoleo = (UserRole) session.get("userRoleo");
		if (userRoleo == null) {
			return "opsessiongo";
		}
		clue = clueService.loadById(id);
		return "view";
	}

	/**
	 * 获取新增案件的事项信息
	 * 
	 * @return
	 */
	public String getNewClues() {

		// 登陆验证
		UserRole userRoleo = (UserRole) session.get("userRoleo");
		if (userRoleo == null) {
			return "opsessiongo";
		}

		UserRole userRole = userRoleService.getUserRoleById(userRoleo.getId());

		List<Clue> clues = clueService.getNewClueByUserRole(userRole);
		List<Person> persons = personService.getNewPersonsByUserRole(userRole);

		List<ClueVO> clueVOs = new ArrayList<ClueVO>();

		if (clues != null && clues.size() > 0) {
			for (Clue clue : clues) {
				ClueVO clueVO = new ClueVO();
				clueVO.setId(clue.getId());
				clueVO.setName(clue.getTitle());
				clueVO.setJoinDate(clue.getJoinDate());
				clueVO.setType(0);
				clueVOs.add(clueVO);
			}

		}

		if (persons != null && persons.size() > 0) {
			for (Person person : persons) {

				if (person.getType() == 14) {
					ClueVO clueVO = new ClueVO();
					clueVO.setId(person.getId());
					clueVO.setName(person.getName());
					clueVO.setJoinDate(person.getJoinDate());
					clueVO.setType(1);
					clueVOs.add(clueVO);
				}
			}
		}

		JSONArray jsonArray = JSONArray.fromObject(clueVOs);
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
		return null;
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

	public int getCon() {
		return con;
	}

	public void setCon(int con) {
		this.con = con;
	}

	public String getConvalue() {
		return convalue;
	}

	public void setConvalue(String convalue) {
		this.convalue = convalue;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public IClueService getClueService() {
		return clueService;
	}

	public void setClueService(IClueService clueService) {
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

	public String getCheckedIDs() {
		return checkedIDs;
	}

	public void setCheckedIDs(String checkedIDs) {
		this.checkedIDs = checkedIDs;
	}

	public int getUlimit() {
		return ulimit;
	}

	public void setUlimit(int ulimit) {
		this.ulimit = ulimit;
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

	public String getPageTileName() {
		return pageTileName;
	}

	public void setPageTileName(String pageTileName) {
		this.pageTileName = pageTileName;
	}

	public int getQueryState() {
		return queryState;
	}

	public void setQueryState(int queryState) {
		this.queryState = queryState;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public ILawcaseService getLawcaseService() {
		return lawcaseService;
	}

	public void setLawcaseService(ILawcaseService lawcaseService) {
		this.lawcaseService = lawcaseService;
	}

	public Lawcase getLawcase() {
		return lawcase;
	}

	public void setLawcase(Lawcase lawcase) {
		this.lawcase = lawcase;
	}

	public int getLawid() {
		return lawid;
	}

	public void setLawid(int lawid) {
		this.lawid = lawid;
	}

	public int getTroubid() {
		return troubid;
	}

	public void setTroubid(int troubid) {
		this.troubid = troubid;
	}

	public ITroubleshootingService getTroubleshootingService() {
		return troubleshootingService;
	}

	public void setTroubleshootingService(
			ITroubleshootingService troubleshootingService) {
		this.troubleshootingService = troubleshootingService;
	}

	public Troubleshooting getTroubleshooting() {
		return troubleshooting;
	}

	public void setTroubleshooting(Troubleshooting troubleshooting) {
		this.troubleshooting = troubleshooting;
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

	public String getJsonUnits() {
		return jsonUnits;
	}

	public void setJsonUnits(String jsonUnits) {
		this.jsonUnits = jsonUnits;
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

	public int getCtype() {
		return ctype;
	}

	public void setCtype(int ctype) {
		this.ctype = ctype;
	}

	public List<UnitVO> getUnitVOs() {
		return unitVOs;
	}

	public IUserRoleService getUserRoleService() {
		return userRoleService;
	}

	public void setUserRoleService(IUserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}

	public IPersonService getPersonService() {
		return personService;
	}

	public void setPersonService(IPersonService personService) {
		this.personService = personService;
	}

}
