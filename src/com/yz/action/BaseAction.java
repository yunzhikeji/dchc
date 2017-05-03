package com.yz.action;

import com.opensymphony.xwork2.ActionSupport;
import com.yz.model.UserRole;
import com.yz.service.UserRoleAware;
import com.yz.util.ExceptionUtil;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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


	public void decodeParameters()
	{
		if (convalue != null && !convalue.equals("")) {
			decodeAndReplaceBlank(convalue);
		}
		if (starttime != null && !starttime.equals("")) {
			decodeAndReplaceBlank(starttime);
		}
		if (endtime != null && !endtime.equals("")) {
			decodeAndReplaceBlank(endtime);
		}
	}


	private String decodeAndReplaceBlank(String param)
	{
		try {
			param = URLDecoder.decode(param, "utf-8");
			param = param.replace(" ", "");
		}catch (UnsupportedEncodingException ex)
		{
			ExceptionUtil.getStackTrace(ex);
		}
		return param;
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


}
