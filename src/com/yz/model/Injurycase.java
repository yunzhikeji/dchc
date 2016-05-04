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
 * Injurycase entity.重伤案件,一般案件
 * 
 * @author lq
 */
@Entity
@Table(name = "injurycase", schema = "dbo", catalog = "dchc")
public class Injurycase implements java.io.Serializable {

	// Fields

	private Integer id;
	private UserRole userRole;// 录入人员
	private String caseNumber;// 案件编号
	private String caseType;// 案件类型
	private String caseName;// 案件名称
	private String fillUnit;// 填报单位
	private String fillName;// 填报人姓名
	private String fillTime;// 填报时间
	private String briefCase;// 简要案情
	private String appraiser;// 鉴定人
	private String telphone;// 鉴定人联系电话
	private String injuryAssess;// 重伤评估
	private String endSituation;// 完结情况
	private String comprehensiveJudge;// 综合情况
	private String leaderInstruction;// 领导批示
	private List<Otherperson> otherpersons = new ArrayList<Otherperson>();// 其他人员，包括（同案人，嫌疑人，关系人）
	private List<Troubleshooting> troubleshootings = new ArrayList<Troubleshooting>();// 疑难解答
	private List<Judge> judges = new ArrayList<Judge>();// 研判信息,部门查证，上报情况
	private String joinDate;// 录入时间
	private Integer handleState;// 办理状态
	private Integer itype;
	private String imageCase; // 案件图片
	private Integer isCanvas; // 水印
	private String pids; // pids
	private String caseIds; // caseIds
	private Integer isRelated; // 是否已串并案
	private String videoPath; // 视频
	private String thumbPath; // 缩略图路径
	private String casePlace;//案发地点
	private String startTime;//案发时间

	// Constructors

	/** default constructor */
	public Injurycase() {
	}

	public Injurycase(UserRole userRole, String caseNumber, String caseType,
			String caseName, String fillUnit, String fillName, String fillTime,
			String briefCase, String appraiser, String telphone,
			String injuryAssess, String endSituation,
			String comprehensiveJudge, String leaderInstruction,
			List<Otherperson> otherpersons,
			List<Troubleshooting> troubleshootings, List<Judge> judges,
			String joinDate, Integer handleState, Integer itype,
			String imageCase, Integer isCanvas, String pids, String caseIds,
			Integer isRelated, String videoPath, String thumbPath,String casePlace, String startTime) {
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
		this.comprehensiveJudge = comprehensiveJudge;
		this.leaderInstruction = leaderInstruction;
		this.otherpersons = otherpersons;
		this.troubleshootings = troubleshootings;
		this.judges = judges;
		this.joinDate = joinDate;
		this.handleState = handleState;
		this.itype = itype;
		this.imageCase = imageCase;
		this.isCanvas = isCanvas;
		this.pids = pids;
		this.caseIds = caseIds;
		this.isRelated = isRelated;
		this.videoPath = videoPath;
		this.thumbPath = thumbPath;
		this.casePlace = casePlace;
		this.startTime = startTime;
	}

	/** full constructor */

	@Column(name = "appraiser", length = 20)
	public String getAppraiser() {
		return this.appraiser;
	}

	@Column(name = "briefCase")
	public String getBriefCase() {
		return this.briefCase;
	}

	@Column(name = "caseIds")
	public String getCaseIds() {
		return caseIds;
	}

	@Column(name = "caseName", length = 30)
	public String getCaseName() {
		return this.caseName;
	}

	@Column(name = "caseNumber", length = 30)
	public String getCaseNumber() {
		return this.caseNumber;
	}

	@Column(name = "casePlace", length = 100)
	public String getCasePlace() {
		return casePlace;
	}

	@Column(name = "caseType", length = 30)
	public String getCaseType() {
		return this.caseType;
	}

	@Column(name = "comprehensiveJudge")
	public String getComprehensiveJudge() {
		return comprehensiveJudge;
	}

	@Column(name = "endSituation", length = 100)
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

	@Column(name = "handleState")
	public Integer getHandleState() {
		return handleState;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	@Column(name = "imageCase")
	public String getImageCase() {
		return imageCase;
	}

	@Column(name = "injuryAssess")
	public String getInjuryAssess() {
		return this.injuryAssess;
	}

	@Column(name = "isCanvas")
	public Integer getIsCanvas() {
		return isCanvas;
	}

	@Column(name = "isRelated")
	public Integer getIsRelated() {
		return isRelated;
	}

	@Column(name = "itype")
	public Integer getItype() {
		return itype;
	}

	@Column(name = "joinDate", length = 30)
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

	@Column(name = "pids")
	public String getPids() {
		return pids;
	}

	@Column(name = "startTime", length = 30)
	public String getStartTime() {
		return startTime;
	}

	@Column(name = "telphone", length = 30)
	public String getTelphone() {
		return this.telphone;
	}

	@Column(name = "thumbPath")
	public String getThumbPath() {
		return thumbPath;
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

	@Column(name = "videoPath")
	public String getVideoPath() {
		return videoPath;
	}

	public void setAppraiser(String appraiser) {
		this.appraiser = appraiser;
	}

	public void setBriefCase(String briefCase) {
		this.briefCase = briefCase;
	}

	public void setCaseIds(String caseIds) {
		this.caseIds = caseIds;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public void setCasePlace(String casePlace) {
		this.casePlace = casePlace;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public void setComprehensiveJudge(String comprehensiveJudge) {
		this.comprehensiveJudge = comprehensiveJudge;
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

	public void setHandleState(Integer handleState) {
		this.handleState = handleState;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setImageCase(String imageCase) {
		this.imageCase = imageCase;
	}

	public void setInjuryAssess(String injuryAssess) {
		this.injuryAssess = injuryAssess;
	}

	public void setIsCanvas(Integer isCanvas) {
		this.isCanvas = isCanvas;
	}

	public void setIsRelated(Integer isRelated) {
		this.isRelated = isRelated;
	}

	public void setItype(Integer itype) {
		this.itype = itype;
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

	public void setPids(String pids) {
		this.pids = pids;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public void setThumbPath(String thumbPath) {
		this.thumbPath = thumbPath;
	}

	public void setTroubleshootings(List<Troubleshooting> troubleshootings) {
		this.troubleshootings = troubleshootings;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}
	
	

}