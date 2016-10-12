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

		// 1:未办理 2：在办理 3：已完结 4:超期办理 5:合计
		for (int i = 1; i <= 5; i++) {
			personCounts.add(personCountArray(con, convalue,starttime, endtime,i, userRole));
		}

		return "personCount";
	}

	// 返回人员个数
	private int getCount(List list) {
		if (list != null) {
			return list.size();
		}
		return 0;
	}

	/**
	 * 拼装人员统计表
	 * @param personCount
	 * @param i
	 * @param userRole
	 * @return
	 */
	private Integer[] personCountArray(int con,String convalue,String starttime,String endtime,int i, UserRole userRole) {
		Integer[] personCount = new Integer[15];
		int count = 0;
		int commonClueCount = 0;
		switch (i) {
		case 0:
			break;
		case 1:
		case 2:
		case 3:
			for (int j = 0; j < personCount.length - 2; j++) {

				personCount[j] = getCount(personService
						.getPersonsByTypeAndHandleState(con,convalue,starttime,endtime,j + 1, i, userRole));
			}
			personCount[personCount.length - 2] = getCount(personService
					.getPersonsByTypeAndHandleState(con,convalue,starttime,endtime,personCount.length, i,
							userRole));

			count = getCount(personService.getPersonsByHandleState(con,convalue,starttime,endtime,i, userRole));

			commonClueCount = getCount(personService
					.getPersonsByTypeAndHandleState(con,convalue,starttime,endtime,personCount.length - 1, i,
							userRole));

			personCount[personCount.length - 1] = count - commonClueCount;
			break;
		case 4:
			for (int j = 0; j < personCount.length - 2; j++) {

				personCount[j] = getCount(personService.getOutOfTimePersonsByType(con,convalue,starttime,endtime,
						j + 1, userRole));
			}
			personCount[personCount.length - 2] = getCount(personService
					.getOutOfTimePersonsByType(con,convalue,starttime,endtime,personCount.length,
							userRole));

			count = getCount(personService.getOutOfTimePersons(con,convalue,starttime,endtime,userRole));

			commonClueCount = getCount(personService.getOutOfTimePersonsByType(con,convalue,starttime,endtime,
					personCount.length - 1, userRole));

			personCount[personCount.length - 1] = count - commonClueCount;

			break;
		case 5:
			for (int j = 0; j < personCount.length - 2; j++) {

				personCount[j] = getCount(personService.getPersonsByType(con,convalue,starttime,endtime,j + 1,
						userRole));
			}
			personCount[personCount.length - 2] = getCount(personService
					.getPersonsByType(con,convalue,starttime,endtime,personCount.length, userRole));

			count = getCount(personService.getPersonsByUserRole(con,convalue,starttime,endtime,userRole));

			commonClueCount = getCount(personService.getPersonsByType(con,convalue,starttime,endtime,
					personCount.length - 1, userRole));

			personCount[personCount.length - 1] = count - commonClueCount;

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
		
		// 1:未办理 2：在办理 3：已完结 4:超期办理 5:合计
		for (int i = 1; i <= 5; i++) {
			injurycaseCounts.add(injurycaseCountArray(con, convalue,starttime, endtime,i, userRole));
		}
		return "injurycaseCount";
	}

	/**
	 * 拼装案件统计表
	 * @param con
	 * @param convalue
	 * @param starttime
	 * @param endtime
	 * @param i 办理状态:1:未办理 2：在办理 3：已完结 4:超期办理 5:合计
	 * @param userRole
	 * @return
	 */
	private Integer[] injurycaseCountArray(int con, String convalue,
			String starttime, String endtime, int i, UserRole userRole) {
		Integer[] injurycaseCount = new Integer[5];
		switch (i) {
		case 0:
			break;
		case 1:
		case 2:
		case 3:
			for (int j = 0; j < injurycaseCount.length - 1; j++) {
				injurycaseCount[j] = getCount(injurycaseService.getInjurycaseByTypeAndHandleState(con,convalue,starttime,endtime,j + 1, i, userRole));
			}
			injurycaseCount[injurycaseCount.length - 1] = getCount(injurycaseService.getInjurycasesByHandleState(con,convalue,starttime,endtime,i,userRole));
			break;
		case 4:
			for (int j = 0; j < injurycaseCount.length - 1; j++) {
				injurycaseCount[j] = getCount(injurycaseService.getOutOfTimeInjurycasesByType(con,convalue,starttime,endtime,j + 1,
						userRole));
			}
			injurycaseCount[injurycaseCount.length - 1] = getCount(injurycaseService.getInOutOfTimejurycasesByUserRole(con,convalue,starttime,endtime,userRole));
			break;
		case 5:
			for (int j = 0; j < injurycaseCount.length - 1; j++) {
				injurycaseCount[j] = getCount(injurycaseService.getInjurycasesByType(con,convalue,starttime,endtime,j + 1,
						userRole));
			}
			injurycaseCount[injurycaseCount.length - 1] = getCount(injurycaseService.getInjurycasesByUserRole(con,convalue,starttime,endtime,userRole));
			break;
		default:
			break;
		}

		return injurycaseCount;
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

		// 1:未办理 2：在办理 3：已完结 4:超期办理 5:合计
		for (int i = 1; i <= 5; i++) {
			clueCounts.add(clueCountArray(con, convalue,starttime, endtime,i, userRole));
		}
		
		return "clueCount";
	}
	
	/**
	 * 线索拼接
	 * @param con
	 * @param convalue
	 * @param starttime
	 * @param endtime
	 * @param i
	 * @param userRole
	 * @return
	 */
	private Integer[] clueCountArray(int con, String convalue,
			String starttime, String endtime, int i, UserRole userRole) {
		Integer[] clueCount = new Integer[3];
		switch (i) {
		case 0:
			break;
		case 1:
		case 2:
		case 3:
			clueCount[0] = getCount(clueService.getCluesByTypeAndHandleState(con,convalue,starttime,endtime,1, i, userRole));
			clueCount[1] = getCount(personService.getPersonsByTypeAndHandleState(con,convalue,starttime,endtime,14, i, userRole));
			clueCount[2] = clueCount[0]+clueCount[1];
			break;
		case 4:
			clueCount[0] = getCount(clueService.getOutOfTimeCluesByType(con,convalue,starttime,endtime,1,userRole));
			clueCount[1] = getCount(personService.getOutOfTimePersonsByType(con,convalue,starttime,endtime,14,userRole));
			clueCount[2] = clueCount[0]+clueCount[1];
			break;
		case 5:
			clueCount[0] = getCount(clueService.getCluesByType(con,convalue,starttime,endtime,1,userRole));
			clueCount[1] = getCount(personService.getPersonsByType(con,convalue,starttime,endtime,14,userRole));
			clueCount[2] = clueCount[0]+clueCount[1];
			break;
		default:
			break;
		}

		return clueCount;
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
