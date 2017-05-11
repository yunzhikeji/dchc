package com.yz.action;

import com.yz.model.*;
import com.yz.service.*;
import com.yz.util.AjaxMsgUtil;
import com.yz.util.ConvertUtil;
import com.yz.util.DateTimeKit;
import com.yz.util.ExcelFileGenerator;
import com.yz.vo.AjaxMsgVO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

@Component("personAction")
@Scope("prototype")
public class PersonAction extends BaseAction {

	private int id;
	private int type;// 人员类型
	private List<String> infoExtractions;// 页面显示被选中 信息提取情况


	@Resource
	private PersonService personService;
	@Resource
	private GamblingCriminalManService gamblingCriminalManService;
	@Resource
	private GuiltSafeguardManService guiltSafeguardManService;
	@Resource
	private DisappearManService disappearManService;
	@Resource
	private AnalyzeManService analyzeManService;
	@Resource
	private ContrastManService contrastManService;
	@Resource
	private OtherpersonService otherpersonService;
	@Resource
	private UserRoleService userRoleService;
	@Resource
	private FileService fileService;

	// 单个表对象
	private Person person;
	private GamblingCriminalMan gamblingCriminalMan;// 1:赌博人员，2:涉恶人员，3:涉黄人员，4:食药环人员，5:涉毒人员，6:留置盘问，7:侵财人员，8:刑事传唤,14:前科人员,15：其他人员
	private GuiltSafeguardMan guiltSafeguardMan;// 9:负案在逃人员,10:维稳人员
	private DisappearMan disappearman;// 11:失踪人员
	private AnalyzeMan analyzeMan;// 12:侵财分析人员
	private ContrastMan contrastMan;// 13:技术比中人员

	private List<Person> persons;
	private List<Otherperson> otherpersons;
	private List<Otherperson> gxrs;// 关系人员
	private List<Otherperson> tars;// 同案人员

	public String list() throws Exception {

		decodeParameters();

		if (page < 1) {
			page = 1;
		}

		pageTileName = selectTileName(type);
		// 总记录数
		totalCount = personService.getTotalCount(con, convalue, currentUserRole, type,
				queryState, starttime, endtime);
		// 总页数
		pageCount = personService.getPageCount(totalCount, size);
		if (page > pageCount && pageCount != 0) {
			page = pageCount;
		}
		// 所有当前页记录对象
		persons = personService.queryList(con, convalue, currentUserRole, page, size,
				type, queryState, starttime, endtime);

		return "list";
	}

	private String selectTileName(int type) {
		String pageName = "人员信息";
		switch (type) {
			case 0:
				pageName = "人员";
				break;
			case 1:
				pageName = "赌博人员";
				break;
			case 2:
				pageName = "涉恶人员";
				break;
			case 3:
				pageName = "涉黄人员";
				break;
			case 4:
				pageName = "食药环人员";
				break;
			case 5:
				pageName = "涉毒人员";
				break;
			case 6:
				pageName = "留置盘问人员";
				break;
			case 7:
				pageName = "侵财人员人员";
				break;
			case 8:
				pageName = "刑事传唤人员";
				break;
			case 9:
				pageName = "负案在逃人员";
				break;
			case 10:
				pageName = "维稳人员";
				break;
			case 11:
				pageName = "失踪人员";
				break;
			case 12:
				pageName = "侵财人员分析";
				break;
			case 13:
				pageName = "技术比中人员";
				break;
			case 14:
				pageName = "前科人员";
				break;
			case 15:
				pageName = "其他人员";
				break;
			default:
				pageName = "人员";
				break;
		}
		return pageName;
	}

	public String goToAdd() {
		pageTileName = selectTileName(type);
		switch (type) {
			case 0:
			case 1:
				// pageName = "赌博人员";
			case 2:
				// pageName = "涉恶人员";
			case 3:
				// pageName = "涉黄人员";
			case 4:
				// pageName = "食药环人员";
			case 5:
				// pageName = "涉毒人员";
			case 6:
				// pageName = "留置盘问";
			case 7:
				// pageName = "侵财人员";
			case 8:
				// pageName = "刑事传唤";
			case 14:
				// pageName = "前科人员";
			case 15:
				// pageName = "其他人员";
				return "gamblingCriminalMan_add";
			case 9:
				// pageName = "负罪在逃";
			case 10:
				// pageName = "维稳人员";
				return "guiltSafeguardMan_add";
			case 11:
				// pageName = "失踪人员";
				return "disappearman_add";
			case 12:
				// pageName = "侵财人员分析";
				return "analyzeMan_add";
			case 13:
				// pageName = "技术比中人员";
				return "contrastMan_add";
			default:
				return "add";
		}

	}

	public String add() throws Exception {
		type = person.getType();
		// 分类添加人员信息
		switch (type) {
			case 0:
			case 1:
				// pageName = "赌博人员";
			case 2:
				// pageName = "涉恶人员";
			case 3:
				// pageName = "涉黄人员";
			case 4:
				// pageName = "食药环人员";
			case 5:
				// pageName = "涉毒人员";
			case 6:
				// pageName = "留置盘问";
			case 7:
				// pageName = "侵财人员";
			case 8:
				// pageName = "刑事传唤";
			case 14:
				// pageName = "前科人员";
			case 15:
				// pageName = "其他人员";
				if (gamblingCriminalMan == null) {
					gamblingCriminalMan = new GamblingCriminalMan();
				}
				gamblingCriminalManService.add(gamblingCriminalMan);
				person.setGamblingCriminalMan(gamblingCriminalMan);
				break;
			case 9:
				// pageName = "负罪在逃";
			case 10:
				// pageName = "维稳人员";
				if (guiltSafeguardMan == null) {
					guiltSafeguardMan = new GuiltSafeguardMan();
				}
				if (picture1 != null && isNotBlankString(picture1FileName)) {
					guiltSafeguardMan.setCriminalRecordPhoto1(fileService.uploadOneFile(picture1, picture1FileName, picture1ContentType, "guiltSafeguardMan"));
				}
				if (picture2 != null && isNotBlankString(picture2FileName)) {
					guiltSafeguardMan.setCriminalRecordPhoto2(fileService.uploadOneFile(picture2, picture2FileName, picture2ContentType, "guiltSafeguardMan"));
				}
				if (picture3 != null && isNotBlankString(picture3FileName)) {
					guiltSafeguardMan.setCriminalRecordPhoto3(fileService.uploadOneFile(picture3, picture3FileName, picture3ContentType, "guiltSafeguardMan"));
				}
				guiltSafeguardManService.add(guiltSafeguardMan);
				person.setGuiltSafeguardMan(guiltSafeguardMan);
				break;
			case 11:
				// pageName = "失踪人员分析";
				if (disappearman == null) {
					disappearman = new DisappearMan();
				}

				if (picture1 != null && isNotBlankString(picture1FileName)) {
					disappearman.setPhoto1(fileService.uploadOneFile(picture1, picture1FileName, picture1ContentType, "disappearman"));
				}
				if (picture2 != null && isNotBlankString(picture2FileName)) {
					disappearman.setPhoto2(fileService.uploadOneFile(picture2, picture2FileName, picture2ContentType, "disappearman"));
				}
				if (picture3 != null && isNotBlankString(picture3FileName)) {
					disappearman.setPhoto3(fileService.uploadOneFile(picture3, picture3FileName, picture3ContentType, "disappearman"));
				}

				disappearManService.add(disappearman);
				person.setDisappearMan(disappearman);
				break;
			case 12:
				// pageName = "侵财人员分析";
				if (analyzeMan == null) {
					analyzeMan = new AnalyzeMan();
				}
				analyzeManService.add(analyzeMan);
				person.setAnalyzeMan(analyzeMan);
				break;
			case 13:
				// pageName = "技术比中人员";
				if (contrastMan == null) {
					contrastMan = new ContrastMan();
				}

				if (picture1 != null && isNotBlankString(picture1FileName)) {
					contrastMan.setRegisterAddressPhoto(fileService.uploadOneFile(picture1, picture1FileName, picture1ContentType, "contrastMan"));
				}
				if (picture2 != null && isNotBlankString(picture2FileName)) {
					contrastMan.setCriminalRecordPhoto(fileService.uploadOneFile(picture2, picture2FileName, picture2ContentType, "contrastMan"));
				}
				contrastManService.add(contrastMan);
				person.setContrastMan(contrastMan);
				break;
			default:
				break;
		}

		person.setPhotoImg(fileService.upload(file, fileFileName, fileContentType, "person"));
		person.setUserRole(currentUserRole);// 设置录入人员
		person.setJoinDate(DateTimeKit.getLocalDate());// 设置录入时间
		person.setHandleState(1);// 初始化处理状态
		person.setIsOutOfTime(0);
		person.setIsNew(1);
		personService.add(person);

		arg[0] = "personAction!list?type=" + person.getType();
		arg[1] = "人员管理";
		return "success_child";
	}

	private File picture1;
	private String picture1ContentType;
	private String picture1FileName;
	private File picture2;
	private String picture2ContentType;
	private String picture2FileName;
	private File picture3;
	private String picture3ContentType;
	private String picture3FileName;

	public String delete() throws Exception {
		person = personService.loadById(id);
		int type = person.getType();
		deletePerson(person);
		arg[0] = "personAction!list?type=" + type;
		arg[1] = "人员管理";
		return SUCCESS;
	}

	private void deletePerson(Person person) {
		switch (person.getType()) {
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 14:
			case 15:
				if (person.getGamblingCriminalMan() != null) {
					id = person.getGamblingCriminalMan().getId();
				}
				break;
			case 9:
			case 10:
				if (person.getGuiltSafeguardMan() != null) {
					id = person.getGuiltSafeguardMan().getId();
					guiltSafeguardMan = guiltSafeguardManService.loadById(id);
					if (guiltSafeguardMan != null) {
						fileService.deleteFileBySrc(guiltSafeguardMan.getCriminalRecordPhoto1());
						fileService.deleteFileBySrc(guiltSafeguardMan.getCriminalRecordPhoto2());
						fileService.deleteFileBySrc(guiltSafeguardMan.getCriminalRecordPhoto3());
						// 同时删除所有同安人员，关系人员的照片
						otherpersons = otherpersonService.getOtherpersons();
						if (otherpersons != null && otherpersons.size() > 0) {
							for (Otherperson otherperson : otherpersons) {
								fileService.deleteFileBySrc(otherperson.getFrontPhoto());
								fileService.deleteFileBySrc(otherperson.getLeftPhoto());
								fileService.deleteFileBySrc(otherperson.getRightPhoto());
							}
						}
					}
				}
				break;
			case 11:
				if (person.getDisappearMan() != null) {
					id = person.getDisappearMan().getId();
					disappearman = disappearManService.loadById(id);
					if (disappearman != null) {
						fileService.deleteFileBySrc(disappearman.getPhoto1());
						fileService.deleteFileBySrc(disappearman.getPhoto2());
						fileService.deleteFileBySrc(disappearman.getPhoto3());
					}
				}
				break;
			case 12:
				break;
			case 13:
				if (person.getContrastMan() != null) {
					id = person.getContrastMan().getId();
					contrastMan = contrastManService.loadById(id);
					if (contrastMan != null) {
						fileService.deleteFileBySrc(contrastMan.getRegisterAddressPhoto());
						fileService.deleteFileBySrc(contrastMan.getCriminalRecordPhoto());
					}
				}
				break;
			default:
				break;
		}

		// 删除照片
		fileService.deleteFileBySrc(person.getPhotoImg());
		personService.delete(person);
	}

	public String deletePersons() throws Exception {

		currentUserRole = (UserRole) session.get("currentUserRole");

		int[] ids = ConvertUtil.StringtoInt(checkedIDs);
		for (int i = 0; i < ids.length; i++) {
			person = personService.loadById(ids[i]);
			deletePerson(person);
		}
		AjaxMsgUtil.outputJSONObjectToAjax(response, new AjaxMsgVO("删除成功."));
		return null;
	}

	/**
	 * 跳转到修改页面
	 *
	 * @return
	 */
	public String load() throws Exception {

		pageTileName = selectTileName(type);

		person = personService.getPersonById(id);// 当前修改人员的id

		type = person.getType();
		// 分类添加人员信息
		switch (type) {
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 14:
			case 15:
				gamblingCriminalMan = person.getGamblingCriminalMan();
				if (gamblingCriminalMan != null) {
					handleInfoExtractionMsg(gamblingCriminalMan.getInfoExtraction());
				}
				return "gamblingCriminalMan_load";
			case 9:
			case 10:
				guiltSafeguardMan = person.getGuiltSafeguardMan();
				gxrs = otherpersonService.getOtherpersonByOtype(1, id);// 关系人
				tars = otherpersonService.getOtherpersonByOtype(2, id);// 同案人
				return "guiltSafeguardMan_load";
			case 11:
				disappearman = person.getDisappearMan();
				return "disappearman_load";
			case 12:
				analyzeMan = person.getAnalyzeMan();
				return "analyzeMan_load";
			case 13:
				contrastMan = person.getContrastMan();
				return "contrastMan_load";
			default:
				return "load";
		}

	}

	// 页面显示被选中 信息提前情况 {'提取手机信息','提取银行卡信息','提取DNA','提取指纹','提取鞋印'}显示格式
	private void handleInfoExtractionMsg(String infoExtraction) {
		infoExtractions = new ArrayList<String>();
		if (infoExtraction != null && infoExtraction.length() > 0)
		{
			if(infoExtraction.contains(","))
			{
				String[] infoExtractionpres = infoExtraction.split(",");
				for (int i = 0; i < infoExtractionpres.length; i++) {
					infoExtractions.add(infoExtractionpres[i].replace(" ", ""));
				}
			}
			infoExtractions.add(infoExtraction);
		}
	}

	public String update() throws Exception {

		type = person.getType();
		// 分类添加人员信息
		switch (type) {
			case 0:
			case 1:
				// pageName = "赌博人员";
			case 2:
				// pageName = "涉恶人员";
			case 3:
				// pageName = "涉黄人员";
			case 4:
				// pageName = "食药环人员";
			case 5:
				// pageName = "涉毒人员";
			case 6:
				// pageName = "留置盘问";
			case 7:
				// pageName = "侵财人员";
			case 8:
				// pageName = "刑事传唤";
			case 14:
				// pageName = "前科人员";
			case 15:
				// pageName = "其他人员";
				if (gamblingCriminalMan == null) {
					gamblingCriminalMan = new GamblingCriminalMan();
					gamblingCriminalManService.add(gamblingCriminalMan);
				} else {
					gamblingCriminalManService.update(gamblingCriminalMan);
				}
				person.setGamblingCriminalMan(gamblingCriminalMan);
				break;
			case 9:
				// pageName = "负罪在逃";
			case 10:
				// pageName = "维稳人员";
				if (guiltSafeguardMan == null) {
					guiltSafeguardMan = new GuiltSafeguardMan();
				}
				if (picture1 != null && isNotBlankString(picture1FileName)) {

					fileService.deleteFileBySrc(guiltSafeguardMan.getCriminalRecordPhoto1());
					guiltSafeguardMan.setCriminalRecordPhoto1(fileService.uploadOneFile(picture1, picture1FileName, picture1ContentType, "guiltSafeguardMan"));
				}
				if (picture2 != null && isNotBlankString(picture2FileName)) {
					fileService.deleteFileBySrc(guiltSafeguardMan.getCriminalRecordPhoto2());
					guiltSafeguardMan.setCriminalRecordPhoto2(fileService.uploadOneFile(picture2, picture2FileName, picture2ContentType, "guiltSafeguardMan"));
				}
				if (picture3 != null && isNotBlankString(picture3FileName)) {
					fileService.deleteFileBySrc(guiltSafeguardMan.getCriminalRecordPhoto3());
					guiltSafeguardMan.setCriminalRecordPhoto3(fileService.uploadOneFile(picture3, picture3FileName, picture3ContentType, "guiltSafeguardMan"));
				}
				guiltSafeguardManService.add(guiltSafeguardMan);
				person.setGuiltSafeguardMan(guiltSafeguardMan);
				break;
			case 11:
				// pageName = "失踪人员分析";
				if (disappearman == null) {
					disappearman = new DisappearMan();
				}

				if (picture1 != null && isNotBlankString(picture1FileName)) {
					fileService.deleteFileBySrc(disappearman.getPhoto1());
					disappearman.setPhoto1(fileService.uploadOneFile(picture1, picture1FileName, picture1ContentType, "disappearman"));
				}
				if (picture2 != null && isNotBlankString(picture2FileName)) {
					fileService.deleteFileBySrc(disappearman.getPhoto2());
					disappearman.setPhoto2(fileService.uploadOneFile(picture2, picture2FileName, picture2ContentType, "disappearman"));
				}
				if (picture3 != null && isNotBlankString(picture3FileName)) {
					fileService.deleteFileBySrc(disappearman.getPhoto3());
					disappearman.setPhoto3(fileService.uploadOneFile(picture3, picture3FileName, picture3ContentType, "disappearman"));
				}

				disappearManService.add(disappearman);
				person.setDisappearMan(disappearman);
				break;
			case 12:
				// pageName = "侵财人员分析";
				if (analyzeMan == null) {
					analyzeMan = new AnalyzeMan();
				}
				analyzeManService.add(analyzeMan);
				person.setAnalyzeMan(analyzeMan);
				break;
			case 13:
				// pageName = "技术比中人员";
				if (contrastMan == null) {
					contrastMan = new ContrastMan();
				}

				if (picture1 != null && isNotBlankString(picture1FileName)) {
					fileService.deleteFileBySrc(contrastMan.getRegisterAddressPhoto());
					contrastMan.setRegisterAddressPhoto(fileService.uploadOneFile(picture1, picture1FileName, picture1ContentType, "contrastMan"));
				}
				if (picture2 != null && isNotBlankString(picture2FileName)) {
					fileService.deleteFileBySrc(contrastMan.getCriminalRecordPhoto());
					contrastMan.setCriminalRecordPhoto(fileService.uploadOneFile(picture2, picture2FileName, picture2ContentType, "contrastMan"));
				}
				contrastManService.add(contrastMan);
				person.setContrastMan(contrastMan);
				break;
			default:
				break;
		}

		if (isFilesNotNull()) {
			fileService.deleteFileBySrc(person.getPhotoImg());
			person.setPhotoImg(fileService.upload(file, fileFileName, fileContentType, "person"));
		}

		if (person.getEndSituation() != null && person.getEndSituation() != "") {
			person.setHandleState(3);// 完结
		}

		if (person.getUserRole() == null) {
			UserRole userRole = userRoleService.getUserRoleById(currentUserRole
					.getId());
			person.setUserRole(userRole);// 设置录入人员
		}

		personService.update(person);

		arg[0] = "personAction!list?type=" + person.getType();
		arg[1] = "人员管理";
		return "success_child";
	}

	public String view() {
		person = personService.loadById(id);
		return "view";
	}

	public String getNewPersons() {
		currentUserRole = (UserRole) session.get("currentUserRole");

		List<Person> persons = personService.getNewPersonsByUserRole(currentUserRole);

		List<AjaxMsgVO> ajaxMsgVOList = new ArrayList<AjaxMsgVO>();

		if (persons != null && persons.size() > 0) {
			for (Person person : persons) {
				if (person.getType() == 14) {
					break;
				}
				AjaxMsgVO personVO = new AjaxMsgVO();
				personVO.setId(person.getId());
				personVO.setName(person.getName());
				personVO.setJoinDate(person.getJoinDate());
				personVO.setType(person.getType());
				switch (person.getType()) {
					case 0:
						personVO.setTypeName("未知人员");
						break;
					case 1:
						personVO.setTypeName("赌博人员");
						break;
					case 2:
						personVO.setTypeName("涉恶人员");
						break;
					case 3:
						personVO.setTypeName("涉黄人员");
						break;
					case 4:
						personVO.setTypeName("食药环人员");
						break;
					case 5:
						personVO.setTypeName("涉毒人员");
						break;
					case 6:
						personVO.setTypeName("留置盘问人员");
						break;
					case 7:
						personVO.setTypeName("侵财人员");
						break;
					case 8:
						personVO.setTypeName("刑事传唤人员");
						break;
					case 9:
						personVO.setTypeName("负案在逃人员");
						break;
					case 10:
						personVO.setTypeName("维稳人员");
						break;
					case 11:
						personVO.setTypeName("失踪人员");
						break;
					case 12:
						personVO.setTypeName("侵财人员分析");
						break;
					case 13:
						personVO.setTypeName("技术比中人员");
						break;
					case 14:
						personVO.setTypeName("前科人员");
						break;
					case 15:
						personVO.setTypeName("其他人员");
						break;
					default:
						break;
				}
				ajaxMsgVOList.add(personVO);
			}
		}

		AjaxMsgUtil.outputJSONArrayToAjax(response, ajaxMsgVOList);
		return null;
	}


	public String importData() {

		if (!isFilesExitExcel()) {
			request.put("errorInfo", "导入文件格式不正确");
			return "opexcel";

		}
		boolean isImportSuccess = personService.savePersonWithExcel(file[0], currentUserRole, type);
		if (isImportSuccess) {
			request.put("errorInfo", "导入数据成功");
			return "opexcel";
		} else {
			request.put("errorInfo", "导出数据失败");
			return "opexcel";
		}
	}

	public String export() {

		decodeParameters();
		// 获取导出的表头和数据
		// 获取表头,存放到ArrayList对象中(人员编号 姓名 出生日期 QQ 微信号 身份证号 户籍地址 户籍区域)
		ArrayList fieldName = personService.getExcelFieldNameList(type);

		// 获取数据
		ArrayList fieldData = personService.getExcelFieldDataList(con,
				convalue, currentUserRole, type, queryState, starttime, endtime);
		
		try {
			OutputStream out = response.getOutputStream();
			response.reset();
			String excelName = "person.xls";
			response.setHeader("Content-Disposition", "attachment; filename="
					+ excelName);
			// 设置excel报表的形式
			response.setContentType("application/vnd.ms-excel");
			ExcelFileGenerator generator = new ExcelFileGenerator(fieldName,
					fieldData);
			generator.expordExcel(out);
			// 设置输出形式
			System.setOut(new PrintStream(out));
			// 刷新输出流
			out.flush();
			// 关闭输出流
			if (out != null) {
				out.close();

			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public String importExcel() {

		return "import";
	}

	// get、set-------------------------------------------

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public GamblingCriminalMan getGamblingCriminalMan() {
		return gamblingCriminalMan;
	}

	public void setGamblingCriminalMan(GamblingCriminalMan gamblingCriminalMan) {
		this.gamblingCriminalMan = gamblingCriminalMan;
	}

	public GamblingCriminalManService getGamblingCriminalManService() {
		return gamblingCriminalManService;
	}

	public void setGamblingCriminalManService(
			GamblingCriminalManService gamblingCriminalManService) {
		this.gamblingCriminalManService = gamblingCriminalManService;
	}

	public List<String> getInfoExtractions() {
		return infoExtractions;
	}

	public void setInfoExtractions(List<String> infoExtractions) {
		this.infoExtractions = infoExtractions;
	}


	public GuiltSafeguardManService getGuiltSafeguardManService() {
		return guiltSafeguardManService;
	}

	public void setGuiltSafeguardManService(
			GuiltSafeguardManService guiltSafeguardManService) {
		this.guiltSafeguardManService = guiltSafeguardManService;
	}

	public GuiltSafeguardMan getGuiltSafeguardMan() {
		return guiltSafeguardMan;
	}

	public void setGuiltSafeguardMan(GuiltSafeguardMan guiltSafeguardMan) {
		this.guiltSafeguardMan = guiltSafeguardMan;
	}

	public DisappearManService getDisappearmanService() {
		return disappearManService;
	}

	public void setDisappearmanService(DisappearManService disappearManService) {
		this.disappearManService = disappearManService;
	}

	public DisappearMan getDisappearman() {
		return disappearman;
	}

	public void setDisappearman(DisappearMan disappearman) {
		this.disappearman = disappearman;
	}

	public AnalyzeManService getAnalyzeManService() {
		return analyzeManService;
	}

	public void setAnalyzeManService(AnalyzeManService analyzeManService) {
		this.analyzeManService = analyzeManService;
	}

	public ContrastManService getContrastManService() {
		return contrastManService;
	}

	public void setContrastManService(ContrastManService contrastManService) {
		this.contrastManService = contrastManService;
	}

	public AnalyzeMan getAnalyzeMan() {
		return analyzeMan;
	}

	public void setAnalyzeMan(AnalyzeMan analyzeMan) {
		this.analyzeMan = analyzeMan;
	}

	public ContrastMan getContrastMan() {
		return contrastMan;
	}

	public void setContrastMan(ContrastMan contrastMan) {
		this.contrastMan = contrastMan;
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

	public File getPicture2() {
		return picture2;
	}

	public void setPicture2(File picture2) {
		this.picture2 = picture2;
	}

	public String getPicture2ContentType() {
		return picture2ContentType;
	}

	public void setPicture2ContentType(String picture2ContentType) {
		this.picture2ContentType = picture2ContentType;
	}

	public String getPicture2FileName() {
		return picture2FileName;
	}

	public void setPicture2FileName(String picture2FileName) {
		this.picture2FileName = picture2FileName;
	}

	public File getPicture3() {
		return picture3;
	}

	public void setPicture3(File picture3) {
		this.picture3 = picture3;
	}

	public String getPicture3ContentType() {
		return picture3ContentType;
	}

	public void setPicture3ContentType(String picture3ContentType) {
		this.picture3ContentType = picture3ContentType;
	}

	public String getPicture3FileName() {
		return picture3FileName;
	}

	public void setPicture3FileName(String picture3FileName) {
		this.picture3FileName = picture3FileName;
	}

	public OtherpersonService getOtherpersonService() {
		return otherpersonService;
	}

	public void setOtherpersonService(OtherpersonService otherpersonService) {
		this.otherpersonService = otherpersonService;
	}

	public List<Otherperson> getGxrs() {
		return gxrs;
	}

	public void setGxrs(List<Otherperson> gxrs) {
		this.gxrs = gxrs;
	}

	public List<Otherperson> getTars() {
		return tars;
	}

	public void setTars(List<Otherperson> tars) {
		this.tars = tars;
	}

	public List<Otherperson> getOtherpersons() {
		return otherpersons;
	}

	public void setOtherpersons(List<Otherperson> otherpersons) {
		this.otherpersons = otherpersons;
	}

	public UserRoleService getUserRoleService() {
		return userRoleService;
	}

	public void setUserRoleService(UserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}

	public void setCurrentUserRole(UserRole sessionCurrentUserRole) {
		this.currentUserRole = sessionCurrentUserRole;
	}

}
