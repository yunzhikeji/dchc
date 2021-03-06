package com.yz.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
	private String appraiser;// 鉴定人(办案人)
	private String appraiserUnitName;//办案人单位
	private String telphone;// 鉴定人联系电话
	private String injuryAssess;// 重伤评估
	private String endSituation;// 完结情况
	private String comprehensiveJudge;// 综合情况
	private String leaderInstruction;// 领导批示
	private List<Otherperson> otherpersons = new ArrayList<Otherperson>();// 其他人员，包括（同案人，嫌疑人，关系人）
	private List<Troubleshooting> troubleshootings = new ArrayList<Troubleshooting>();// 疑难解答
	private List<Judge> judges = new ArrayList<Judge>();// 研判信息,部门查证，上报情况
	private List<Media> medias = new ArrayList<Media>();// 媒体
	private String joinDate;// 录入时间
	private Integer handleState;// 办理状态
	private Integer itype;//1:刑事案件 2:重伤案件 3:团伙系列案件 4:行政案件
	private String imageCase; // 案件图片
	private String detailsDescription; // 图像中的细节描述
	private String goodsDescription; //图像中的实物描述
	private Integer isCanvas; // 水印
	private String caseIds; // caseIds
	private Integer isRelated; // 是否已串并案
	private String casePlace;// 案发地点
	private String startTime;// 案发时间
	private String series;// 串并案关键字(串并案系列名称)
	private Integer isOutOfTime;// 超期办理
	private Integer isNew;
	private String situationNum; //警情编号
	private String crimeTarget; //作案目标
	private String crimeObject; //作案工具
	private String crimePattern; //作案方式
	private String personFeature; //人员特征
	private String goodsFeature; //物品特征


	// Constructors

	public String getDetailsDescription() {
		return detailsDescription;
	}

	public void setDetailsDescription(String detailsDescription) {
		this.detailsDescription = detailsDescription;
	}

	public String getGoodsDescription() {
		return goodsDescription;
	}

	public void setGoodsDescription(String goodsDescription) {
		this.goodsDescription = goodsDescription;
	}

	public String getSituationNum() {
		return situationNum;
	}

	public void setSituationNum(String situationNum) {
		this.situationNum = situationNum;
	}

	public String getCrimeTarget() {
		return crimeTarget;
	}

	public void setCrimeTarget(String crimeTarget) {
		this.crimeTarget = crimeTarget;
	}

	public String getCrimeObject() {
		return crimeObject;
	}

	public void setCrimeObject(String crimeObject) {
		this.crimeObject = crimeObject;
	}

	public String getCrimePattern() {
		return crimePattern;
	}

	public void setCrimePattern(String crimePattern) {
		this.crimePattern = crimePattern;
	}

	public String getPersonFeature() {
		return personFeature;
	}

	public void setPersonFeature(String personFeature) {
		this.personFeature = personFeature;
	}

	public String getGoodsFeature() {
		return goodsFeature;
	}

	public void setGoodsFeature(String goodsFeature) {
		this.goodsFeature = goodsFeature;
	}

	/**
	 * default constructor
	 */
	public Injurycase() {
	}

	public Injurycase(Integer id, UserRole userRole, String caseNumber, String caseType, String caseName, String fillUnit, String fillName, String fillTime, String briefCase, String appraiser, String appraiserUnitName, String telphone, String injuryAssess, String endSituation, String comprehensiveJudge, String leaderInstruction, List<Otherperson> otherpersons, List<Troubleshooting> troubleshootings, List<Judge> judges, List<Media> medias, String joinDate, Integer handleState, Integer itype, String imageCase, String detailsDescription, String goodsDescription, Integer isCanvas, String caseIds, Integer isRelated, String casePlace, String startTime, String series, Integer isOutOfTime, Integer isNew, String situationNum, String crimeTarget, String crimeObject, String crimePattern, String personFeature, String goodsFeature) {
		this.id = id;
		this.userRole = userRole;
		this.caseNumber = caseNumber;
		this.caseType = caseType;
		this.caseName = caseName;
		this.fillUnit = fillUnit;
		this.fillName = fillName;
		this.fillTime = fillTime;
		this.briefCase = briefCase;
		this.appraiser = appraiser;
		this.appraiserUnitName = appraiserUnitName;
		this.telphone = telphone;
		this.injuryAssess = injuryAssess;
		this.endSituation = endSituation;
		this.comprehensiveJudge = comprehensiveJudge;
		this.leaderInstruction = leaderInstruction;
		this.otherpersons = otherpersons;
		this.troubleshootings = troubleshootings;
		this.judges = judges;
		this.medias = medias;
		this.joinDate = joinDate;
		this.handleState = handleState;
		this.itype = itype;
		this.imageCase = imageCase;
		this.detailsDescription = detailsDescription;
		this.goodsDescription = goodsDescription;
		this.isCanvas = isCanvas;
		this.caseIds = caseIds;
		this.isRelated = isRelated;
		this.casePlace = casePlace;
		this.startTime = startTime;
		this.series = series;
		this.isOutOfTime = isOutOfTime;
		this.isNew = isNew;
		this.situationNum = situationNum;
		this.crimeTarget = crimeTarget;
		this.crimeObject = crimeObject;
		this.crimePattern = crimePattern;
		this.personFeature = personFeature;
		this.goodsFeature = goodsFeature;
	}


	/**
	 * full constructor
	 */
	@Column(name = "appraiserUnitName")
	public String getAppraiserUnitName() {
		return appraiserUnitName;
	}

	@Column(name = "appraiser")
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

	@Column(name = "caseName")
	public String getCaseName() {
		return this.caseName;
	}

	@Column(name = "caseNumber")
	public String getCaseNumber() {
		return this.caseNumber;
	}

	@Column(name = "casePlace")
	public String getCasePlace() {
		return casePlace;
	}

	@Column(name = "caseType")
	public String getCaseType() {
		return this.caseType;
	}

	@Column(name = "comprehensiveJudge")
	public String getComprehensiveJudge() {
		return comprehensiveJudge;
	}

	@Column(name = "endSituation")
	public String getEndSituation() {
		return this.endSituation;
	}

	@Column(name = "fillName")
	public String getFillName() {
		return this.fillName;
	}

	@Column(name = "fillTime")
	public String getFillTime() {
		return this.fillTime;
	}

	@Column(name = "fillUnit")
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

	@Column(name = "joinDate")
	public String getJoinDate() {
		return joinDate;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "injurycase")
	public List<Judge> getJudges() {
		return this.judges;
	}

	@Column(name = "series")
	public String getSeries() {
		return series;
	}

	@Column(name = "leaderInstruction")
	public String getLeaderInstruction() {
		return this.leaderInstruction;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "injurycase")
	public List<Media> getMedias() {
		return medias;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "injurycase")
	public List<Otherperson> getOtherpersons() {
		return this.otherpersons;
	}

	@Column(name = "startTime")
	public String getStartTime() {
		return startTime;
	}

	@Column(name = "telphone")
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

	@Column(name = "isOutOfTime")
	public Integer getIsOutOfTime() {
		return isOutOfTime;
	}

	@Column(name = "isNew")
	public Integer getIsNew() {
		return isNew;
	}

	public void setAppraiserUnitName(String appraiserUnitName) {
		this.appraiserUnitName = appraiserUnitName;
	}


	public void setIsNew(Integer isNew) {
		this.isNew = isNew;
	}

	public void setIsOutOfTime(Integer isOutOfTime) {
		this.isOutOfTime = isOutOfTime;
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

	public void setSeries(String series) {
		this.series = series;
	}

	public void setLeaderInstruction(String leaderInstruction) {
		this.leaderInstruction = leaderInstruction;
	}

	public void setMedias(List<Media> medias) {
		this.medias = medias;
	}

	public void setOtherpersons(List<Otherperson> otherpersons) {
		this.otherpersons = otherpersons;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
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