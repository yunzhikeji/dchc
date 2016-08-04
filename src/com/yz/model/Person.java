package com.yz.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Person entity. 所有人员信息
 * 
 * @author lq
 */
@Entity
@Table(name = "person", schema = "dbo", catalog = "dchc")
public class Person implements java.io.Serializable {

	// Fields

	private Integer id;//
	private UserRole userRole; // 录入人员
	private SocialMan socialMan;
	private CommonClue commonClue;// 普通线索人员(普通线索)
	private DisappearMan disappearMan;// 失踪人员
	private AnalyzeMan analyzeMan;// 侵财分析人员
	private GuiltSafeguardMan guiltSafeguardMan;// 负案在逃、维稳人员
	private ContrastMan contrastMan;// 技术比中人员
	private GamblingCriminalMan gamblingCriminalMan;// 赌博人员，涉恶人员，涉黄人员，食药环人员，涉毒人员，留置盘问，侵财人员，刑事传唤
	private String number;// 人员编号
	private String name;// 人员姓名
	private String idcard;// 身份证号
	private String birthday;// 出生日期
	private String telphone;// 联系方式
	private String qq;// QQ
	private String wechat;// 微信
	private Integer sex;// 性别
	private String registerAddress;// 户籍地址
	private String registerAddressArea;// 户籍地址
	private String carrier;// 携带物品（多个以，隔开）
	private String carryTool;// 携带工具（多个以，隔开）
	private String endSituation;// 完结情况
	private String comprehensiveJudge;// 综合研判情况
	private String leaderInstruction;// 领导批示
	private Integer isMakeControl;// 是否布控
	private Integer type;// 类型（具体哪一种人员）1:赌博人员，2:涉恶人员，3:涉黄人员，4:食药环人员，5:涉毒人员，6:留置盘问，7:侵财人员，8:刑事传唤，9:负案在逃人员，10:维稳人员，11:失踪人员，12:侵财人员分析，13:技术比中人员，14:普通线索
	private List<Otherperson> otherpersons = new ArrayList<Otherperson>();// 其他人员，包括（同案人，嫌疑人，关系人）
	private List<Lawcase> lawcases = new ArrayList<Lawcase>();// 涉及案件
	private List<Troubleshooting> troubleshootings = new ArrayList<Troubleshooting>();// 疑难解答
	private List<Judge> judges = new ArrayList<Judge>();// 研判情况
	private String joinDate;// 录入时间
	private Integer handleState;// 办理状态(1:未办理 2：在办理 3：已完结 )
	private String photoImg;// 照片
	private Integer isOutOfTime;// 是否超期办理
	private Integer isNew;

	// Constructors

	/** default constructor */
	public Person() {
	}

	/** full constructor */
	public Person(UserRole userRole, CommonClue commonClue,
			DisappearMan disappearMan, AnalyzeMan analyzeMan,
			GuiltSafeguardMan guiltSafeguardMan, ContrastMan contrastMan,
			GamblingCriminalMan gamblingCriminalMan, String number,
			String name, String idcard, String birthday, String telphone,
			String qq, String wechat, Integer sex, Integer isNew,
			String registerAddress, String registerAddressArea, String carrier,
			String carryTool, String endSituation, String comprehensiveJudge,
			String leaderInstruction, Integer isMakeControl, Integer type,
			String joinDate, int handleState, String photoImg,
			Integer isOutOfTime, List<Otherperson> otherpersons,
			List<Lawcase> lawcases, List<Troubleshooting> troubleshootings,
			List<Judge> judges) {
		this.userRole = userRole;
		this.commonClue = commonClue;
		this.disappearMan = disappearMan;
		this.analyzeMan = analyzeMan;
		this.guiltSafeguardMan = guiltSafeguardMan;
		this.contrastMan = contrastMan;
		this.gamblingCriminalMan = gamblingCriminalMan;
		this.number = number;
		this.name = name;
		this.idcard = idcard;
		this.birthday = birthday;
		this.telphone = telphone;
		this.qq = qq;
		this.wechat = wechat;
		this.sex = sex;
		this.registerAddress = registerAddress;
		this.registerAddressArea = registerAddressArea;
		this.carrier = carrier;
		this.carryTool = carryTool;
		this.endSituation = endSituation;
		this.comprehensiveJudge = comprehensiveJudge;
		this.leaderInstruction = leaderInstruction;
		this.isMakeControl = isMakeControl;
		this.type = type;
		this.joinDate = joinDate;
		this.handleState = handleState;
		this.photoImg = photoImg;
		this.otherpersons = otherpersons;
		this.lawcases = lawcases;
		this.troubleshootings = troubleshootings;
		this.judges = judges;
		this.isOutOfTime = isOutOfTime;
		this.isNew = isNew;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "anid")
	public AnalyzeMan getAnalyzeMan() {
		return this.analyzeMan;
	}

	@Column(name = "birthday")
	public String getBirthday() {
		return this.birthday;
	}

	@Column(name = "carrier")
	public String getCarrier() {
		return this.carrier;
	}

	@Column(name = "carryTool")
	public String getCarryTool() {
		return this.carryTool;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "comid")
	public CommonClue getCommonClue() {
		return this.commonClue;
	}

	@Column(name = "comprehensiveJudge")
	public String getComprehensiveJudge() {
		return this.comprehensiveJudge;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "conid")
	public ContrastMan getContrastMan() {
		return this.contrastMan;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "disid")
	public DisappearMan getDisappearMan() {
		return this.disappearMan;
	}

	@Column(name = "endSituation")
	public String getEndSituation() {
		return this.endSituation;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "gaid")
	public GamblingCriminalMan getGamblingCriminalMan() {
		return this.gamblingCriminalMan;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "guid")
	public GuiltSafeguardMan getGuiltSafeguardMan() {
		return this.guiltSafeguardMan;
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

	@Column(name = "IDcard")
	public String getIdcard() {
		return this.idcard;
	}

	@Column(name = "isMakeControl")
	public Integer getIsMakeControl() {
		return this.isMakeControl;
	}

	@Column(name = "joinDate")
	public String getJoinDate() {
		return joinDate;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "person")
	public List<Judge> getJudges() {
		return this.judges;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "person")
	public List<Lawcase> getLawcases() {
		return this.lawcases;
	}

	@Column(name = "leaderInstruction")
	public String getLeaderInstruction() {
		return this.leaderInstruction;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	@Column(name = "number")
	public String getNumber() {
		return this.number;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "person")
	public List<Otherperson> getOtherpersons() {
		return this.otherpersons;
	}

	@Column(name = "photoImg")
	public String getPhotoImg() {
		return photoImg;
	}

	@Column(name = "qq")
	public String getQq() {
		return this.qq;
	}

	@Column(name = "registerAddress")
	public String getRegisterAddress() {
		return this.registerAddress;
	}

	@Column(name = "registerAddressArea")
	public String getRegisterAddressArea() {
		return registerAddressArea;
	}

	@Column(name = "sex")
	public Integer getSex() {
		return this.sex;
	}

	@Column(name = "telphone")
	public String getTelphone() {
		return this.telphone;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "person")
	public List<Troubleshooting> getTroubleshootings() {
		return this.troubleshootings;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uid")
	public UserRole getUserRole() {
		return this.userRole;
	}

	@Column(name = "wechat")
	public String getWechat() {
		return this.wechat;
	}

	@Column(name = "isOutOfTime")
	public Integer getIsOutOfTime() {
		return isOutOfTime;
	}

	@Column(name = "isNew")
	public Integer getIsNew() {
		return isNew;
	}

	public void setIsNew(Integer isNew) {
		this.isNew = isNew;
	}

	public void setIsOutOfTime(Integer isOutOfTime) {
		this.isOutOfTime = isOutOfTime;
	}

	public void setAnalyzeMan(AnalyzeMan analyzeMan) {
		this.analyzeMan = analyzeMan;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public void setCarryTool(String carryTool) {
		this.carryTool = carryTool;
	}

	public void setCommonClue(CommonClue commonClue) {
		this.commonClue = commonClue;
	}

	public void setComprehensiveJudge(String comprehensiveJudge) {
		this.comprehensiveJudge = comprehensiveJudge;
	}

	public void setContrastMan(ContrastMan contrastMan) {
		this.contrastMan = contrastMan;
	}

	public void setDisappearMan(DisappearMan disappearMan) {
		this.disappearMan = disappearMan;
	}

	public void setEndSituation(String endSituation) {
		this.endSituation = endSituation;
	}

	public void setGamblingCriminalMan(GamblingCriminalMan gamblingCriminalMan) {
		this.gamblingCriminalMan = gamblingCriminalMan;
	}

	public void setGuiltSafeguardMan(GuiltSafeguardMan guiltSafeguardMan) {
		this.guiltSafeguardMan = guiltSafeguardMan;
	}

	public void setHandleState(Integer handleState) {
		this.handleState = handleState;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public void setIsMakeControl(Integer isMakeControl) {
		this.isMakeControl = isMakeControl;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public void setJudges(List<Judge> judges) {
		this.judges = judges;
	}

	public void setLawcases(List<Lawcase> lawcases) {
		this.lawcases = lawcases;
	}

	public void setLeaderInstruction(String leaderInstruction) {
		this.leaderInstruction = leaderInstruction;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setOtherpersons(List<Otherperson> otherpersons) {
		this.otherpersons = otherpersons;
	}

	public void setPhotoImg(String photoImg) {
		this.photoImg = photoImg;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public void setRegisterAddress(String registerAddress) {
		this.registerAddress = registerAddress;
	}

	public void setRegisterAddressArea(String registerAddressArea) {
		this.registerAddressArea = registerAddressArea;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public void setTroubleshootings(List<Troubleshooting> troubleshootings) {
		this.troubleshootings = troubleshootings;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "soid")
	public SocialMan getSocialMan() {
		return socialMan;
	}

	public void setSocialMan(SocialMan socialMan) {
		this.socialMan = socialMan;
	}

}