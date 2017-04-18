package com.yz.action;

import com.opensymphony.xwork2.ActionSupport;
import com.yz.model.Pnotice;
import com.yz.model.UserRole;
import com.yz.service.IPnoticeService;
import com.yz.util.AjaxMsgUtil;
import com.yz.util.ConvertUtil;
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
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

@Component("pnoticeAction")  
@Scope("prototype")
public class PnoticeAction extends ActionSupport implements RequestAware,SessionAware, ServletResponseAware, ServletRequestAware {

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
	private int con;
	private String convalue;
	private int status;// 按状态
	private int pid;// 按用户id
	private String starttime;
	private String endtime;

	//批量删除
	private String checkedIDs;
	
	//service层对象
	@Resource
	private IPnoticeService pnoticeService;

	//单个表对象
	private Pnotice pnotice;
	
	//list表对象
	 
	private List<Pnotice> pnotices;

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
		// 总记录数
		totalCount = pnoticeService.getTotalCount(con, convalue, userRoleo,starttime,endtime);
		// 总页数
		pageCount = pnoticeService.getPageCount(totalCount, size);
		if (page > pageCount && pageCount != 0) {
			page = pageCount;
		}
		// 所有当前页记录对象
		pnotices = pnoticeService.queryList(con, convalue, userRoleo, page, size,starttime,endtime);
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
		pnotice.setUserRole(userRoleo);
		pnoticeService.add(pnotice);

		arg[0] = "pnoticeAction!list";
		arg[1] = "通知管理";
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
		pnoticeService.deleteById(id);
		arg[0] = "pnoticeAction!list";
		arg[1] = "通知管理";
		return SUCCESS;
	}
	
	
	/**
	 * 删除二(批量删除)
	 * 
	 * @return
	 */
	public String deletePnotices() {
		
		int[] ids = ConvertUtil.StringtoInt(checkedIDs);
		for(int i=0;i<ids.length;i++)
		{
			pnoticeService.deleteById(ids[i]);
		}
		AjaxMsgUtil.outputJSONObjectToAjax(response,new AjaxMsgVO("删除成功."));
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
		pnotice = pnoticeService.loadById(id);// 当前修改通知的id
		/*
		 * 当前操作通知权限划分
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
		pnotice.setUserRole(userRoleo);
		pnoticeService.update(pnotice);
		arg[0] = "pnoticeAction!list";
		arg[1] = "通知管理";
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
		pnotice = pnoticeService.loadById(id);
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

	public IPnoticeService getPnoticeService() {
		return pnoticeService;
	}

	public void setPnoticeService(IPnoticeService pnoticeService) {
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
	
	
	
}
