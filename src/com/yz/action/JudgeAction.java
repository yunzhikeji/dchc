package com.yz.action;

import com.yz.model.*;
import com.yz.service.*;
import com.yz.util.AjaxMsgUtil;
import com.yz.util.DateTimeKit;
import com.yz.vo.AjaxMsgVO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component("judgeAction")
@Scope("prototype")
public class JudgeAction extends BaseAction{

	// 条件
	private int id;
	private int pid;// 人员id
	private int inid; // 案件id
	private int cid;// 刑侦线索

	private int jid;// 研判id

	// service层对象
	@Resource
	private PersonService personService;
	@Resource
	private InjurycaseService injurycaseService;
	@Resource
	private ClueService clueService;
	@Resource
	private JudgeService judgeService;
	@Resource
	private UnitService unitService;
	@Resource
	private UserRoleService userRoleService;
	@Resource
	private MediaService mediaService;
	@Resource
	private FileService fileService;


	// 单个表对象
	private Person person;
	private Injurycase injurycase;
	private Clue clue;

	private Judge judge;
	private Unit unit;

	// list表对象
	private List<Person> persons;
	private List<Judge> judges;
	private List<Media> medias;

	// 文件上传
	private File[] file;
	private String[] fileContentType;
	private String[] fileFileName;


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



	public String add() throws Exception {

		judge.setScanImage(fileService.upload(file, fileFileName, fileContentType, "judge"));
		judge.setDeadline(DateTimeKit.getLocalDate());
		judge.setIsNew(1);
		judgeService.addAndChangeUnit(judge);
		return "success_child";
	}


	public String commit() throws Exception {
		return "success_child";
	}


	public String deleteJudge() throws Exception {

		judge = judgeService.loadById(jid);

		fileService.deleteFileBySrc(judge.getScanImage());

		judgeService.delete(judge);

		AjaxMsgUtil.outputJSONObjectToAjax(response, new AjaxMsgVO("删除成功."));
		return null;
	}

	public String load() {

		medias = mediaService.loadJudgeByJid(jid);// 其他文件
		judge = judgeService.loadById(jid);
		return "load";
	}

	public String update() throws Exception {

		fileService.deleteFileBySrc(judge.getScanImage());

		judge.setScanImage(fileService.upload(file, fileFileName, fileContentType, "judge"));

		judgeService.update(judge);
		return "success_child";
	}

	/**
	 * 获取新增研判的事项信息
	 *
	 * @return
	 */
	public String getNewJudges() {

		currentUserRole = (UserRole) session.get("currentUserRole");

		Unit unit = currentUserRole.getUnit();

		List<Judge> judges = judgeService.getNewJudges();

		List<AjaxMsgVO> ajaxMsgVOList = new ArrayList<AjaxMsgVO>();

		if (judges != null && judges.size() > 0) {
			for (Judge judge : judges) {
				AjaxMsgVO judgeVO = new AjaxMsgVO();

				judgeVO.setJoinDate(judge.getReportTime());
				if (judge.getPerson() != null) {
					if (isContainID(unit.getPids(), judge.getPerson().getId()
							.toString())
							|| currentUserRole.getUserLimit() == 2
							|| unit.getNumber().equals(UnitAction.OPERTION_UNIT_NUMBER)) {

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
							|| currentUserRole.getUserLimit() == 2
							|| unit.getNumber().equals(UnitAction.OPERTION_UNIT_NUMBER)) {

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
							|| currentUserRole.getUserLimit() == 2
							|| unit.getNumber().equals(UnitAction.OPERTION_UNIT_NUMBER)) {
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

		AjaxMsgUtil.outputJSONArrayToAjax(response, ajaxMsgVOList);
		return null;
	}


	// private


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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
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

	public int getJid() {
		return jid;
	}

	public void setJid(int jid) {
		this.jid = jid;
	}

	public JudgeService getJudgeService() {
		return judgeService;
	}

	public void setJudgeService(JudgeService judgeService) {
		this.judgeService = judgeService;
	}

	public Judge getJudge() {
		return judge;
	}

	public void setJudge(Judge judge) {
		this.judge = judge;
	}


	public UnitService getUnitService() {
		return unitService;
	}

	public void setUnitService(UnitService unitService) {
		this.unitService = unitService;
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

	public UserRoleService getUserRoleService() {
		return userRoleService;
	}

	public void setUserRoleService(UserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}

	public MediaService getMediaService() {
		return mediaService;
	}

	public void setMediaService(MediaService mediaService) {
		this.mediaService = mediaService;
	}

	public List<Media> getMedias() {
		return medias;
	}

	public void setMedias(List<Media> medias) {
		this.medias = medias;
	}

	public FileService getFileService() {
		return fileService;
	}

	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	public File[] getFile() {
		return file;
	}

	public void setFile(File[] file) {
		this.file = file;
	}

	public String[] getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String[] fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String[] getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String[] fileFileName) {
		this.fileFileName = fileFileName;
	}

	public void setCurrentUserRole(UserRole sessionCurrentUserRole) {
		this.currentUserRole = sessionCurrentUserRole;
	}
}
