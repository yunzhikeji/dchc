package com.yz.action;

import com.opensymphony.xwork2.ActionSupport;
import com.yz.model.UserRole;
import com.yz.service.UserRoleAware;
import com.yz.util.ExceptionUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/2.
 */
public class BaseAction extends ActionSupport implements RequestAware,
		SessionAware, ServletResponseAware, ServletRequestAware, UserRoleAware {

	protected Map<String, Object> request;
	protected Map<String, Object> session;
	protected javax.servlet.http.HttpServletResponse response;
	protected javax.servlet.http.HttpServletRequest req;

	// 分页显示
	protected String[] arg = new String[2];
	protected int page;
	protected final int size = 10;
	protected int pageCount;
	protected int totalCount;

	// 条件
	protected int id;
	protected int con;
	protected String convalue;
	protected int status;// 按状态
	protected int queryState;
	protected String starttime;
	protected String endtime;

	// 页面信息
	protected String pageTileName;// 页面标题名称

	// 批量删除
	protected String checkedIDs;

	protected UserRole currentUserRole;


	// 文件上传
	protected File[] file;
	protected String[] fileContentType;
	protected String[] fileFileName;


	protected void decodeParameters() {
		if (isNotBlankString(convalue)) {
			decodeAndReplaceBlank(convalue);
		}
		if (isNotBlankString(starttime)) {
			decodeAndReplaceBlank(starttime);
		}
		if (isNotBlankString(endtime)) {
			decodeAndReplaceBlank(endtime);
		}
	}


	protected boolean isFilesNotNull() {
		if (file == null)
			return false;

		if (isListNotNull(Arrays.asList(file)) && isListNotNull(Arrays.asList(fileFileName))) {
			return true;
		}
		return false;
	}
	
	
	protected boolean isFilesExitExcel() {
		if (file == null)
		return false;
		
		String currentFileContentType = fileContentType[0];
		if(currentFileContentType.contains("excel"))
		{
			return true;
		}
		return false;
	}


	protected boolean isListNotNull(List<?> list) {
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}


	protected String decodeAndReplaceBlank(String param) {
		try {
			param = URLDecoder.decode(param, "utf-8");
			param = replaceBlank(param);
		} catch (UnsupportedEncodingException ex) {
			ExceptionUtil.getStackTrace(ex);
		}
		return param;
	}


	protected boolean isNotBlankString(String param) {


		return StringUtils.isNotBlank(param);
	}


	protected String replaceBlank(String param) {

		if (param == null) {
			return "";
		}
		return param.replace(" ", "");
	}


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

	public int getSize() {
		return size;
	}

	public void setCurrentUserRole(UserRole sessionCurrentUserRole) {
		this.currentUserRole = sessionCurrentUserRole;
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
}
