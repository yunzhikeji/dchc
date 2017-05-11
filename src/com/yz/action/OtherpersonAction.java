package com.yz.action;

import com.yz.auth.AuthObject;
import com.yz.model.Injurycase;
import com.yz.model.Otherperson;
import com.yz.model.Person;
import com.yz.model.UserRole;
import com.yz.service.*;
import com.yz.util.AjaxMsgUtil;
import com.yz.vo.AjaxMsgVO;
import com.yz.vo.OtherPersonVO;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;

@Component("otherpersonAction")
@Scope("prototype")
public class OtherpersonAction extends BaseAction {


	// 条件
	private int pid;// 人员id
	private int inid; //案件id
	private int otherid;// 同案人员，关系人员

	private String idcard;

	// service层对象
	@Resource
	private PersonService personService;
	@Resource
	private OtherpersonService otherpersonService;
	@Resource
	private InjurycaseService injurycaseService;
	@Resource
	private ClueService clueService;
	@Resource
	private FileService fileService;
	@Resource(name = "authObject")
	private AuthObject authObject;

	// 单个表对象
	private Person person;
	private Injurycase injurycase;
	private Otherperson otherperson;


	public String getPersonByIdcard() throws Exception {

		Person person = personService.getPersonByIdcard(idcard);
		OtherPersonVO otherPersonVO = new OtherPersonVO();
		if (person != null) {

			otherPersonVO.setId(person.getId());
			otherPersonVO.setName(person.getName());
			otherPersonVO.setNumber(person.getNumber());
			otherPersonVO.setIdcard(person.getIdcard());
			otherPersonVO.setTelphone(person.getTelphone());
			otherPersonVO.setWechat(person.getWechat());
			otherPersonVO.setQq(person.getQq());
			otherPersonVO.setPhotoImg(person.getPhotoImg());
			if (person.getGamblingCriminalMan() != null) {
				otherPersonVO.setCurrentAddress(person.getGamblingCriminalMan().getCurrentAddress());
				otherPersonVO.setCurrentAddressArea(person.getGamblingCriminalMan().getCurrentAddressArea());
			}
		}

		AjaxMsgUtil.outputJSONOToAjax(response, JSONObject.fromObject(otherPersonVO).toString());
		return null;
	}

	public String goToAdd() {
		if (pid != 0) {
			person = personService.loadById(pid);
		}
		if (inid != 0) {
			injurycase = injurycaseService.loadById(inid);
		}
		return "add";
	}

	public String add() throws Exception {
		if (picture1 != null && isNotBlankString(picture1FileName)) {
			otherperson.setFrontPhoto(fileService.uploadOneFile(picture1, picture1FileName, picture1ContentType, "otherperson"));
		}
		if (picture2 != null && isNotBlankString(picture2FileName)) {
			otherperson.setLeftPhoto(fileService.uploadOneFile(picture2, picture2FileName, picture2ContentType, "otherperson"));
		}
		if (picture3 != null && isNotBlankString(picture3FileName)) {
			otherperson.setRightPhoto(fileService.uploadOneFile(picture3, picture3FileName, picture3ContentType, "otherperson"));
		}
		otherpersonService.add(otherperson);
		return "success_child";
	}



	/**
	 * 9:负案在逃人员,10:维稳人员 上传前科照片
	 */
	private File picture1;
	private String picture1ContentType;
	private String picture1FileName;
	private File picture2;
	private String picture2ContentType;
	private String picture2FileName;
	private File picture3;
	private String picture3ContentType;
	private String picture3FileName;

	public String deleteOtherperson() throws Exception {

		otherperson = otherpersonService.loadById(otherid);
		if (isNotBlankString(otherperson.getFrontPhoto())) {
			fileService.deleteFileBySrc(otherperson.getFrontPhoto());
		}
		if (isNotBlankString(otherperson.getLeftPhoto())) {
			fileService.deleteFileBySrc(otherperson.getLeftPhoto());
		}
		if (isNotBlankString(otherperson.getRightPhoto())) {
			fileService.deleteFileBySrc(otherperson.getRightPhoto());
		}
		otherpersonService.delete(otherperson);
		AjaxMsgUtil.outputJSONObjectToAjax(response, new AjaxMsgVO("删除成功."));
		return null;
	}

	public String load() {
		otherperson = otherpersonService.loadById(otherid);
		return "load";
	}

	public String update() throws Exception {
		if (picture1 != null && isNotBlankString(picture1FileName)) {
			fileService.deleteFileBySrc(otherperson.getFrontPhoto());
			otherperson.setFrontPhoto(fileService.uploadOneFile(picture1, picture1FileName, picture1ContentType, "otherperson"));
		}
		if (picture2 != null && isNotBlankString(picture2FileName)) {

			fileService.deleteFileBySrc(otherperson.getLeftPhoto());
			otherperson.setLeftPhoto(fileService.uploadOneFile(picture2, picture2FileName, picture2ContentType, "otherperson"));
		}

		if (picture3 != null && isNotBlankString(picture3FileName)) {
			fileService.deleteFileBySrc(otherperson.getRightPhoto());
			otherperson.setRightPhoto(fileService.uploadOneFile(picture3, picture3FileName, picture3ContentType, "otherperson"));
		}
		otherpersonService.update(otherperson);
		return "success_child";
	}


	// get、set-------------------------------------------
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

	public int getOtherid() {
		return otherid;
	}

	public void setOtherid(int otherid) {
		this.otherid = otherid;
	}

	public OtherpersonService getOtherpersonService() {
		return otherpersonService;
	}

	public void setOtherpersonService(OtherpersonService otherpersonService) {
		this.otherpersonService = otherpersonService;
	}

	public Otherperson getOtherperson() {
		return otherperson;
	}

	public void setOtherperson(Otherperson otherperson) {
		this.otherperson = otherperson;
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

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public void setCurrentUserRole(UserRole sessionCurrentUserRole) {
		this.currentUserRole = sessionCurrentUserRole;
	}

	public FileService getFileService() {
		return fileService;
	}

	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}
}
