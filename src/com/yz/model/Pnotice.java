package com.yz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Pnotice entity.
 * 
 * @author lq 通知公告
 */
@Entity
@Table(name = "pnotice", schema = "dbo", catalog = "dchc")
public class Pnotice implements java.io.Serializable {

	// Fields

	private Integer id;
	private UserRole userRole;
	private String title;
	private String author;
	private String releaseTime;
	private String article;

	// Constructors

	/** default constructor */
	public Pnotice() {
	}

	/** full constructor */
	public Pnotice(UserRole userRole, String title, String author,
			String releaseTime, String article) {
		this.userRole = userRole;
		this.title = title;
		this.author = author;
		this.releaseTime = releaseTime;
		this.article = article;
	}

	// Property accessors
	@Column(name = "article")
	public String getArticle() {
		return this.article;
	}

	@Column(name = "author",length = 30)
	public String getAuthor() {
		return this.author;
	}

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	@Column(name = "releaseTime",length = 50)
	public String getReleaseTime() {
		return this.releaseTime;
	}

	@Column(name = "title")
	public String getTitle() {
		return this.title;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uid")
	public UserRole getUserRole() {
		return this.userRole;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

}