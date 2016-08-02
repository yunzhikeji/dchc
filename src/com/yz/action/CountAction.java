package com.yz.action;

import java.net.URLDecoder;
import java.util.ArrayList;
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
import com.yz.model.Clue;
import com.yz.model.Injurycase;
import com.yz.model.Person;
import com.yz.model.UserRole;
import com.yz.service.IClueService;
import com.yz.service.IInjurycaseService;
import com.yz.service.IPersonService;
import com.yz.service.IUserRoleService;

@Component("countAction")
@Scope("prototype")
public class CountAction extends ActionSupport implements RequestAware,
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

	private int con;
	private String convalue;
	private int status;// 按状态
	private int queryState;
	private String starttime;
	private String endtime;

	// service层对象
	@Resource
	private IPersonService personService;
	@Resource
	private IClueService clueService;
	@Resource
	private IInjurycaseService injurycaseService;
	@Resource
	private IUserRoleService userRoleService;

	// list表对象
	private List<Clue> clues;
	private List<Person> persons;
	private List<Injurycase> injurycases;

	private List<Integer[]> personCounts;
	private List<Integer[]> injurycaseCounts;
	private List<Integer[]> clueCounts;

	/**
	 * 人员统计管理
	 */
	public String personCount() throws Exception {

		// 登陆验证
		UserRole userRoleo = (UserRole) session.get("userRoleo");
		if (userRoleo == null) {
			return "opsessiongo";
		}

		personCounts = new ArrayList<Integer[]>();

		UserRole userRole = userRoleService.getUserRoleById(userRoleo.getId());

		if (convalue != null && !convalue.equals("")) {
			convalue = URLDecoder.decode(convalue, "utf-8");
		}
		if (starttime != null && !starttime.equals("")) {
			starttime = URLDecoder.decode(starttime, "utf-8");
		}
		if (endtime != null && !endtime.equals("")) {
			endtime = URLDecoder.decode(endtime, "utf-8");
		}

		// 1:未办理 2：在办理 3：已完结
		for (int i = 1; i <= 3; i++) {
			personCounts.add(personCountArray(i,userRole));
		}
		// 超期办理

		// 合计

		return "personCount";
	}

	// 返回人员个数
	private int getCount(List<Person> pers) {
		if (pers != null) {
			return pers.size();
		}
		return 0;
	}

	/**
	 * 
	 * @param personCount
	 * @param i
	 * @param userRole
	 * @return
	 */
	private Integer[] personCountArray(int i, UserRole userRole) {
		Integer[] personCount = new Integer[15];
		switch (i) {
		case 0:
			break;
		case 1:
		case 2:
		case 3:
			personCount = new Integer[15];
			for (int j = 0; j < personCount.length; j++) {

				personCount[j] = getCount(personService
						.getPersonsByTypeAndHandleState(j + 1, i, userRole));
			}
			personCount[13] = getCount(personService
					.getPersonsByTypeAndHandleState(15, i, userRole));

			int count = getCount(personService.getPersonsByHandleState(i,
					userRole));

			int commonClueCount = getCount(personService
					.getPersonsByTypeAndHandleState(14, i, userRole));

			personCount[14] = count - commonClueCount;
			break;
		case 4:
			break;
		default:
			break;
		}

		return personCount;
	}

	/**
	 * 案件统计管理
	 */
	public String injurycaseCount() throws Exception {

		// 登陆验证
		UserRole userRoleo = (UserRole) session.get("userRoleo");
		if (userRoleo == null) {
			return "opsessiongo";
		}

		injurycaseCounts = new ArrayList<Integer[]>();

		UserRole userRole = userRoleService.getUserRoleById(userRoleo.getId());

		if (convalue != null && !convalue.equals("")) {
			convalue = URLDecoder.decode(convalue, "utf-8");
		}
		if (starttime != null && !starttime.equals("")) {
			starttime = URLDecoder.decode(starttime, "utf-8");
		}
		if (endtime != null && !endtime.equals("")) {
			endtime = URLDecoder.decode(endtime, "utf-8");
		}

		return "injurycaseCount";
	}

	/**
	 * 线索统计管理
	 */
	public String clueCount() throws Exception {

		// 登陆验证
		UserRole userRoleo = (UserRole) session.get("userRoleo");
		if (userRoleo == null) {
			return "opsessiongo";
		}

		UserRole userRole = userRoleService.getUserRoleById(userRoleo.getId());

		clueCounts = new ArrayList<Integer[]>();

		if (convalue != null && !convalue.equals("")) {
			convalue = URLDecoder.decode(convalue, "utf-8");
		}
		if (starttime != null && !starttime.equals("")) {
			starttime = URLDecoder.decode(starttime, "utf-8");
		}
		if (endtime != null && !endtime.equals("")) {
			endtime = URLDecoder.decode(endtime, "utf-8");
		}

		return "clueCount";
	}

	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	public String goToAdd() {

		return "add";
	}

	/**
	 * 添加
	 * 
	 * @return
	 * @throws Exception
	 */

	public String add() throws Exception {

		return "success_child";
	}

	/**
	 * 删除一
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		return SUCCESS;
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
		return "view";
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

	public List<Clue> getClues() {
		return clues;
	}

	public void setClues(List<Clue> clues) {
		this.clues = clues;
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

	public IInjurycaseService getInjurycaseService() {
		return injurycaseService;
	}

	public void setInjurycaseService(IInjurycaseService injurycaseService) {
		this.injurycaseService = injurycaseService;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public List<Injurycase> getInjurycases() {
		return injurycases;
	}

	public void setInjurycases(List<Injurycase> injurycases) {
		this.injurycases = injurycases;
	}

	public List<Integer[]> getPersonCounts() {
		return personCounts;
	}

	public void setPersonCounts(List<Integer[]> personCounts) {
		this.personCounts = personCounts;
	}

	public List<Integer[]> getInjurycaseCounts() {
		return injurycaseCounts;
	}

	public void setInjurycaseCounts(List<Integer[]> injurycaseCounts) {
		this.injurycaseCounts = injurycaseCounts;
	}

	public List<Integer[]> getClueCounts() {
		return clueCounts;
	}

	public void setClueCounts(List<Integer[]> clueCounts) {
		this.clueCounts = clueCounts;
	}

}
