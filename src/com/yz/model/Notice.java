package com.yz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Notice entity.通知公告
 * 
 * @author lq
 */
@Entity
@Table(name = "notice", schema = "dbo", catalog = "dchc")
public class Notice implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;//标题
	private String flowNumber;//流水编号
	private String issuer;//发布人
	private String releaseTime;//发布时间
	private String content;//发布内容

	// Constructors

	/** default constructor */
	public Notice() {
	}

	/** full constructor */
	public Notice(String title, String flowNumber, String issuer,
			String releaseTime, String content) {
		this.title = title;
		this.flowNumber = flowNumber;
		this.issuer = issuer;
		this.releaseTime = releaseTime;
		this.content = content;
	}

	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	@Column(name = "flowNumber", length = 30)
	public String getFlowNumber() {
		return this.flowNumber;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	@Column(name = "issuer", length = 30)
	public String getIssuer() {
		return this.issuer;
	}

	@Column(name = "releaseTime", length = 30)
	public String getReleaseTime() {
		return this.releaseTime;
	}

	@Column(name = "title", length = 30)
	public String getTitle() {
		return this.title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setFlowNumber(String flowNumber) {
		this.flowNumber = flowNumber;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}