package com.yz.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
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
import com.yz.model.Clue;
import com.yz.model.Injurycase;
import com.yz.model.Otherperson;
import com.yz.model.Person;
import com.yz.service.IClueService;
import com.yz.service.IInjurycaseService;
import com.yz.service.IOtherpersonService;
import com.yz.service.IPersonService;
import com.yz.util.DateTimeKit;
import com.yz.vo.AjaxMsgVO;

@Component("otherpersonAction")
@Scope("prototype")
public class OtherpersonAction extends ActionSupport implements RequestAware,
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
	private int inid; //案件id
	private int cid;//刑侦线索
	private int otherid;// 同案人员，关系人员

	private int otype;// 其他人员类型 1：关系人员，2：同案人员

	// service层对象
	@Resource
	private IPersonService personService;
	@Resource
	private IOtherpersonService otherpersonService;
	@Resource
	private IInjurycaseService injurycaseService;
	@Resource
	private IClueService clueService;

	// 单个表对象
	private Person person;
	private Injurycase injurycase;
	private Otherperson otherperson;
	private Clue clue;


	// list表对象
	private List<Person> persons;
	private List<Otherperson> otherpersons;
	private List<Otherperson> gxrs;// 关系人员
	private List<Otherperson> tars;// 同案人员
	private List<Otherperson> xyrs;// 嫌疑人员

	/**
	 * 其他人员管理
	 */

	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	public String goToAdd() {
		if(pid!=0)
		{
			person = personService.loadById(pid);
		}
		if(inid!=0)
		{
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
			otherperson.setFrontPhoto("otherperson" + "/" + imageName);
		}
		if (picture2 != null && picture2FileName != null
				&& !picture2FileName.replace(" ", "").equals("")) {
			String imageName = DateTimeKit.getDateRandom()
					+ picture2FileName.substring(picture2FileName.indexOf("."));
			this.upload("/otherperson", imageName, picture2);
			otherperson.setLeftPhoto("otherperson" + "/" + imageName);
		}
		if (picture3 != null && picture3FileName != null
				&& !picture3FileName.replace(" ", "").equals("")) {
			String imageName = DateTimeKit.getDateRandom()
					+ picture3FileName.substring(picture3FileName.indexOf("."));
			this.upload("/otherperson", imageName, picture3);
			otherperson.setRightPhoto("otherperson" + "/" + imageName);
		}

		if (otherperson.getPerson()!= null) {
			changePersonHandleState(otherperson.getPerson().getId());
		}
		if (otherperson.getInjurycase()!= null) {
			changeInjurycaseHandleState(otherperson.getInjurycase().getId());
		}
		otherpersonService.add(otherperson);
		return "success_child";
	}
	
	//改变人员当前处理状态
	private void changePersonHandleState(int perid) {
		
		Person per = personService.loadById(perid);
		if(per!=null)
		{
			if(per.getHandleState()==1)
			{
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
	
	private void changeClueHandleState(int clid) {

		Clue clue = clueService.loadById(clid);
		if (clue != null) {
			if (clue.getHandleState() == 1) {
				clue.setHandleState(2);
				clueService.update(clue);
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
			File photofile = new File(ServletActionContext.getServletContext()
					.getRealPath("/")
					+ otherperson.getFrontPhoto());
			photofile.delete();
		}
		if (otherperson.getLeftPhoto() != null
				&& !otherperson.getLeftPhoto().replace(" ", "").equals("")) {
			File photofile = new File(ServletActionContext.getServletContext()
					.getRealPath("/")
					+ otherperson.getLeftPhoto());
			photofile.delete();
		}
		if (otherperson.getRightPhoto() != null
				&& !otherperson.getRightPhoto().replace(" ", "").equals("")) {
			File photofile = new File(ServletActionContext.getServletContext()
					.getRealPath("/")
					+ otherperson.getRightPhoto());
			photofile.delete();
		}
		otherpersonService.delete(otherperson);
		AjaxMsgVO msgVO = new AjaxMsgVO();
		msgVO.setMessage("删除成功.");
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
	 * 加载
	 * @return
	 */
	public String load() {
		otherperson = otherpersonService.loadById(otherid);
		return "load";
	}

	/**
	 * 修改
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		if (picture1 != null && picture1FileName != null
				&& !picture1FileName.replace(" ", "").equals("")) {
			String imageName = DateTimeKit.getDateRandom()
					+ picture1FileName.substring(picture1FileName.indexOf("."));
			this.upload("/otherperson", imageName, picture1);
			File photofile = new File(ServletActionContext.getServletContext()
					.getRealPath("/")
					+ otherperson.getFrontPhoto());
			photofile.delete();
			otherperson.setFrontPhoto("otherperson" + "/" + imageName);
		}
		if (picture2 != null && picture2FileName != null
				&& !picture2FileName.replace(" ", "").equals("")) {
			String imageName = DateTimeKit.getDateRandom()
					+ picture2FileName.substring(picture2FileName.indexOf("."));
			this.upload("/otherperson", imageName, picture2);
			File photofile = new File(ServletActionContext.getServletContext()
					.getRealPath("/")
					+ otherperson.getLeftPhoto());
			photofile.delete();
			otherperson.setLeftPhoto("otherperson" + "/" + imageName);
		}

		if (picture3 != null && picture3FileName != null
				&& !picture3FileName.replace(" ", "").equals("")) {
			String imageName = DateTimeKit.getDateRandom()
					+ picture3FileName.substring(picture3FileName.indexOf("."));
			this.upload("/otherperson", imageName, picture3);
			File photofile = new File(ServletActionContext.getServletContext()
					.getRealPath("/")
					+ otherperson.getRightPhoto());
			photofile.delete();
			otherperson.setRightPhoto("otherperson" + "/" + imageName);
		}
		otherpersonService.update(otherperson);
		return "success_child";
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

	public int getSize() {
		return size;
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

	public int getOtype() {
		return otype;
	}

	public void setOtype(int otype) {
		this.otype = otype;
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

	public List<Otherperson> getXyrs() {
		return xyrs;
	}

	public void setXyrs(List<Otherperson> xyrs) {
		this.xyrs = xyrs;
	}

	public List<Otherperson> getOtherpersons() {
		return otherpersons;
	}

	public void setOtherpersons(List<Otherperson> otherpersons) {
		this.otherpersons = otherpersons;
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
	
	

}
