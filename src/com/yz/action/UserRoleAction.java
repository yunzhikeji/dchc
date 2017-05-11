package com.yz.action;

import com.yz.model.Unit;
import com.yz.model.UserRole;
import com.yz.service.FileService;
import com.yz.service.UnitService;
import com.yz.service.UserRoleService;
import com.yz.util.AjaxMsgUtil;
import com.yz.util.ConvertUtil;
import com.yz.util.MD5Util;
import com.yz.vo.AjaxMsgVO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component("userRoleAction")
@Scope("prototype")
public class UserRoleAction extends BaseAction {

	private int id;
	private String username;
	private String password;
	@Resource
	private UnitService unitService;
	@Resource
	private UserRoleService userRoleService;
	@Resource
	private FileService fileService;

	private UserRole userRole;
	private List<UserRole> userRoles;
	private List<Unit> units;


	// 个人资料新旧密码
	private String password1;
	private String password2;

	// 新增用户名,密码
	private String uname;
	private String pword;
	private String unitName;


	/**
	 * 用户管理
	 */
	public String list() throws Exception {

		decodeParameters();

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

	public String goToAdd() {

		units = unitService.getUnits();
		return "add";
	}


	public String add() throws Exception {

		userRole.setUsername(uname);
		userRole.setPassword(MD5Util.convertMD5(MD5Util.string2MD5(pword)));
		userRole.setPhoto(fileService.upload(file, fileFileName, fileContentType,
				"userRole"));
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


	public String delete() {

		userRole = userRoleService.getUserRoleById(id);
		fileService.deleteFileBySrc(userRole.getPhoto());
		userRoleService.delete(userRole);
		arg[0] = "userRoleAction!list";
		arg[1] = "用户管理";
		return SUCCESS;
	}

	public String deleteUserRoles() {

		int[] ids = ConvertUtil.StringtoInt(checkedIDs);
		for (int i = 0; i < ids.length; i++) {
			userRole = userRoleService.getUserRoleById(ids[i]);
			fileService.deleteFileBySrc(userRole.getPhoto());
			userRoleService.delete(userRole);
		}
		AjaxMsgUtil.outputJSONObjectToAjax(response, new AjaxMsgVO("删除成功."));
		return null;
	}

	public String load() {

		userRole = userRoleService.getUserRoleById(id);
		units = unitService.getUnits();
		return "load";
	}

	public String update() throws Exception {
		if (file != null) {
			fileService.deleteFileBySrc(userRole.getPhoto());
			userRole.setPhoto(fileService.upload(file, fileFileName, fileContentType,
					"userRole"));
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

	public String updatePassword() throws Exception {
		currentUserRole.setPassword(MD5Util.convertMD5(MD5Util.string2MD5(password)));
		userRoleService.update(currentUserRole);
		arg[0] = "userRoleAction!list";
		arg[1] = "用户管理";
		return SUCCESS;
	}

	public String view() {
		userRole = userRoleService.getUserRoleById(id);
		return "view";
	}

	public String currentUserRole() {
		userRole = userRoleService.getUserRoleById(currentUserRole.getId());
		units = unitService.getUnits();
		return "currentUserRole";
	}

	public String updateCurrentUserRole() throws Exception {
		if (isFilesNotNull()) {
			fileService.deleteFileBySrc(userRole.getPhoto());
			userRole.setPhoto(fileService.upload(file, fileFileName, fileContentType,
					"userRole"));
		}
		if (isNotBlankString(password1)&& isNotBlankString(password2)) {
			userRole.setPassword(MD5Util.convertMD5(MD5Util
					.string2MD5(password1)));
		}
		userRoleService.update(userRole);
		arg[0] = "userRoleAction!currentUserRole";
		arg[1] = "个人资料";
		return SUCCESS;
	}

	public String getUserRoleByUnitName() {

		userRoles = userRoleService.getUserRoleBUnitName(unitName);

		List<AjaxMsgVO> ajaxMsgVOList = new ArrayList<AjaxMsgVO>();

		if (userRoles != null && userRoles.size() > 0) {
			for (UserRole userRole : userRoles) {
				AjaxMsgVO userRoleVO = new AjaxMsgVO();
				userRoleVO.setId(userRole.getId());
				userRoleVO.setName(userRole.getRealname());
				ajaxMsgVOList.add(userRoleVO);
			}
		}
		AjaxMsgUtil.outputJSONArrayToAjax(response, ajaxMsgVOList);
		return null;
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

	public FileService getFileService() {
		return fileService;
	}

	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
}
