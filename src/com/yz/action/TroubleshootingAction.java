package com.yz.action;

import com.opensymphony.xwork2.ActionSupport;
import com.yz.model.*;
import com.yz.service.*;
import com.yz.util.AjaxMsgUtil;
import com.yz.vo.AjaxMsgVO;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Component("troubleshootingAction")
@Scope("prototype")
public class TroubleshootingAction extends ActionSupport implements
		RequestAware, SessionAware, ServletResponseAware, ServletRequestAware,UserRoleAware {

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

	private int troubid;// 疑难问题

	// service层对象
	@Resource
	private PersonService personService;
	@Resource
	private InjurycaseService injurycaseService;
	@Resource
	private ClueService clueService;
	@Resource
	private TroubleshootingService troubleshootingService;

	// 单个表对象
	private Person person;
	private Injurycase injurycase;
	private Clue clue;

	private Troubleshooting troubleshooting;

	// list表对象
	private List<Person> persons;

	//当前登录用户
	private UserRole currentUserRole;

	/**
	 * 疑难解答模块
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

	public String add() throws Exception {

		if (troubleshooting.getPerson() != null) {
			changePersonHandleState(troubleshooting.getPerson().getId());
		}
		if (troubleshooting.getInjurycase() != null) {
			changeInjurycaseHandleState(troubleshooting.getInjurycase().getId());
		}
		if (troubleshooting.getClue() != null) {
			changeClueHandleState(troubleshooting.getClue().getId());
		}
		troubleshootingService.add(troubleshooting);
		return "success_child";
	}

	public String deleteTroubleshooting() throws Exception {

		troubleshooting = troubleshootingService.loadById(troubid);
		troubleshootingService.delete(troubleshooting);
		AjaxMsgUtil.outputJSONObjectToAjax(response,new AjaxMsgVO("删除成功."));
		return null;
	}

	public String load() {
		troubleshooting = troubleshootingService.loadById(troubid);
		return "load";
	}

	public String update() {
		troubleshootingService.update(troubleshooting);
		return "success_child";
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

	// 改变线索当前处理状态
	private void changeClueHandleState(int clid) {

		Clue clue = clueService.loadById(clid);
		if (clue != null) {
			if (clue.getHandleState() == 1) {
				clue.setHandleState(2);
				clueService.update(clue);
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

	public PersonService getPersonService() {
		return personService;
	}

	public void setPersonService(PersonService personService) {
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

	public int getTroubid() {
		return troubid;
	}

	public void setTroubid(int troubid) {
		this.troubid = troubid;
	}

	public TroubleshootingService getTroubleshootingService() {
		return troubleshootingService;
	}

	public void setTroubleshootingService(
			TroubleshootingService troubleshootingService) {
		this.troubleshootingService = troubleshootingService;
	}

	public Troubleshooting getTroubleshooting() {
		return troubleshooting;
	}

	public void setTroubleshooting(Troubleshooting troubleshooting) {
		this.troubleshooting = troubleshooting;
	}

	public InjurycaseService getInjurycaseService() {
		return injurycaseService;
	}

	public void setInjurycaseService(InjurycaseService injurycaseService) {
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

	public ClueService getClueService() {
		return clueService;
	}

	public void setClueService(ClueService clueService) {
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


	public void setCurrentUserRole(UserRole sessionCurrentUserRole) {
		this.currentUserRole = sessionCurrentUserRole;
	}

}
