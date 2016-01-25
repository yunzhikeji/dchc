package com.yz.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Injurycase entity.重伤案件
 * 
 * @author lq
 */
@Entity
@Table(name = "injurycase", schema = "dbo", catalog = "dchc")
public class Injurycase implements java.io.Serializable {

	// Fields

	private Integer id;
	private UserRole userRole;//录入人员
	private String caseNumber;//案件编号
	private String caseType;//案件类型
	private String caseName;//案件名称
	private String fillUnit;//填报单位
	private String fillName;//填报人姓名
	private String fillTime;//填报时间
	private String briefCase;//简要案情
	private String appraiser;//鉴定人
	private String telphone;//鉴定人联系电话
	private String injuryAssess;//重伤评估
	private String endSituation;//完结情况
	private String leaderInstruction;//领导批示
	private List<Otherperson> otherpersons = new ArrayList<Otherperson>();//其他人员，包括（同案人，嫌疑人，关系人）
	private List<Troubleshooting> troubleshootings = new ArrayList<Troubleshooting>();//疑难解答
	private List<Judge> judges = new ArrayList<Judge>();//研判信息,部门查证，上报情况
	private String joinDate;//录入时间
	private Integer handleState;//办理状态
	// Constructors

	/** default constructor */
	public Injurycase() {
	}

	/** full constructor */
	public Injurycase(UserRole userRole, String caseNumber, String caseType,
			String caseName, String fillUnit, String fillName, String fillTime,
			String briefCase, String appraiser, String telphone,
			String injuryAssess, String endSituation,String joinDate,Integer handleState,
			String leaderInstruction, List<Otherperson> otherpersons,
			List<Troubleshooting> troubleshootings, List<Judge> judges) {
		this.userRole = userRole;
		this.caseNumber = caseNumber;
		this.caseType = caseType;
		this.caseName = caseName;
		this.fillUnit = fillUnit;
		this.fillName = fillName;
		this.fillTime = fillTime;
		this.briefCase = briefCase;
		this.appraiser = appraiser;
		this.telphone = telphone;
		this.injuryAssess = injuryAssess;
		this.endSituation = endSituation;
		this.leaderInstruction = leaderInstruction;
		this.joinDate = joinDate;
		this.handleState = handleState;
		this.otherpersons = otherpersons;
		this.troubleshootings = troubleshootings;
		this.judges = judges;
		
	}

	@Column(name = "appraiser", length = 20)
	public String getAppraiser() {
		return this.appraiser;
	}

	@Column(name = "briefCase")
	public String getBriefCase() {
		return this.briefCase;
	}

	@Column(name = "caseName", length = 30)
	public String getCaseName() {
		return this.caseName;
	}

	@Column(name = "caseNumber", length = 30)
	public String getCaseNumber() {
		return this.caseNumber;
	}

	@Column(name = "caseType", length = 30)
	public String getCaseType() {
		return this.caseType;
	}

	@Column(name = "endSituation",length= 100)
	public String getEndSituation() {
		return this.endSituation;
	}

	@Column(name = "fillName", length = 20)
	public String getFillName() {
		return this.fillName;
	}

	@Column(name = "fillTime", length = 30)
	public String getFillTime() {
		return this.fillTime;
	}

	@Column(name = "fillUnit", length = 30)
	public String getFillUnit() {
		return this.fillUnit;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	@Column(name = "injuryAssess")
	public String getInjuryAssess() {
		return this.injuryAssess;
	}

	@Column(name = "joinDate",length=30)
	public String getJoinDate() {
		return joinDate;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "injurycase")
	public List<Judge> getJudges() {
		return this.judges;
	}

	@Column(name = "leaderInstruction")
	public String getLeaderInstruction() {
		return this.leaderInstruction;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "injurycase")
	public List<Otherperson> getOtherpersons() {
		return this.otherpersons;
	}

	@Column(name = "telphone", length = 30)
	public String getTelphone() {
		return this.telphone;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "injurycase")
	public List<Troubleshooting> getTroubleshootings() {
		return this.troubleshootings;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uid")
	public UserRole getUserRole() {
		return this.userRole;
	}

	public void setAppraiser(String appraiser) {
		this.appraiser = appraiser;
	}

	public void setBriefCase(String briefCase) {
		this.briefCase = briefCase;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public void setEndSituation(String endSituation) {
		this.endSituation = endSituation;
	}

	public void setFillName(String fillName) {
		this.fillName = fillName;
	}

	public void setFillTime(String fillTime) {
		this.fillTime = fillTime;
	}

	public void setFillUnit(String fillUnit) {
		this.fillUnit = fillUnit;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setInjuryAssess(String injuryAssess) {
		this.injuryAssess = injuryAssess;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public void setJudges(List<Judge> judges) {
		this.judges = judges;
	}

	public void setLeaderInstruction(String leaderInstruction) {
		this.leaderInstruction = leaderInstruction;
	}

	public void setOtherpersons(List<Otherperson> otherpersons) {
		this.otherpersons = otherpersons;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public void setTroubleshootings(List<Troubleshooting> troubleshootings) {
		this.troubleshootings = troubleshootings;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

}