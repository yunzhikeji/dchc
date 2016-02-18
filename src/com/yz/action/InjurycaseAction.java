package com.yz.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
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

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.yz.model.AnalyzeMan;
import com.yz.model.CommonClue;
import com.yz.model.ContrastMan;
import com.yz.model.DisappearMan;
import com.yz.model.GamblingCriminalMan;
import com.yz.model.GuiltSafeguardMan;
import com.yz.model.Injurycase;
import com.yz.model.Judge;
import com.yz.model.Lawcase;
import com.yz.model.Otherperson;
import com.yz.model.Person;
import com.yz.model.Successexample;
import com.yz.model.Troubleshooting;
import com.yz.model.Unit;
import com.yz.model.UserRole;
import com.yz.service.IAnalyzeManService;
import com.yz.service.ICommonClueService;
import com.yz.service.IContrastManService;
import com.yz.service.IDisappearManService;
import com.yz.service.IGamblingCriminalManService;
import com.yz.service.IGuiltSafeguardManService;
import com.yz.service.IInjurycaseService;
import com.yz.service.IJudgeService;
import com.yz.service.ILawcaseService;
import com.yz.service.IOtherpersonService;
import com.yz.service.IPersonService;
import com.yz.service.ISuccessexampleService;
import com.yz.service.ITroubleshootingService;
import com.yz.service.IUnitService;
import com.yz.service.IUserRoleService;
import com.yz.util.ConvertUtil;
import com.yz.util.DateTimeKit;
import com.yz.vo.AjaxMsgVO;
import com.yz.vo.UnitVO;

@Component("injurycaseAction")
@Scope("prototype")
public class InjurycaseAction extends ActionSupport implements RequestAware,
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
	private int inid;// 案件id

	private int troubid;// 疑难问题
	private int otherid;// 同案人员，关系人员
	private int jid;// 研判情况
	private int con;
	private String convalue;
	private int status;// 按状态
	private int itype;// 类型
	private int jtype;// 发起类型 1:研判信息 2：部门查证 3：上报情况
	private int queryState;// 办理状态
	private String starttime;
	private String endtime;

	// 页面信息
	private String pageTileName;// 页面标题名称

	// 批量删除
	private String checkedIDs;

	// service层对象
	private IUnitService unitService;
	private IInjurycaseService injurycaseService;
	private ITroubleshootingService troubleshootingService;
	private IOtherpersonService otherpersonService;
	private IJudgeService judgeService;
	private ISuccessexampleService successexampleService;
	
	private IUserRoleService userRoleService;

	// 单个表对象

	private Troubleshooting troubleshooting;
	private Otherperson otherperson;
	private Injurycase injurycase;
	private Judge judge;
	private Successexample successexample;

	private UnitVO unitVO;
	private Unit unit;

	// list表对象
	private List<Injurycase> injurycases;
	private List<Otherperson> tars;// 同案人员
	private List<Judge> judges;
	private List<UnitVO> unitVOs;
	private List<Unit> units;

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
		
		UserRole  userRole = userRoleService.loadById(userRoleo.getId());

		if (convalue != null && !convalue.equals("")) {
			convalue = URLDecoder.decode(convalue, "utf-8");
		}
		if (starttime != null && !starttime.equals("")) {
			starttime = URLDecoder.decode(starttime, "utf-8");
		}
		if (endtime != null && !endtime.equals("")) {
			endtime = URLDecoder.decode(endtime, "utf-8");
		}
		if (page < 1) {
			page = 1;
		}

		pageTileName = selectTileName(itype);

		// 总记录数
		totalCount = injurycaseService.getTotalCount(con, convalue, userRole,
				itype, queryState, starttime, endtime);
		// 总页数
		pageCount = injurycaseService.getPageCount(totalCount, size);
		if (page > pageCount && pageCount != 0) {
			page = pageCount;
		}
		// 所有当前页记录对象
		injurycases = injurycaseService.queryList(con, convalue, userRole,
				page, size, itype, queryState, starttime, endtime);

		return "list";
	}

	// 选择页面名称
	private String selectTileName(int type) {
		// TODO Auto-generated method stub
		String pageName = "案件信息";
		switch (type) {
		case 0:
			pageName = "案件";
			break;
		case 1:
			pageName = "一般案件";
			break;
		case 2:
			pageName = "重伤案件";
			break;
		case 3:
			pageName = "团伙系列案件";
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
		
		UserRole  userRole = userRoleService.loadById(userRoleo.getId());
		injurycase.setUserRole(userRole);// 设置录入人员
		injurycase.setJoinDate(DateTimeKit.getLocalDate());// 设置录入时间
		injurycase.setHandleState(1);// 初始化处理状态

		injurycaseService.add(injurycase);
		
		
		//添加当前用户id到部门pids
		if(userRole.getUnit()!=null)
		{
			int uid = userRole.getUnit().getId();
			Unit un = unitService.loadById(uid);
			
			if(un.getInids()!=null&&un.getInids()!="")
			{
				un.setInids(handleIDs(un.getInids(),injurycase.getId()+""));
			}else
			{
				un.setInids(injurycase.getId()+",");
			}
			unitService.update(un);
		}
		arg[0] = "injurycaseAction!list?itype=" + injurycase.getItype();
		arg[1] = "案件管理";
		return "success_child";
	}
	
	
	//处理ids
	private String handleIDs(String objIDs,String objID) {
		// TODO Auto-generated method stub
		Set<String> ids = new HashSet<String>();
		String newIDs = "";
		String[] arrayIDs = objIDs.split(",");
		for(int i=0;i<arrayIDs.length;i++)
		{
			ids.add(arrayIDs[i]);
		}
		ids.add(objID);
		
		for (String id : ids) {  
		      newIDs= newIDs+id+",";
		} 
		System.out.println(newIDs);
		return newIDs;
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
		injurycase = injurycaseService.loadById(id);
		injurycaseService.delete(injurycase);
		arg[0] = "injurycaseAction!list?itype=" + injurycase.getItype();
		arg[1] = "案件管理";
		return SUCCESS;
	}

	/**
	 * 删除二(批量删除)
	 * 
	 * @return
	 */
	public String deleteInjurycases() throws Exception {

		int[] ids = ConvertUtil.StringtoInt(checkedIDs);
		for (int i = 0; i < ids.length; i++) {
			injurycase = injurycaseService.loadById(id);
			injurycaseService.delete(injurycase);
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
		pageTileName = selectTileName(itype);

		tars = otherpersonService.getInjurycaseOtherpersonByOtype(2, id);// 同案人
		
		
		
		injurycase = injurycaseService.queryInjurycaseById(id);// 当前修改案件的id
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
		if (injurycase.getEndSituation() != null
				&& injurycase.getEndSituation() != "") {
			injurycase.setHandleState(3);// 完结
		}
		injurycaseService.update(injurycase);

		arg[0] = "injurycaseAction!list?itype=" + injurycase.getItype();
		arg[1] = "案件管理";
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
		injurycase = injurycaseService.loadById(id);
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

	public String getCheckedIDs() {
		return checkedIDs;
	}

	public void setCheckedIDs(String checkedIDs) {
		this.checkedIDs = checkedIDs;
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

	public int getTroubid() {
		return troubid;
	}

	public void setTroubid(int troubid) {
		this.troubid = troubid;
	}

	public ITroubleshootingService getTroubleshootingService() {
		return troubleshootingService;
	}

	@Resource
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

	public int getSize() {
		return size;
	}

	public String getJsonUnits() {
		return jsonUnits;
	}

	public void setJsonUnits(String jsonUnits) {
		this.jsonUnits = jsonUnits;
	}

	public int getOtherid() {
		return otherid;
	}

	public void setOtherid(int otherid) {
		this.otherid = otherid;
	}

	public IOtherpersonService getOtherpersonService() {
		return otherpersonService;
	}

	@Resource
	public void setOtherpersonService(IOtherpersonService otherpersonService) {
		this.otherpersonService = otherpersonService;
	}

	public Otherperson getOtherperson() {
		return otherperson;
	}

	public void setOtherperson(Otherperson otherperson) {
		this.otherperson = otherperson;
	}

	public List<Otherperson> getTars() {
		return tars;
	}

	public void setTars(List<Otherperson> tars) {
		this.tars = tars;
	}

	public ISuccessexampleService getSuccessexampleService() {
		return successexampleService;
	}

	@Resource
	public void setSuccessexampleService(
			ISuccessexampleService successexampleService) {
		this.successexampleService = successexampleService;
	}

	public Successexample getSuccessexample() {
		return successexample;
	}

	public void setSuccessexample(Successexample successexample) {
		this.successexample = successexample;
	}

	public int getItype() {
		return itype;
	}

	public void setItype(int itype) {
		this.itype = itype;
	}

	public IInjurycaseService getInjurycaseService() {
		return injurycaseService;
	}

	@Resource
	public void setInjurycaseService(IInjurycaseService injurycaseService) {
		this.injurycaseService = injurycaseService;
	}

	public Injurycase getInjurycase() {
		return injurycase;
	}

	public void setInjurycase(Injurycase injurycase) {
		this.injurycase = injurycase;
	}

	public List<Injurycase> getInjurycases() {
		return injurycases;
	}

	public void setInjurycases(List<Injurycase> injurycases) {
		this.injurycases = injurycases;
	}

	public int getJid() {
		return jid;
	}

	public void setJid(int jid) {
		this.jid = jid;
	}

	public int getJtype() {
		return jtype;
	}

	public void setJtype(int jtype) {
		this.jtype = jtype;
	}

	public IUnitService getUnitService() {
		return unitService;
	}

	@Resource
	public void setUnitService(IUnitService unitService) {
		this.unitService = unitService;
	}

	public IJudgeService getJudgeService() {
		return judgeService;
	}

	@Resource
	public void setJudgeService(IJudgeService judgeService) {
		this.judgeService = judgeService;
	}

	public Judge getJudge() {
		return judge;
	}

	public void setJudge(Judge judge) {
		this.judge = judge;
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

	public List<Judge> getJudges() {
		return judges;
	}

	public void setJudges(List<Judge> judges) {
		this.judges = judges;
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

	public int getInid() {
		return inid;
	}

	public void setInid(int inid) {
		this.inid = inid;
	}

	public IUserRoleService getUserRoleService() {
		return userRoleService;
	}

	@Resource
	public void setUserRoleService(IUserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}

	public List<UnitVO> getUnitVOs() {
		return unitVOs;
	}

	
}
