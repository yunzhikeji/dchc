package com.yz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "media", schema = "dbo", catalog = "dchc")
public class Media {
	private Integer id; // 主键id
	private String title; // 媒体名称
	private String descript; // 媒体描述
	private String src; // 媒体存放地址
	private Integer mtype; // 媒体类型(0:图片，1：视频)
	private String uptime; // 上传时间
	private String picSrc;// 视频截图图片
	private Injurycase injurycase;// 所属案件

	@Column(name = "descript", length = 400)
	public String getDescript() {
		return descript;
	}

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "inid")
	public Injurycase getInjurycase() {
		return injurycase;
	}

	@Column(name = "mtype")
	public Integer getMtype() {
		return mtype;
	}

	@Column(name = "src", length = 200)
	public String getSrc() {
		return src;
	}

	@Column(name = "title", length = 50)
	public String getTitle() {
		return title;
	}

	@Column(name = "uptime", length = 40)
	public String getUptime() {
		return uptime;
	}

	@Column(name = "picSrc")
	public String getPicSrc() {
		return picSrc;
	}

	public void setPicSrc(String picSrc) {
		this.picSrc = picSrc;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setInjurycase(Injurycase injurycase) {
		this.injurycase = injurycase;
	}

	public void setMtype(Integer mtype) {
		this.mtype = mtype;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setUptime(String uptime) {
		this.uptime = uptime;
	}

	public String toString() {
		return "{id:" + this.id + "," + "title:" + "\"" + this.title + "\""
				+ "," + "src:" + "\"" + this.src + "\"" + "}";
	}

}
