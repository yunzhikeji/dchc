package com.yz.action;

import com.yz.auth.AuthObject;
import com.yz.model.Injurycase;
import com.yz.model.Otherperson;
import com.yz.model.Person;
import com.yz.model.UserRole;
import com.yz.service.ClueService;
import com.yz.service.InjurycaseService;
import com.yz.service.OtherpersonService;
import com.yz.service.PersonService;
import com.yz.util.AjaxMsgUtil;
import com.yz.util.DateTimeKit;
import com.yz.vo.AjaxMsgVO;
import com.yz.vo.OtherPersonVO;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.*;

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
	//环境变量
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


		JSONObject jsonObj = JSONObject.fromObject(otherPersonVO);
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
	 * 其他人员管理
	 */

	/**
	 * 跳转到添加页面
	 *
	 * @return
	 */
	public String goToAdd() {
		if (pid != 0) {
			person = personService.loadById(pid);
		}
		if (inid != 0) {
			injurycase = injurycaseService.loadById(inid);
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
		if (picture1 != null && picture1FileName != null
				&& !picture1FileName.replace(" ", "").equals("")) {
			String imageName = DateTimeKit.getDateRandom()
					+ picture1FileName.substring(picture1FileName.indexOf("."));
			this.upload("/otherperson", imageName, picture1);
			otherperson.setFrontPhoto("/otherperson" + "/" + imageName);
		}
		if (picture2 != null && picture2FileName != null
				&& !picture2FileName.replace(" ", "").equals("")) {
			String imageName = DateTimeKit.getDateRandom()
					+ picture2FileName.substring(picture2FileName.indexOf("."));
			this.upload("/otherperson", imageName, picture2);
			otherperson.setLeftPhoto("/otherperson" + "/" + imageName);
		}
		if (picture3 != null && picture3FileName != null
				&& !picture3FileName.replace(" ", "").equals("")) {
			String imageName = DateTimeKit.getDateRandom()
					+ picture3FileName.substring(picture3FileName.indexOf("."));
			this.upload("/otherperson", imageName, picture3);
			otherperson.setRightPhoto("/otherperson" + "/" + imageName);
		}

		if (otherperson.getPerson() != null) {
			changePersonHandleState(otherperson.getPerson().getId());
		}
		if (otherperson.getInjurycase() != null) {
			changeInjurycaseHandleState(otherperson.getInjurycase().getId());
		}
		otherpersonService.add(otherperson);
		return "success_child";
	}

	//改变人员当前处理状态
	private void changePersonHandleState(int perid) {

		Person per = personService.loadById(perid);
		if (per != null) {
			if (per.getHandleState() == 1) {
				per.setHandleState(2);
				personService.update(per);
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


	// 上传照片
	private File picture;
	private String pictureContentType;
	private String pictureFileName;

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

	/**
	 * 删除
	 */
	public String deleteOtherperson() throws Exception {

		otherperson = otherpersonService.loadById(otherid);
		if (otherperson.getFrontPhoto() != null
				&& !otherperson.getFrontPhoto().replace(" ", "").equals("")) {
			File photofile = new File(authObject.getFileRoot()
					+ otherperson.getFrontPhoto());
			photofile.delete();
		}
		if (otherperson.getLeftPhoto() != null
				&& !otherperson.getLeftPhoto().replace(" ", "").equals("")) {
			File photofile = new File(authObject.getFileRoot()
					+ otherperson.getLeftPhoto());
			photofile.delete();
		}
		if (otherperson.getRightPhoto() != null
				&& !otherperson.getRightPhoto().replace(" ", "").equals("")) {
			File photofile = new File(authObject.getFileRoot()
					+ otherperson.getRightPhoto());
			photofile.delete();
		}
		otherpersonService.delete(otherperson);
		AjaxMsgUtil.outputJSONObjectToAjax(response, new AjaxMsgVO("删除成功."));
		return null;
	}

	/**
	 * 加载
	 *
	 * @return
	 */
	public String load() {
		otherperson = otherpersonService.loadById(otherid);
		return "load";
	}

	/**
	 * 修改
	 *
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		if (picture1 != null && picture1FileName != null
				&& !picture1FileName.replace(" ", "").equals("")) {
			String imageName = DateTimeKit.getDateRandom()
					+ picture1FileName.substring(picture1FileName.indexOf("."));
			this.upload("/otherperson", imageName, picture1);
			File photofile = new File(authObject.getFileRoot()
					+ otherperson.getFrontPhoto());
			photofile.delete();
			otherperson.setFrontPhoto("/otherperson" + "/" + imageName);
		}
		if (picture2 != null && picture2FileName != null
				&& !picture2FileName.replace(" ", "").equals("")) {
			String imageName = DateTimeKit.getDateRandom()
					+ picture2FileName.substring(picture2FileName.indexOf("."));
			this.upload("/otherperson", imageName, picture2);
			File photofile = new File(authObject.getFileRoot()
					+ otherperson.getLeftPhoto());
			photofile.delete();
			otherperson.setLeftPhoto("/otherperson" + "/" + imageName);
		}

		if (picture3 != null && picture3FileName != null
				&& !picture3FileName.replace(" ", "").equals("")) {
			String imageName = DateTimeKit.getDateRandom()
					+ picture3FileName.substring(picture3FileName.indexOf("."));
			this.upload("/otherperson", imageName, picture3);
			File photofile = new File(authObject.getFileRoot()
					+ otherperson.getRightPhoto());
			photofile.delete();
			otherperson.setRightPhoto("/otherperson" + "/" + imageName);
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


}
