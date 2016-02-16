package com.yz.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.yz.model.Person;
import com.yz.model.Successexample;
import com.yz.model.UserRole;
import com.yz.service.IClueService;
import com.yz.service.IInjurycaseService;
import com.yz.service.IPersonService;
import com.yz.service.ISuccessexampleService;
import com.yz.util.ConvertUtil;
import com.yz.vo.AjaxMsgVO;

@Component("successexampleAction")  
@Scope("prototype")
public class SuccessexampleAction extends ActionSupport implements RequestAware,SessionAware, ServletResponseAware, ServletRequestAware {

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
	private int con;
	private String convalue;
	private int status;// 按状态
	private String starttime;
	private String endtime;

	//批量删除
	private String checkedIDs;
	
	//service层对象
	private ISuccessexampleService successexampleService;
	private IPersonService personService;
	private IInjurycaseService injurycaseService;
	private IClueService clueService;

	//单个表对象
	private Person person;
	private Injurycase injurycase;
	private Clue clue;
	private Successexample successexample;
	
	//list表对象
	 
	private List<Successexample> successexamples;

	//权限
	private int ulimit;



	/**
	 * 机构管理
	 */
	public String list() throws Exception {
		UserRole userRoleo = (UserRole) session.get("userRoleo");
		if (userRoleo == null) {
			return "opsessiongo";
		}
		if (convalue != null && !convalue.equals("")) {
			convalue = URLDecoder.decode(convalue, "utf-8");
		}
		if (page < 1) {
			page = 1;
		}
		if(starttime!=null&&!starttime.equals("")){
			starttime=URLDecoder.decode(starttime, "utf-8");
		}
		if(endtime!=null&&!endtime.equals("")){
			endtime=URLDecoder.decode(endtime, "utf-8");
		}
		// 总记录数
		totalCount = successexampleService.getTotalCount(con, convalue, userRoleo,starttime,endtime);
		// 总页数
		pageCount = successexampleService.getPageCount(totalCount, size);
		if (page > pageCount && pageCount != 0) {
			page = pageCount;
		}
		// 所有当前页记录对象
		successexamples = successexampleService.queryList(con, convalue, userRoleo, page, size,starttime,endtime);
		return "list";
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
		if(pid!=0)
		{
			person = personService.loadById(pid);
		}
		if(inid!=0)
		{
			injurycase = injurycaseService.loadById(inid);
		}
		if(cid!=0)
		{
			clue = clueService.loadById(cid);
		}
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
		successexampleService.add(successexample);

		arg[0] = "successexampleAction!list";
		arg[1] = "成功案例管理";
		return "success_child";
	}

	/**
	 * 删除一
	 * 
	 * @return
	 */
	public String delete() {
		UserRole userRoleo = (UserRole) session.get("userRoleo");
		if (userRoleo == null) {
			return "opsessiongo";
		}
		successexampleService.deleteById(id);
		arg[0] = "successexampleAction!list";
		arg[1] = "成功案例管理";
		return SUCCESS;
	}
	
	
	/**
	 * 删除二(批量删除)
	 * 
	 * @return
	 */
	public String deleteSuccessexamples() {
		
		int[] ids = ConvertUtil.StringtoInt(checkedIDs);
		for(int i=0;i<ids.length;i++)
		{
			successexampleService.deleteById(ids[i]);
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
		successexample = successexampleService.loadById(id);// 当前修改成功案例的id
		/*
		 * 当前操作成功案例权限划分
		 */
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
		successexampleService.update(successexample);
		arg[0] = "successexampleAction!list";
		arg[1] = "成功案例管理";
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
		successexample = successexampleService.loadById(id);
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

	public ISuccessexampleService getSuccessexampleService() {
		return successexampleService;
	}

	@Resource
	public void setSuccessexampleService(ISuccessexampleService successexampleService) {
		this.successexampleService = successexampleService;
	}

	public Successexample getSuccessexample() {
		return successexample;
	}

	public void setSuccessexample(Successexample successexample) {
		this.successexample = successexample;
	}

	public List<Successexample> getSuccessexamples() {
		return successexamples;
	}

	public void setSuccessexamples(List<Successexample> successexamples) {
		this.successexamples = successexamples;
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


	public IPersonService getPersonService() {
		return personService;
	}

	@Resource
	public void setPersonService(IPersonService personService) {
		this.personService = personService;
	}


	public IInjurycaseService getInjurycaseService() {
		return injurycaseService;
	}


	@Resource
	public void setInjurycaseService(IInjurycaseService injurycaseService) {
		this.injurycaseService = injurycaseService;
	}


	public IClueService getClueService() {
		return clueService;
	}


	@Resource
	public void setClueService(IClueService clueService) {
		this.clueService = clueService;
	}


	public Person getPerson() {
		return person;
	}


	public void setPerson(Person person) {
		this.person = person;
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
