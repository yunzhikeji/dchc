package com.yz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "media", schema = "dbo", catalog = "dchc")
public class Media {
	private Integer id;           				//主键id
	private String title;						//视频名称
	private String descript;					//视频描述
	private String src;							//视频存放地址
	private String picture;						//视频截图
	private String uptime;						//上传时间	
	@Id
	@GeneratedValue
	@Column(name="id", unique = true , nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="title", length = 50)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name="descript", length = 400)
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	
	@Column(name="src", length = 200)
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	
	@Column(name="picture",length = 200)
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	@Column(name="uptime",length = 40)
	public String getUptime() {
		return uptime;
	}
	public void setUptime(String uptime) {
		this.uptime = uptime;
	}
	

}
