package com.yz.vo;

import com.yz.model.UserRole;

public class InjurycaseVO {

	// Fields
	private Integer id;
	private String imageCase; // 案件图片
	private String caseName;// 案件名称
	private String startTime;// 案发时间
	private Integer isRelated; // 是否已串并案
	private String series;// 串并案关键字
	private UserRole userRole;// 录入人员
	private Integer handleState;// 办理状态
	private Integer videoNumber;
	private Integer imageNumher;

	public Integer getVideoNumber() {
		return videoNumber;
	}

	public void setVideoNumber(Integer videoNumber) {
		this.videoNumber = videoNumber;
	}

	public Integer getImageNumher() {
		return imageNumher;
	}

	public void setImageNumher(Integer imageNumher) {
		this.imageNumher = imageNumher;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImageCase() {
		return imageCase;
	}

	public void setImageCase(String imageCase) {
		this.imageCase = imageCase;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public Integer getIsRelated() {
		return isRelated;
	}

	public void setIsRelated(Integer isRelated) {
		this.isRelated = isRelated;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public Integer getHandleState() {
		return handleState;
	}

	public void setHandleState(Integer handleState) {
		this.handleState = handleState;
	}

}
