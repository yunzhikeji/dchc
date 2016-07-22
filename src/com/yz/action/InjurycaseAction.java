package com.yz.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.yz.model.Injurycase;
import com.yz.model.Judge;
import com.yz.model.Media;
import com.yz.model.Otherperson;
import com.yz.model.Successexample;
import com.yz.model.Troubleshooting;
import com.yz.model.Unit;
import com.yz.model.UserRole;
import com.yz.service.IInjurycaseService;
import com.yz.service.IJudgeService;
import com.yz.service.IMediaService;
import com.yz.service.IOtherpersonService;
import com.yz.service.ISuccessexampleService;
import com.yz.service.ITroubleshootingService;
import com.yz.service.IUnitService;
import com.yz.service.IUserRoleService;
import com.yz.util.ConvertUtil;
import com.yz.util.DateTimeKit;
import com.yz.vo.AjaxMsgVO;
import com.yz.vo.InjurycaseVO;
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
	@Resource
	private IUnitService unitService;
	@Resource
	private IInjurycaseService injurycaseService;
	@Resource
	private ITroubleshootingService troubleshootingService;
	@Resource
	private IOtherpersonService otherpersonService;
	private IJudgeService judgeService;
	@Resource
	private ISuccessexampleService successexampleService;
	@Resource
	private IMediaService mediaService;

	@Resource
	private IUserRoleService userRoleService;

	// 单个表对象
	private Troubleshooting troubleshooting;
	private Otherperson otherperson;
	private Injurycase injurycase;
	private Judge judge;
	private Successexample successexample;
	private Media media;

	private UnitVO unitVO;
	private Unit unit;

	// list表对象
	private List<Injurycase> injurycases;
	private List<InjurycaseVO> injurycaseVOs;
	private List<Otherperson> tars;// 同案人员
	private List<Media> medias;
	private List<Judge> judges;
	private List<UnitVO> unitVOs;
	private List<Unit> units;
	// 同系列案件
	private List<Injurycase> injurycaseSeries;

	private List<Media> mediaVideos;
	private List<Media> mediaImages;

	// 部门json
	private String jsonUnits;

	// 案件图片
	private File picture1;
	private String picture1ContentType;
	private String picture1FileName;

	// 串并案查询案件关键字
	private String keyword;
	// 串并案系列名称
	private String series;

	/**
	 * 人员管理
	 */
	public String list() throws Exception {

		// 登陆验证
		UserRole userRoleo = (UserRole) session.get("userRoleo");
		if (userRoleo == null) {
			return "opsessiongo";
		}

		UserRole userRole = userRoleService.loadById(userRoleo.getId());

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
			pageName = "串并案";
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

		UserRole userRole = userRoleService.loadById(userRoleo.getId());
		injurycase.setUserRole(userRole);// 设置录入人员
		injurycase.setJoinDate(DateTimeKit.getLocalDate());// 设置录入时间
		injurycase.setHandleState(1);// 初始化处理状态

		if (picture1 != null && picture1FileName != null
				&& !picture1FileName.replace(" ", "").equals("")) {
			String imageName = DateTimeKit.getDateRandom()
					+ picture1FileName.substring(picture1FileName.indexOf("."));
			this.upload("/case", imageName, picture1);
			injurycase.setImageCase("case" + "/" + imageName);
		}
		injurycaseService.add(injurycase);

		// 添加当前用户id到部门pids
		if (userRole.getUnit() != null) {
			int uid = userRole.getUnit().getId();
			Unit un = unitService.loadById(uid);

			if (un.getInids() != null && un.getInids() != "") {
				un.setInids(handleIDs(un.getInids(), injurycase.getId() + ""));
			} else {
				un.setInids(injurycase.getId() + ",");
			}
			unitService.update(un);
		}
		arg[0] = "injurycaseAction!list?itype=" + injurycase.getItype();
		arg[1] = "案件管理";
		return "success_child";
	}

	// 文件上传
	public void upload(String fileName, String imageName, File picture)
			throws Exception {
		File saved = new File(ServletActionContext.getServletContext()
				.getRealPath(fileName), imageName);
		InputStream ins = null;
		OutputStream ous = null;
		try {
			saved.getParentFile().mkdirs();
			ins = new FileInputStream(picture);
			ous = new FileOutputStream(saved);
			byte[] b = new byte[1024];
			int len = 0;
			while ((len = ins.read(b)) != -1) {
				ous.write(b, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ous != null)
				ous.close();
			if (ins != null)
				ins.close();
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

		if (injurycase.getImageCase() != null
				&& !injurycase.getImageCase().replace(" ", "").equals("")) {
			File photofile = new File(ServletActionContext.getServletContext()
					.getRealPath("/")
					+ injurycase.getImageCase());
			photofile.delete();
		}

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
			injurycase = injurycaseService.loadById(ids[i]);
			if (injurycase.getImageCase() != null
					&& !injurycase.getImageCase().replace(" ", "").equals("")) {
				File photofile = new File(ServletActionContext
						.getServletContext().getRealPath("/")
						+ injurycase.getImageCase());
				photofile.delete();
			}
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

		medias = mediaService.loadInjurycaseByTypeAndPid(1, id);// 视频文件

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
		if (picture1 != null && picture1FileName != null
				&& !picture1FileName.replace(" ", "").equals("")) {
			String imageName = DateTimeKit.getDateRandom()
					+ picture1FileName.substring(picture1FileName.indexOf("."));
			this.upload("/case", imageName, picture1);
			File photofile = new File(ServletActionContext.getServletContext()
					.getRealPath("/")
					+ injurycase.getImageCase());
			photofile.delete();
			injurycase.setImageCase("case" + "/" + imageName);
		}
		
		if (injurycase.getUserRole() == null) {
			UserRole userRole = userRoleService.loadById(userRoleo.getId());
			injurycase.setUserRole(userRole);// 设置录入人员
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

	/***************************************************************************
	 * 串并案
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public String listcba() throws UnsupportedEncodingException {
		// 登陆验证
		UserRole userRoleo = (UserRole) session.get("userRoleo");
		if (userRoleo == null) {
			return "opsessiongo";
		}

		UserRole userRole = userRoleService.loadById(userRoleo.getId());

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
		// 总记录数
		totalCount = injurycaseService.getTotalCount(con, convalue, userRole,
				queryState, starttime, endtime);
		// 总页数
		pageCount = injurycaseService.getPageCount(totalCount, 9);
		if (page > pageCount && pageCount != 0) {
			page = pageCount;
		}
		// 所有当前页记录对象
		injurycases = injurycaseService.queryList(con, convalue, userRole,
				page, 9, queryState, starttime, endtime);
		
		if(injurycases!=null&&injurycases.size()>0)
		{
			injurycaseVOs = new ArrayList<InjurycaseVO>();
			for (int i = 0; i < injurycases.size(); i++) {
				InjurycaseVO injurycaseVO = new InjurycaseVO();
				int vNumber = 0;
				int iNumber = 0;
				injurycaseVO.setId(injurycases.get(i).getId());
				injurycaseVO.setCaseName(injurycases.get(i).getCaseName());
				injurycaseVO.setHandleState(injurycases.get(i).getHandleState());
				injurycaseVO.setImageCase(injurycases.get(i).getImageCase());
				injurycaseVO.setIsRelated(injurycases.get(i).getIsRelated());
				injurycaseVO.setSeries(injurycases.get(i).getSeries());
				injurycaseVO.setStartTime(injurycases.get(i).getStartTime());
				injurycaseVO.setUserRole(injurycases.get(i).getUserRole());
				for(Media media:injurycases.get(i).getMedias())
				{
					if(media.getMtype()==1)
					{
						vNumber++;
					}else if(media.getMtype()==0)
					{
						iNumber++;
					}
				}
				injurycaseVO.setVideoNumber(vNumber);
				injurycaseVO.setImageNumher(iNumber);
				injurycaseVOs.add(injurycaseVO);
			}
		}
		return "listcba";
	}

	/**
	 * 跳转到修改页面
	 * 
	 * @return
	 */
	public String loadcba() throws Exception {
		UserRole userRoleo = (UserRole) session.get("userRoleo");
		if (userRoleo == null) {
			return "opsessiongo";
		}
		pageTileName = selectTileName(3);

		injurycase = injurycaseService.queryInjurycaseById(id);// 当前修改案件的id

		mediaVideos = mediaService.loadInjurycaseByTypeAndPid(1, id);// 视频文件

		mediaImages = mediaService.loadInjurycaseByTypeAndPid(0, id);// 图像文件

		injurycaseSeries = injurycaseService.queryInjurycaseBySeries(injurycase
				.getSeries(), id);// 获得同系列案件(已串并案)
		getInjurycaseByKeyword(keyword, id);

		return "loadcba";

	}

	// 查询关键字获得未串并的案件
	private List<Injurycase> getInjurycaseByKeyword(String keyword, int id) {
		injurycases = injurycaseService.queryInjurycaseByKeyword(keyword, id);// 获得模糊查询其他的案件

		if (injurycases != null && injurycases.size() >= 6) {
			injurycases = injurycases.subList(0, 6);// 获取前五条记录
		}
		return injurycases;

	}

	/**
	 * 案件串并操作
	 * 
	 * @return
	 */
	public String handleInjurycaseSeries() throws Exception {

		if (series != null && !series.equals("")) {
			series = URLDecoder.decode(series, "utf-8");
		}
		// ---------------------------修改当前案件串并属性
		injurycase = injurycaseService.loadById(id);
		if (injurycase != null) {
			if (injurycase.getIsRelated() == null
					|| injurycase.getIsRelated() != 1) {
				injurycase.setIsRelated(1);
			}
			if (injurycase.getSeries() == null
					|| !injurycase.getSeries().equals(series)) {
				injurycase.setSeries(series);
			}
			injurycaseService.update(injurycase);
		}

		// ---------------------------修改其他案件串并属性
		int[] ids = ConvertUtil.StringtoInt(checkedIDs);
		for (int i = 0; i < ids.length; i++) {
			injurycase = injurycaseService.loadById(ids[i]);
			if (injurycase.getIsRelated() == null
					|| injurycase.getIsRelated() != 1) {
				injurycase.setIsRelated(1);
			}
			if (injurycase.getSeries() == null
					|| !injurycase.getSeries().equals(series)) {
				injurycase.setSeries(series);
			}
			injurycaseService.update(injurycase);
		}

		AjaxMsgVO msgVO = new AjaxMsgVO();
		msgVO.setMessage("串并案操作成功成功.");
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

	public void setUnitService(IUnitService unitService) {
		this.unitService = unitService;
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

	public void setUserRoleService(IUserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}

	public List<UnitVO> getUnitVOs() {
		return unitVOs;
	}

	public File getPicture1() {
		return picture1;
	}

	public void setPicture1(File picture1) {
		this.picture1 = picture1;
	}

	public String getPicture1ContentType() {
		return picture1ContentType;
	}

	public void setPicture1ContentType(String picture1ContentType) {
		this.picture1ContentType = picture1ContentType;
	}

	public String getPicture1FileName() {
		return picture1FileName;
	}

	public void setPicture1FileName(String picture1FileName) {
		this.picture1FileName = picture1FileName;
	}

	public IMediaService getMediaService() {
		return mediaService;
	}

	public void setMediaService(IMediaService mediaService) {
		this.mediaService = mediaService;
	}

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	public List<Media> getMedias() {
		return medias;
	}

	public void setMedias(List<Media> medias) {
		this.medias = medias;
	}

	public List<Media> getMediaVideos() {
		return mediaVideos;
	}

	public void setMediaVideos(List<Media> mediaVideos) {
		this.mediaVideos = mediaVideos;
	}

	public List<Media> getMediaImages() {
		return mediaImages;
	}

	public void setMediaImages(List<Media> mediaImages) {
		this.mediaImages = mediaImages;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public List<Injurycase> getInjurycaseSeries() {
		return injurycaseSeries;
	}

	public void setInjurycaseSeries(List<Injurycase> injurycaseSeries) {
		this.injurycaseSeries = injurycaseSeries;
	}

	public List<InjurycaseVO> getInjurycaseVOs() {
		return injurycaseVOs;
	}

	public void setInjurycaseVOs(List<InjurycaseVO> injurycaseVOs) {
		this.injurycaseVOs = injurycaseVOs;
	}
	
	

}
