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
import java.util.List;
import java.util.Map;

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
import com.yz.model.AnalyzeMan;
import com.yz.model.Clue;
import com.yz.model.CommonClue;
import com.yz.model.ContrastMan;
import com.yz.model.GamblingCriminalMan;
import com.yz.model.GuiltSafeguardMan;
import com.yz.model.Injurycase;
import com.yz.model.Person;
import com.yz.model.Pnotice;
import com.yz.model.Successexample;
import com.yz.model.Troubleshooting;
import com.yz.model.Unit;
import com.yz.model.UserRole;
import com.yz.service.IAnalyzeManService;
import com.yz.service.IClueService;
import com.yz.service.ICommonClueService;
import com.yz.service.IContrastManService;
import com.yz.service.IGamblingCriminalManService;
import com.yz.service.IGuiltSafeguardManService;
import com.yz.service.IInjurycaseService;
import com.yz.service.IPersonService;
import com.yz.service.IPnoticeService;
import com.yz.service.ISuccessexampleService;
import com.yz.service.ITroubleshootingService;
import com.yz.service.IUnitService;
import com.yz.service.IUserRoleService;
import com.yz.util.ConvertUtil;
import com.yz.util.DateTimeKit;
import com.yz.vo.AjaxMsgVO;
import com.yz.vo.ClueNumberVO;
import com.yz.vo.InjurycaseNumberVO;
import com.yz.vo.PersonNumberVO;

@Component("userRoleAction")  
@Scope("prototype")
public class UserRoleAction extends ActionSupport implements RequestAware,
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
	private int con;
	private String convalue;
	private int status;// 按状态
	private int pid;// 按用户id
	
	
	// 登陆
	private String username;
	private String password;

	//批量删除
	private String checkedIDs;
	
	//service层对象
	private IUnitService unitService;
	private IUserRoleService userRoleService;
	private IPnoticeService pnoticeService;
	private ISuccessexampleService successexampleService;
	private IGamblingCriminalManService gamblingCriminalManService;
	private IGuiltSafeguardManService guiltSafeguardManService;
	private IAnalyzeManService analyzeManService;
	private IContrastManService contrastManService;
	private ICommonClueService commonClueService;
	
	private ITroubleshootingService troubleshootingService;
	private IPersonService personService;
	private IClueService clueService;
	private IInjurycaseService injurycaseService;

	//单个对象
	private UserRole userRole;

	//list对象
	private List<UserRole> userRoles;
	private List<Unit> units;
	private List<Pnotice> pnotices;
	private List<Successexample> successexamples;
	private List<Person> persons;
	private List<GamblingCriminalMan> gamblingCriminalMans;
	private List<GuiltSafeguardMan> guiltSafeguardMans;
	private List<AnalyzeMan> analyzeMans;
	private List<ContrastMan> contrastMans;
	private List<CommonClue> commonClues;
	
	private List<Clue> clues;
	private List<Injurycase> injurycases;
	
	
	private List<Troubleshooting> troubleshootings;
	
	//处理主界面人员数量信息
	private List<PersonNumberVO> personNumberVOs;
	private List<InjurycaseNumberVO> injurycaseNumberVOs;
	private List<ClueNumberVO> clueNumberVOs;

	
	//个人资料新旧密码
	private String password1;
	private String password2;

	/**
	 * 用户登陆
	 * @throws Exception 
	 */
	public String login() throws Exception {
		
		if(checkDatebase())//检查数据库
		{
			UserRole userRoleTest = new UserRole();
			userRoleTest.setNumber("测试人员");
			userRoleTest.setUsername("test");
			userRoleTest.setPassword("test");
			userRoleTest.setUserLimit(1);
			userRoleService.add(userRoleTest);
			session.put("userRoleo", userRoleTest);
			return "loginSucc";
		}
		if (username == null || username.equals("") || password == null
				|| password.equals("")) {
			String loginfail = "用户名或密码不能为空";
			request.put("loginFail", loginfail);
			return "adminLogin";
		}
		UserRole userRoleLogin = userRoleService.userRolelogin(username, password);
		if (userRoleLogin == null) {
			String loginfail = "用户名或密码输入有误";
			request.put("loginFail", loginfail);
			return "adminLogin";
		} else {
			//设置登陆时间
			if(session.get("userRoleo")==null)
			{
				setLoginTime(userRoleLogin);
				session.put("userRoleo", userRoleLogin);
			}
			//checkIP();//检查IP地址
			return "loginSucc";
		}
	}
	
	public String welcome()
	{
		// 登陆验证
		UserRole userRoleo = (UserRole) session.get("userRoleo");
		if (userRoleo == null) {
			return "opsessiongo";
		}
		UserRole  userRoleWelcome = userRoleService.loadById(userRoleo.getId());
		//欢迎界面
		pnotices = pnoticeService.getPnotices();
		successexamples = successexampleService.getSuccessexamples();//所有
		troubleshootings = troubleshootingService.getTroubleshootings();//所有
		
		persons = personService.getPersons();
		setMainPersonJspNumber(userRoleWelcome);
		setMainInjurycaseJspNumber(userRoleWelcome);
		setMainClueJspNumber(userRoleWelcome);
		
		return "welcome";
	}

	
	private void setMainClueJspNumber(UserRole userRole) {
		// TODO Auto-generated method stub
		clueNumberVOs = new ArrayList<ClueNumberVO>();
		
		for(int i =1;i<=1;i++)
		{
			ClueNumberVO clueNumberVO = new ClueNumberVO();
			clueNumberVO.setCtype(i);
			int number1 = getCurrentClueVONumber(i,1,userRole);
			int number2 = getCurrentClueVONumber(i,2,userRole);
			int number3 = getCurrentClueVONumber(i,3,userRole);
			int number4 = number1+number2+number3;
			clueNumberVO.setNumber1(number1);
			clueNumberVO.setNumber2(number2);
			clueNumberVO.setNumber3(number3);
			clueNumberVO.setTotalNumber(number4);
			clueNumberVOs.add(clueNumberVO);
		}
	}

	

	private void setMainInjurycaseJspNumber(UserRole userRole) {
		// TODO Auto-generated method stub
		injurycaseNumberVOs = new ArrayList<InjurycaseNumberVO>();
		
		for(int i =1;i<=3;i++)
		{
			InjurycaseNumberVO injurycaseNumberVO = new InjurycaseNumberVO();
			injurycaseNumberVO.setItype(i);
			int number1 = getCurrentInjurycaseVONumber(i,1,userRole);
			int number2 = getCurrentInjurycaseVONumber(i,2,userRole);
			int number3 = getCurrentInjurycaseVONumber(i,3,userRole);
			int number4 = number1+number2+number3;
			injurycaseNumberVO.setNumber1(number1);
			injurycaseNumberVO.setNumber2(number2);
			injurycaseNumberVO.setNumber3(number3);
			injurycaseNumberVO.setTotalNumber(number4);
			injurycaseNumberVOs.add(injurycaseNumberVO);
		}
	}

	//设置人员前端显示
	private void setMainPersonJspNumber(UserRole userRole) {
		// TODO Auto-generated method stub
		personNumberVOs = new ArrayList<PersonNumberVO>();
		
		for(int i =1;i<=14;i++)
		{
			PersonNumberVO personNumberVO = new PersonNumberVO();
			personNumberVO.setType(i);
			int number1 = getCurrentVONumber(i,1,userRole);
			int number2 = getCurrentVONumber(i,2,userRole);
			int number3 = getCurrentVONumber(i,3,userRole);
			int number4 = number1+number2+number3;
			personNumberVO.setNumber1(number1);
			personNumberVO.setNumber2(number2);
			personNumberVO.setNumber3(number3);
			personNumberVO.setTotalNumber(number4);
			personNumberVOs.add(personNumberVO);
		}
	}
	
	private int getCurrentVONumber(int type,int handleState,UserRole userRole)
	{
		persons = personService.getPersonsByTypeAndHandleState(type,handleState,userRole);
		if(persons!=null)
		{
			return persons.size();
		}else
		{
			return 0;
		}
	
	}
	
	private int getCurrentClueVONumber(int ctype, int handleState,UserRole userRole) {
		// TODO Auto-generated method stub
		clues = clueService.getCluesByTypeAndHandleState(ctype,handleState,userRole);
		if(clues!=null)
		{
			return clues.size();
		}else
		{
			return 0;
		}
	}
	
	private int getCurrentInjurycaseVONumber(int itype,int handleState,UserRole userRole)
	{
		injurycases = injurycaseService.getInjurycaseByTypeAndHandleState(itype,handleState,userRole);
		if(injurycases!=null)
		{
			return injurycases.size();
		}else
		{
			return 0;
		}
	
	}

	//设置登陆时间
	private void setLoginTime(UserRole userRoleLogin) {
		// TODO Auto-generated method stub
		if(userRoleLogin.getBeforeLoginTime()==""||userRoleLogin.getBeforeLoginTime()==null)
		{
			userRoleLogin.setBeforeLoginTime(DateTimeKit.getLocalTime());
		}else
		{
			userRoleLogin.setBeforeLoginTime(userRoleLogin.getCurrentLoginTime());
		}
		userRoleLogin.setCurrentLoginTime(DateTimeKit.getLocalTime());
		userRoleService.update(userRoleLogin);
	}

	private boolean checkDatebase() {
		// TODO Auto-generated method stub
		units = unitService.getUnits();
		userRoles = userRoleService.getUserRoles();
		if(units.size()==0&&userRoles.size()==0)
		{
			return true;
		}else
		{
			return false;
		}
	}

	private void checkIP() {
		// TODO Auto-generated method stub
		//String ip = getIpAddr(req);
	}

	/*
	 * 获取IP地址
	 */
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 用户注销
	 */
	public String logout() {
		session.clear();
		return "adminLogin";
	}


	/**
	 * 用户管理
	 */
	public String list() throws Exception {
		// 判断会话是否失效
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
		// 总记录数
		totalCount = userRoleService.getTotalCount(con, convalue, userRoleo);
		// 总页数
		pageCount = userRoleService.getPageCount(totalCount, size);
		if (page > pageCount && pageCount != 0) {
			page = pageCount;
		}
		// 所有当前页记录对象
		userRoles = userRoleService.queryList(con, convalue, userRoleo, page, size);
		return "list";
	}


	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	public String goToAdd() {
		
		units = unitService.getUnits();
		return "add";
	}

	/**
	 * 添加
	 * 
	 * @return
	 * @throws Exception
	 */

	public String add() throws Exception {
		//判断回话是否失效
		UserRole userRoleo = (UserRole) session.get("userRoleo");
		if (userRoleo == null) {
			return "opsessiongo_child";
		}
		if(picture!=null&&pictureFileName!=null&&!pictureFileName.replace(" ", "").equals("")){
			String imageName=DateTimeKit.getDateRandom()+pictureFileName.substring(pictureFileName.indexOf("."));
			this.upload("/userRole",imageName,picture);
			userRole.setPhoto("userRole"+"/"+imageName);
		}
		userRoleService.add(userRole);

		arg[0] = "userRoleAction!list";
		arg[1] = "用户管理";
		return "success_child";
	}
	
	//上传照片
	private File picture;
	private String pictureContentType;
	private String pictureFileName;
	//文件上传
	public void upload(String fileName,String imageName,File picture) throws Exception{
		File saved=new File(ServletActionContext.getServletContext().getRealPath(fileName),imageName);
		InputStream ins=null;
		OutputStream ous=null;
		try {
			saved.getParentFile().mkdirs();
			ins=new FileInputStream(picture);
			ous=new FileOutputStream(saved);
			byte[] b=new byte[1024];
			int len = 0;
			while((len=ins.read(b))!=-1){
				ous.write(b,0,len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(ous!=null)
				ous.close();
			if(ins!=null) 
				ins.close();
		}
	}
	

	/**
	 * 删除一
	 * 
	 * @return
	 */
	public String delete() {
		// 判断会话是否失效
		UserRole userRoleo = (UserRole) session.get("userRole");
		if (userRoleo == null) {
			return "opsessiongo";
		}
		
		userRole = userRoleService.loadById(id);
		//删除照片
		File photofile=new File(ServletActionContext.getServletContext().getRealPath("/")+userRole.getPhoto());
		photofile.delete();
		
		userRoleService.delete(userRole);
		
		userRoleService.deleteById(id);
		arg[0] = "userRoleAction!list";
		arg[1] = "用户管理";
		return SUCCESS;
	}
	
	/**
	 * 删除二(批量删除)
	 * 
	 * @return
	 */
	public String deleteUserRoles() {
		
		int[] ids = ConvertUtil.StringtoInt(checkedIDs);
		for(int i=0;i<ids.length;i++)
		{
			userRole = userRoleService.loadById(ids[i]);
			//删除照片
			File photofile=new File(ServletActionContext.getServletContext().getRealPath("/")+userRole.getPhoto());
			photofile.delete();
			
			userRoleService.delete(userRole);
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
	public String load(){
		
		userRole = userRoleService.loadById(id);
		units = unitService.getUnits();
		return "load";
	}
	
	/**
	 * 修改
	 * 
	 * @return
	 */
	public String update() throws Exception {
		// 判断会话是否失效
		UserRole userRoleo = (UserRole) session.get("userRoleo");
		if (userRoleo == null) {
			return "opsessiongo_child";
		}
		if(picture!=null&&pictureFileName!=null&&!pictureFileName.replace(" ", "").equals("")){
			String imageName=DateTimeKit.getDateRandom()+pictureFileName.substring(pictureFileName.indexOf("."));
			this.upload("/userRole",imageName,picture);
			File photofile=new File(ServletActionContext.getServletContext().getRealPath("/")+userRole.getPhoto());
			photofile.delete();
			userRole.setPhoto("userRole"+"/"+imageName);
		}

		userRoleService.update(userRole);
		arg[0] = "userRoleAction!list";
		arg[1] = "用户管理";
		return "success_child";
	}
	
	
	

	/**
	 * 跳转到修改秒页面
	 * 
	 * @return
	 */
	public String loadPassword() throws Exception {
		UserRole userRoleo = (UserRole) session.get("userRole");
		if (userRoleo == null) {
			return "opsessiongo";
		}
		password = userRoleo.getPassword();
		return "password";
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	public String updatePassword() throws Exception {
		// 判断会话是否失效
		UserRole userRoleo = (UserRole) session.get("userRole");
		if (userRoleo == null) {
			return "opsessiongo";
		}
		userRoleo.setPassword(password);
		userRoleService.update(userRoleo);
		arg[0] = "userRoleAction!list";
		arg[1] = "用户管理";
		return SUCCESS;
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
		userRole = userRoleService.loadById(id);
		return "view";
	}
	
	/**
	 * 个人资料
	 */
	public String currentUserRole()
	{
		UserRole userRoleo = (UserRole) session.get("userRoleo");
		if (userRoleo == null) {
			return "opsessiongo";
		}
		userRole = userRoleService.loadById(userRoleo.getId());;
		units = unitService.getUnits();
		return "currentUserRole";
	}
	
	public String updateCurrentUserRole() throws Exception
	{
		UserRole userRoleo = (UserRole) session.get("userRoleo");
		if (userRoleo == null) {
			return "opsessiongo";
		}
		if(picture!=null&&pictureFileName!=null&&!pictureFileName.replace(" ", "").equals("")){
			String imageName=DateTimeKit.getDateRandom()+pictureFileName.substring(pictureFileName.indexOf("."));
			this.upload("/userRole",imageName,picture);
			File photofile=new File(ServletActionContext.getServletContext().getRealPath("/")+userRole.getPhoto());
			photofile.delete();
			userRole.setPhoto("userRole"+"/"+imageName);
		}
		if(password1!=null&&!password1.replace(" ", "").equals("")&&password2!=null&&!password2.replace(" ", "").equals(""))
		{
			userRole.setPassword(password1);
		}
		userRoleService.update(userRole);
		arg[0] = "userRoleAction!currentUserRole";
		arg[1] = "个人资料";
		return SUCCESS;
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

	public IUserRoleService getUserRoleService() {
		return userRoleService;
	}

	@Resource
	public void setUserRoleService(IUserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getCheckedIDs() {
		return checkedIDs;
	}

	public void setCheckedIDs(String checkedIDs) {
		this.checkedIDs = checkedIDs;
	}

	public IUnitService getUnitService() {
		return unitService;
	}

	@Resource
	public void setUnitService(IUnitService unitService) {
		this.unitService = unitService;
	}

	public List<Unit> getUnits() {
		return units;
	}

	public void setUnits(List<Unit> units) {
		this.units = units;
	}

	public File getPicture() {
		return picture;
	}

	public void setPicture(File picture) {
		this.picture = picture;
	}

	public String getPictureContentType() {
		return pictureContentType;
	}

	public void setPictureContentType(String pictureContentType) {
		this.pictureContentType = pictureContentType;
	}

	public String getPictureFileName() {
		return pictureFileName;
	}

	public void setPictureFileName(String pictureFileName) {
		this.pictureFileName = pictureFileName;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public IPnoticeService getPnoticeService() {
		return pnoticeService;
	}

	@Resource
	public void setPnoticeService(IPnoticeService pnoticeService) {
		this.pnoticeService = pnoticeService;
	}

	public List<Pnotice> getPnotices() {
		return pnotices;
	}

	public void setPnotices(List<Pnotice> pnotices) {
		this.pnotices = pnotices;
	}

	public ISuccessexampleService getSuccessexampleService() {
		return successexampleService;
	}

	@Resource
	public void setSuccessexampleService(
			ISuccessexampleService successexampleService) {
		this.successexampleService = successexampleService;
	}

	public IGamblingCriminalManService getGamblingCriminalManService() {
		return gamblingCriminalManService;
	}

	@Resource
	public void setGamblingCriminalManService(
			IGamblingCriminalManService gamblingCriminalManService) {
		this.gamblingCriminalManService = gamblingCriminalManService;
	}

	public IGuiltSafeguardManService getGuiltSafeguardManService() {
		return guiltSafeguardManService;
	}

	@Resource
	public void setGuiltSafeguardManService(
			IGuiltSafeguardManService guiltSafeguardManService) {
		this.guiltSafeguardManService = guiltSafeguardManService;
	}

	public IAnalyzeManService getAnalyzeManService() {
		return analyzeManService;
	}

	@Resource
	public void setAnalyzeManService(IAnalyzeManService analyzeManService) {
		this.analyzeManService = analyzeManService;
	}

	public IContrastManService getContrastManService() {
		return contrastManService;
	}

	@Resource
	public void setContrastManService(IContrastManService contrastManService) {
		this.contrastManService = contrastManService;
	}

	public ICommonClueService getCommonClueService() {
		return commonClueService;
	}

	@Resource
	public void setCommonClueService(ICommonClueService commonClueService) {
		this.commonClueService = commonClueService;
	}

	public List<Successexample> getSuccessexamples() {
		return successexamples;
	}

	public void setSuccessexamples(List<Successexample> successexamples) {
		this.successexamples = successexamples;
	}

	public List<GamblingCriminalMan> getGamblingCriminalMans() {
		return gamblingCriminalMans;
	}

	public void setGamblingCriminalMans(
			List<GamblingCriminalMan> gamblingCriminalMans) {
		this.gamblingCriminalMans = gamblingCriminalMans;
	}

	public List<GuiltSafeguardMan> getGuiltSafeguardMans() {
		return guiltSafeguardMans;
	}

	public void setGuiltSafeguardMans(List<GuiltSafeguardMan> guiltSafeguardMans) {
		this.guiltSafeguardMans = guiltSafeguardMans;
	}

	public List<AnalyzeMan> getAnalyzeMans() {
		return analyzeMans;
	}

	public void setAnalyzeMans(List<AnalyzeMan> analyzeMans) {
		this.analyzeMans = analyzeMans;
	}

	public List<ContrastMan> getContrastMans() {
		return contrastMans;
	}

	public void setContrastMans(List<ContrastMan> contrastMans) {
		this.contrastMans = contrastMans;
	}

	public List<CommonClue> getCommonClues() {
		return commonClues;
	}

	public void setCommonClues(List<CommonClue> commonClues) {
		this.commonClues = commonClues;
	}

	public ITroubleshootingService getTroubleshootingService() {
		return troubleshootingService;
	}

	@Resource
	public void setTroubleshootingService(
			ITroubleshootingService troubleshootingService) {
		this.troubleshootingService = troubleshootingService;
	}

	public List<Troubleshooting> getTroubleshootings() {
		return troubleshootings;
	}

	public void setTroubleshootings(List<Troubleshooting> troubleshootings) {
		this.troubleshootings = troubleshootings;
	}

	public IPersonService getPersonService() {
		return personService;
	}

	@Resource
	public void setPersonService(IPersonService personService) {
		this.personService = personService;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public List<PersonNumberVO> getPersonNumberVOs() {
		return personNumberVOs;
	}

	public void setPersonNumberVOs(List<PersonNumberVO> personNumberVOs) {
		this.personNumberVOs = personNumberVOs;
	}

	public IClueService getClueService() {
		return clueService;
	}

	@Resource
	public void setClueService(IClueService clueService) {
		this.clueService = clueService;
	}

	public IInjurycaseService getInjurycaseService() {
		return injurycaseService;
	}

	@Resource
	public void setInjurycaseService(IInjurycaseService injurycaseService) {
		this.injurycaseService = injurycaseService;
	}

	public List<Clue> getClues() {
		return clues;
	}

	public void setClues(List<Clue> clues) {
		this.clues = clues;
	}

	public List<Injurycase> getInjurycases() {
		return injurycases;
	}

	public void setInjurycases(List<Injurycase> injurycases) {
		this.injurycases = injurycases;
	}

	public List<InjurycaseNumberVO> getInjurycaseNumberVOs() {
		return injurycaseNumberVOs;
	}

	public void setInjurycaseNumberVOs(List<InjurycaseNumberVO> injurycaseNumberVOs) {
		this.injurycaseNumberVOs = injurycaseNumberVOs;
	}

	public List<ClueNumberVO> getClueNumberVOs() {
		return clueNumberVOs;
	}

	public void setClueNumberVOs(List<ClueNumberVO> clueNumberVOs) {
		this.clueNumberVOs = clueNumberVOs;
	}
	
	

}
