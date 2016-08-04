package com.yz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Successexample entity.
 * 
 * @author lq 成功案例
 */
@Entity
@Table(name = "successexample", schema = "dbo", catalog = "dchc")
public class Successexample implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer perid;//弱关联 对应person
	private Integer inid;//弱关联 对应injurycase
	private Integer clid;//弱关联 对应clue
	private String title;
	private String number;
	private String releaseName;
	private String releaseTime;
	private String content;

	// Constructors

	/** default constructor */
	public Successexample() {
	}

	/** full constructor */
	public Successexample(Integer perid, Integer inid, Integer clid,
			String title, String number, String releaseName, String releaseTime, String content) {
		this.perid = perid;
		this.inid = inid;
		this.clid = clid;
		this.title = title;
		this.number = number;
		this.releaseName = releaseName;
		this.releaseTime = releaseTime;
		this.content = content;
	}

	// Property accessors


	@Column(name = "clid")
	public Integer getClid() {
		return this.clid;
	}

	@Column(name = "content")
	public String getContent() {
		return content;
	}

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	@Column(name = "inid")
	public Integer getInid() {
		return this.inid;
	}

	@Column(name = "number")
	public String getNumber() {
		return this.number;
	}

	@Column(name = "perid")
	public Integer getPerid() {
		return this.perid;
	}

	@Column(name = "releaseName")
	public String getReleaseName() {
		return this.releaseName;
	}

	@Column(name = "releaseTime")
	public String getReleaseTime() {
		return this.releaseTime;
	}

	@Column(name = "title")
	public String getTitle() {
		return this.title;
	}

	public void setClid(Integer clid) {
		this.clid = clid;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setInid(Integer inid) {
		this.inid = inid;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setPerid(Integer perid) {
		this.perid = perid;
	}

	public void setReleaseName(String releaseName) {
		this.releaseName = releaseName;
	}

	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}