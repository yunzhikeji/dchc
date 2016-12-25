package com.yz.auth;

/**
 * 认证对象
 * 
 * @author Administrator
 * 
 */
public class AuthObject {

	private String tempURL;
	private String propertiesURL;
	private String authURL;// 认证地址
	private String appId;// 应用标识
	private String attrType;// 获取属性列表控制 none：不获取属性；all 获取全部属性 ；portion 获取指定属性
	private String attributes;// 当配置文件中attrType 值为portion时
	// 获取查询指定属性,当且仅当attrType为portion时有效

	private String fileRoot;

	public String getTempURL() {
		return tempURL;
	}

	public void setTempURL(String tempURL) {
		this.tempURL = tempURL;
	}

	public String getPropertiesURL() {
		return propertiesURL;
	}

	public void setPropertiesURL(String propertiesURL) {
		this.propertiesURL = propertiesURL;
	}

	public String getAuthURL() {
		return authURL;
	}

	public void setAuthURL(String authURL) {
		this.authURL = authURL;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAttrType() {
		return attrType;
	}

	public void setAttrType(String attrType) {
		this.attrType = attrType;
	}

	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	public String getFileRoot() {
		return fileRoot;
	}

	public void setFileRoot(String fileRoot) {
		this.fileRoot = fileRoot;
	}

}
