package com.yz.action;

import com.opensymphony.xwork2.ActionSupport;
import com.yz.auth.AuthObject;
import com.yz.model.*;
import com.yz.service.*;
import com.yz.util.AjaxMsgUtil;
import com.yz.util.DateTimeKit;
import com.yz.util.InfoType;
import com.yz.vo.AjaxMsgVO;
import com.yz.vo.UnitVO;
import net.sf.json.JSONArray;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
	@Resource
	private IUserRoleService userRoleService;
	@Resource
	private IMediaService mediaService;

	@Resource(name = "authObject")
	private AuthObject authObject;

	// 扫描件图片
	private File picture1;
	private String picture1ContentType;
	private String picture1FileName;

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
	private List<Media> medias;

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

				if (number.equals("371402020000")) {
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
			changeHandleStateAndJudgeIndex(judge.getPerson().getId(),
					InfoType.PERSON, judge.getJtype());
			unitService.updateJudgeUnit(judge.getPerson().getId() + "",
					InfoType.PERSON, 1);
			// setUnitPids(userRoleo, judge);

		}

		if (judge.getInjurycase() != null) {
			changeHandleStateAndJudgeIndex(judge.getInjurycase().getId(),
					InfoType.CASE, judge.getJtype());
			unitService.updateJudgeUnit(judge.getInjurycase().getId() + "",
					InfoType.CASE, 1);
			// setUnitInids(userRoleo, judge);

		}
		if (judge.getClue() != null) {
			changeHandleStateAndJudgeIndex(judge.getClue().getId(),
					InfoType.CLUE, judge.getJtype());
			unitService.updateJudgeUnit(judge.getClue().getId() + "",
					InfoType.CLUE, 1);
			// setUnitCids(userRoleo, judge);

		}

		if (picture1 != null && picture1FileName != null
				&& !picture1FileName.replace(" ", "").equals("")) {
			String imageName = DateTimeKit.getDateRandom()
					+ picture1FileName.substring(picture1FileName.indexOf("."));
			this.upload("/judge", imageName, picture1);
			judge.setScanImage("/judge" + "/" + imageName);
		}

		judge.setDeadline(DateTimeKit.getLocalDate());
		judge.setIsNew(1);
		judgeService.add(judge);
		return "success_child";
	}

	// 文件上传
	public void upload(String fileName, String imageName, File picture)
			throws Exception {
		File saved = new File(authObject.getFileRoot() + fileName, imageName);
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

	private void changeHandleStateAndJudgeIndex(Integer id, InfoType infoType,
			int currentJtype) {

		switch (infoType) {
		case PERSON:
			Person per = personService.loadById(id);
			if (per != null) {
				if (per.getHandleState() == 1) {
					per.setHandleState(2);
					personService.update(per);
				}
			}
			judges = judgeService.loadByTypeAndPid(currentJtype, judge
					.getPerson().getId());
			break;
		case CASE:
			Injurycase injurycase = injurycaseService.loadById(id);
			if (injurycase != null) {
				if (injurycase.getHandleState() == 1) {
					injurycase.setHandleState(2);
					injurycaseService.update(injurycase);
				}
			}
			judges = judgeService.loadInjurycaseByTypeAndPid(currentJtype,
					judge.getInjurycase().getId());
			break;
		case CLUE:
			Clue clue = clueService.loadById(id);
			if (clue != null) {
				if (clue.getHandleState() == 1) {
					clue.setHandleState(2);
					clueService.update(clue);
				}
			}
			judges = judgeService.loadClueByTypeAndPid(currentJtype, judge
					.getClue().getId());
			break;
		default:
			break;
		}

		if (judges != null) {
			judge.setIndexNumber(judges.size() + 1);
		}
	}

	public String deleteJudge() throws Exception {
		judge = judgeService.loadById(jid);
		if (judge.getScanImage() != null
				&& !judge.getScanImage().replace(" ", "").equals("")) {
			File photofile = new File(authObject.getFileRoot()
					+ judge.getScanImage());
			photofile.delete();
		}
		judgeService.delete(judge);
		AjaxMsgUtil.outputJSONObjectToAjax(response,new AjaxMsgVO("删除成功."));
		return null;
	}

	public String load() {

		medias = mediaService.loadJudgeByJid(jid);// 其他文件
		judge = judgeService.loadById(jid);
		return "load";
	}

	public String update() throws Exception {

		if (picture1 != null && picture1FileName != null
				&& !picture1FileName.replace(" ", "").equals("")) {
			String imageName = DateTimeKit.getDateRandom()
					+ picture1FileName.substring(picture1FileName.indexOf("."));
			this.upload("/judge", imageName, picture1);
			File photofile = new File(authObject.getFileRoot()
					+ judge.getScanImage());
			photofile.delete();
			judge.setScanImage("/judge" + "/" + imageName);
		}
		judgeService.update(judge);
		return "success_child";
	}

	/**
	 * 获取新增研判的事项信息
	 * 
	 * @return
	 */
	public String getNewJudges() {

		// 登陆验证
		UserRole userRoleo = (UserRole) session.get("userRoleo");
		if (userRoleo == null) {
			return "opsessiongo";
		}

		UserRole userRole = userRoleService.getUserRoleById(userRoleo.getId());

		Unit unit = unitService.getUnitById(userRole.getUnit().getId());

		List<Judge> judges = judgeService.getNewJudges();

		List<AjaxMsgVO> ajaxMsgVOList = new ArrayList<AjaxMsgVO>();

		if (judges != null && judges.size() > 0) {
			for (Judge judge : judges) {
				AjaxMsgVO judgeVO = new AjaxMsgVO();

				judgeVO.setJoinDate(judge.getReportTime());
				if (judge.getPerson() != null) {

					// 查看权限
					if (isContainID(unit.getPids(), judge.getPerson().getId()
							.toString())
							|| userRole.getUserLimit() == 2
							|| unit.getNumber().equals("371402020000")) {

						judgeVO.setId(judge.getPerson().getId());
						judgeVO.setName(judge.getPerson().getName());
						judgeVO.setType(judge.getPerson().getType());
						switch (judge.getPerson().getType()) {
						case 0:
							judgeVO.setType(0);
							judgeVO.setTypeName("未知人员");
							break;
						case 1:
							judgeVO.setTypeName("赌博人员");
							break;
						case 2:
							judgeVO.setTypeName("涉恶人员");
							break;
						case 3:
							judgeVO.setTypeName("涉黄人员");
							break;
						case 4:
							judgeVO.setTypeName("食药环人员");
							break;
						case 5:
							judgeVO.setTypeName("涉毒人员");
							break;
						case 6:
							judgeVO.setTypeName("留置盘问人员");
							break;
						case 7:
							judgeVO.setTypeName("侵财人员");
							break;
						case 8:
							judgeVO.setTypeName("刑事传唤人员");
							break;
						case 9:
							judgeVO.setTypeName("负案在逃人员");
							break;
						case 10:
							judgeVO.setTypeName("维稳人员");
							break;
						case 11:
							judgeVO.setTypeName("失踪人员");
							break;
						case 12:
							judgeVO.setTypeName("侵财人员分析");
							break;
						case 13:
							judgeVO.setTypeName("技术比中人员");
							break;
						case 14:
							judgeVO.setTypeName("前科人员");
							break;
						case 15:
							judgeVO.setTypeName("其他人员");
							break;
						default:
							judgeVO.setTypeName("其他人员");
							break;
						}
					}

				}

				if (judge.getInjurycase() != null) {
					// 查看权限
					if (isContainID(unit.getInids(), judge.getInjurycase()
							.getId().toString())
							|| userRole.getUserLimit() == 2
							|| unit.getNumber().equals("371402020000")) {

						judgeVO.setId(judge.getInjurycase().getId());
						judgeVO.setName(judge.getInjurycase().getCaseName());
						switch (judge.getInjurycase().getItype()) {
						case 0:
							judgeVO.setType(0);
							judgeVO.setTypeName("未知案件");
							break;
						case 1:
							judgeVO.setType(16);
							judgeVO.setTypeName("刑事案件");
							break;
						case 2:
							judgeVO.setType(17);
							judgeVO.setTypeName("重伤案件");
							break;
						case 3:
							judgeVO.setType(18);
							judgeVO.setTypeName("团伙系列案件");
							break;
						case 4:
							judgeVO.setType(19);
							judgeVO.setTypeName("行政案件");
							break;
						default:
							break;
						}

					}

				}

				if (judge.getClue() != null) {

					// 查看权限
					if (isContainID(unit.getCids(), judge.getClue().getId()
							.toString())
							|| userRole.getUserLimit() == 2
							|| unit.getNumber().equals("371402020000")) {
						judgeVO.setId(judge.getClue().getId());
						judgeVO.setName(judge.getClue().getTitle());
						switch (judge.getClue().getCtype()) {
						case 0:
							judgeVO.setType(0);
							judgeVO.setTypeName("未知线索");
							break;
						case 1:
							judgeVO.setType(20);
							judgeVO.setTypeName("刑侦线索");
							break;
						case 2:
							judgeVO.setType(21);
							judgeVO.setTypeName("普通线索");
							break;
						default:
							break;
						}

					}
				}
				ajaxMsgVOList.add(judgeVO);
			}
		}

		AjaxMsgUtil.outputJSONArrayToAjax(response,ajaxMsgVOList);
		return null;
	}

	private boolean isContainID(String ids, String id) {

		if (ids == null || ids.replace(" ", "").equals("")) {
			return false;
		}

		String[] idString = ids.split(",");

		List<String> list = Arrays.asList(idString);

		return list.contains(id);

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

	public IUserRoleService getUserRoleService() {
		return userRoleService;
	}

	public void setUserRoleService(IUserRoleService userRoleService) {
		this.userRoleService = userRoleService;
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

	public List<Media> getMedias() {
		return medias;
	}

	public void setMedias(List<Media> medias) {
		this.medias = medias;
	}

}
