package com.yz.action;

import com.yz.auth.AuthObject;
import com.yz.model.Unit;
import com.yz.model.UserRole;
import com.yz.service.UnitService;
import com.yz.service.UserRoleService;
import com.yz.util.AjaxMsgUtil;
import com.yz.util.ConvertUtil;
import com.yz.util.DateTimeKit;
import com.yz.util.MD5Util;
import com.yz.vo.AjaxMsgVO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.*;
import java.net.URLDecoder;
import java.util.List;

@Component("userRoleAction")
@Scope("prototype")
public class UserRoleAction extends BaseAction {

	private int id;
	private String username;
	private String password;
	@Resource(name = "authObject")
	private AuthObject authObject;
	@Resource
	private UnitService unitService;
	@Resource
	private UserRoleService userRoleService;
	private UserRole userRole;
	private List<UserRole> userRoles;
	private List<Unit> units;


	// 个人资料新旧密码
	private String password1;
	private String password2;

	// 新增用户名,密码
	private String uname;
	private String pword;

	/**
	 * 用户管理
	 */
	public String list() throws Exception {
		// 判断会话是否失效

		if (convalue != null && !convalue.equals("")) {
			convalue = URLDecoder.decode(convalue, "utf-8");
			convalue = convalue.replace(" ", "");
		}
		if (page < 1) {
			page = 1;
		}
		// 总记录数
		totalCount = userRoleService.getTotalCount(con, convalue, currentUserRole);
		// 总页数
		pageCount = userRoleService.getPageCount(totalCount, size);
		if (page > pageCount && pageCount != 0) {
			page = pageCount;
		}
		// 所有当前页记录对象
		userRoles = userRoleService.queryList(con, convalue, currentUserRole, page,
				size);
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
		// 判断回话是否失效

		if (picture != null && pictureFileName != null
				&& !pictureFileName.replace(" ", "").equals("")) {
			String imageName = DateTimeKit.getDateRandom()
					+ pictureFileName.substring(pictureFileName.indexOf("."));
			this.upload("/userRole", imageName, picture);
			userRole.setPhoto("/userRole" + "/" + imageName);
		}

		userRole.setUsername(uname);
		userRole.setPassword(MD5Util.convertMD5(MD5Util.string2MD5(pword)));
		userRoleService.add(userRole);

		arg[0] = "userRoleAction!list";
		arg[1] = "用户管理";
		return "success_child";
	}

	/*
	 * 检查用户名是否存在
	 *
	 */
	public String checkUsername() {
		if (userRoleService.getUserRoleByUsername(username) != null) {
			AjaxMsgUtil.outputJSONObjectToAjax(response, new AjaxMsgVO("该用户名已经存在,请重新输入."));
		}
		return null;
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
	 * 删除一
	 *
	 * @return
	 */
	public String delete() {
		// 判断会话是否失效


		userRole = userRoleService.getUserRoleById(id);
		// 删除照片
		File photofile = new File(authObject.getFileRoot()
				+ userRole.getPhoto());
		photofile.delete();

		userRoleService.delete(userRole);
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
		for (int i = 0; i < ids.length; i++) {
			userRole = userRoleService.getUserRoleById(ids[i]);
			// 删除照片
			File photofile = new File(authObject.getFileRoot()
					+ userRole.getPhoto());
			photofile.delete();

			userRoleService.delete(userRole);
		}
		AjaxMsgUtil.outputJSONObjectToAjax(response, new AjaxMsgVO("删除成功."));
		return null;
	}

	/**
	 * 跳转到修改页面
	 *
	 * @return
	 */
	public String load() {

		userRole = userRoleService.getUserRoleById(id);
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

		if (picture != null && pictureFileName != null
				&& !pictureFileName.replace(" ", "").equals("")) {
			String imageName = DateTimeKit.getDateRandom()
					+ pictureFileName.substring(pictureFileName.indexOf("."));
			this.upload("/userRole", imageName, picture);
			File photofile = new File(authObject.getFileRoot()
					+ userRole.getPhoto());
			photofile.delete();
			userRole.setPhoto("/userRole" + "/" + imageName);
		}

		userRoleService.update(userRole);
		arg[0] = "userRoleAction!list";
		arg[1] = "用户管理";
		return "success_child";
	}


	public String loadPassword() throws Exception {
		password = currentUserRole.getPassword();
		return "password";
	}

	/**
	 * 修改密码
	 *
	 * @return
	 */
	public String updatePassword() throws Exception {
		currentUserRole.setPassword(MD5Util.convertMD5(MD5Util.string2MD5(password)));
		userRoleService.update(currentUserRole);
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

		userRole = userRoleService.getUserRoleById(id);
		return "view";
	}

	/**
	 * 个人资料
	 */
	public String currentUserRole() {

		userRole = userRoleService.getUserRoleById(currentUserRole.getId());
		units = unitService.getUnits();
		return "currentUserRole";
	}

	public String updateCurrentUserRole() throws Exception {

		if (picture != null && pictureFileName != null
				&& !pictureFileName.replace(" ", "").equals("")) {
			String imageName = DateTimeKit.getDateRandom()
					+ pictureFileName.substring(pictureFileName.indexOf("."));
			this.upload("/userRole", imageName, picture);
			File photofile = new File(authObject.getFileRoot()
					+ userRole.getPhoto());
			photofile.delete();
			userRole.setPhoto("/userRole" + "/" + imageName);
		}
		if (password1 != null && !password1.replace(" ", "").equals("")
				&& password2 != null && !password2.replace(" ", "").equals("")) {
			userRole.setPassword(MD5Util.convertMD5(MD5Util
					.string2MD5(password1)));
		}
		userRoleService.update(userRole);
		arg[0] = "userRoleAction!currentUserRole";
		arg[1] = "个人资料";
		return SUCCESS;
	}

	// get、set-------------------------------------------
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserRoleService getUserRoleService() {
		return userRoleService;
	}

	public void setUserRoleService(UserRoleService userRoleService) {
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

	public UnitService getUnitService() {
		return unitService;
	}

	public void setUnitService(UnitService unitService) {
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


	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPword() {
		return pword;
	}

	public void setPword(String pword) {
		this.pword = pword;
	}

	public void setCurrentUserRole(UserRole sessionCurrentUserRole) {
		this.currentUserRole = sessionCurrentUserRole;
	}


}
