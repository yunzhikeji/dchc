package com.yz.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.engine.Cascade;

/**
 * Judge entity.研判信息,部门查证，上报情况
 * 
 * @author lq
 */
@Entity
@Table(name = "judge", schema = "dbo", catalog = "dchc")
public class Judge implements java.io.Serializable {

	// Fields

	private Integer id;
	private Clue clue;//所属刑侦线索
	private Injurycase injurycase;//所属重伤案件
	private Person person;//所属人员
	private String reportUnit;//报送部门（多个以，隔开）
	private String reportTime;//上报时间
	private String transactor;//上报人（承办人）
	private String telphone;//联系方式
	private String judgeRequirement;//研判要求
	private Integer indexNumber;//序号（表示研判顺序）
	private String criminalJudge;//刑技研判
	private String networkJudge;//网技研判
	private String intelligenceJudge;//情报研判
	private String imageJudge;//图帧研判
	private Integer jtype;//1:研判信息  2：部门查证 3：上报情况

	// Constructors

	/** default constructor */
	public Judge() {
	}

	/** full constructor */
	public Judge(Clue clue, Injurycase injurycase, Person person,
			String reportUnit, String reportTime, String transactor,
			String telphone, String judgeRequirement, Integer indexNumber,
			String criminalJudge, String networkJudge,
			String intelligenceJudge, String imageJudge, Integer jtype) {
		this.clue = clue;
		this.injurycase = injurycase;
		this.person = person;
		this.reportUnit = reportUnit;
		this.reportTime = reportTime;
		this.transactor = transactor;
		this.telphone = telphone;
		this.judgeRequirement = judgeRequirement;
		this.indexNumber = indexNumber;
		this.criminalJudge = criminalJudge;
		this.networkJudge = networkJudge;
		this.intelligenceJudge = intelligenceJudge;
		this.imageJudge = imageJudge;
		this.jtype = jtype;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clid")
	public Clue getClue() {
		return this.clue;
	}

	@Column(name = "criminalJudge")
	public String getCriminalJudge() {
		return this.criminalJudge;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	@Column(name = "imageJudge")
	public String getImageJudge() {
		return this.imageJudge;
	}

	@Column(name = "indexNumber")
	public Integer getIndexNumber() {
		return this.indexNumber;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "inid")
	public Injurycase getInjurycase() {
		return this.injurycase;
	}

	@Column(name = "intelligenceJudge")
	public String getIntelligenceJudge() {
		return this.intelligenceJudge;
	}

	@Column(name = "judgeRequirement")
	public String getJudgeRequirement() {
		return this.judgeRequirement;
	}

	@Column(name = "networkJudge")
	public String getNetworkJudge() {
		return this.networkJudge;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "perid")
	public Person getPerson() {
		return this.person;
	}
	
	@Column(name = "reportTime", length = 30)
	public String getReportTime() {
		return this.reportTime;
	}

	@Column(name = "reportUnit", length = 255)
	public String getReportUnit() {
		return this.reportUnit;
	}

	@Column(name = "telphone", length = 30)
	public String getTelphone() {
		return this.telphone;
	}

	@Column(name = "transactor", length = 20)
	public String getTransactor() {
		return this.transactor;
	}

	@Column(name = "jtype")
	public Integer getJtype() {
		return this.jtype;
	}

	public void setClue(Clue clue) {
		this.clue = clue;
	}

	public void setCriminalJudge(String criminalJudge) {
		this.criminalJudge = criminalJudge;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setImageJudge(String imageJudge) {
		this.imageJudge = imageJudge;
	}

	public void setIndexNumber(Integer indexNumber) {
		this.indexNumber = indexNumber;
	}

	public void setInjurycase(Injurycase injurycase) {
		this.injurycase = injurycase;
	}

	public void setIntelligenceJudge(String intelligenceJudge) {
		this.intelligenceJudge = intelligenceJudge;
	}

	public void setJudgeRequirement(String judgeRequirement) {
		this.judgeRequirement = judgeRequirement;
	}

	public void setNetworkJudge(String networkJudge) {
		this.networkJudge = networkJudge;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}

	public void setReportUnit(String reportUnit) {
		this.reportUnit = reportUnit;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public void setTransactor(String transactor) {
		this.transactor = transactor;
	}

	public void setJtype(Integer jtype) {
		this.jtype = jtype;
	}

}