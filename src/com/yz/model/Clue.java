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
 * Clue entity.
 * 
 * @author MyEclipse Persistence Tools 刑侦线索，团伙系列案件
 */
@Entity
@Table(name = "clue", schema = "dbo", catalog = "dchc")
public class Clue implements java.io.Serializable {

	// Fields

	private Integer id;
	private UserRole userRole; // 录入人员
	private String number; // 编号
	private String securityClassification; // 密级
	private String intelligenceType; // 情报类型
	private String title; // 标题
	private String contactName; // 联系人
	private String telphone; // 电话号码
	private String carrier; // 携带物品
	private String carryTool; // 携带工具
	private String clueMessage; // 线索信息
	private String endSituation; // 完结情况 1抓获 2死亡 3撤销案件 4释放 5治安拘留 6刑事拘留 7留置盘问
	// 8其它
	private String comprehensiveJudge; // 综合研判情况
	private String leaderInstruction; // 领导批示
	private Integer ctype; // 类型
	private List<Lawcase> lawcases = new ArrayList<Lawcase>();
	private List<Troubleshooting> troubleshootings = new ArrayList<Troubleshooting>();
	private List<Otherperson> otherpersons = new ArrayList<Otherperson>();
	private List<Judge> judges = new ArrayList<Judge>();
	private String joinDate;
	private Integer handleState;// 办理状态
	private Integer isOutOfTime;// 是否超期办理
	private Integer isNew;

	// Constructors

	/** default constructor */
	public Clue() {
	}

	/** full constructor */
	public Clue(UserRole userRole, String number,
			String securityClassification, String title, String contactName,
			String telphone, String carrier, String carryTool,
			String clueMessage, String endSituation, String comprehensiveJudge,
			String leaderInstruction, Integer ctype, String joinDate,
			Integer handleState, Integer isOutOfTime, Integer isNew,
			List<Lawcase> lawcases, List<Troubleshooting> troubleshootings,
			List<Otherperson> otherpersons, List<Judge> judges) {
		this.userRole = userRole;
		this.number = number;
		this.securityClassification = securityClassification;
		this.title = title;
		this.contactName = contactName;
		this.telphone = telphone;
		this.carrier = carrier;
		this.carryTool = carryTool;
		this.clueMessage = clueMessage;
		this.endSituation = endSituation;
		this.comprehensiveJudge = comprehensiveJudge;
		this.leaderInstruction = leaderInstruction;
		this.ctype = ctype;
		this.joinDate = joinDate;
		this.handleState = handleState;
		this.lawcases = lawcases;
		this.troubleshootings = troubleshootings;
		this.otherpersons = otherpersons;
		this.judges = judges;
		this.isOutOfTime = isOutOfTime;
		this.isNew = isNew;
	}

	@Column(name = "carrier")
	public String getCarrier() {
		return this.carrier;
	}

	@Column(name = "carryTool")
	public String getCarryTool() {
		return this.carryTool;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "clue")
	public List<Lawcase> getLawcases() {
		return this.lawcases;
	}

	@Column(name = "clueMessage")
	public String getClueMessage() {
		return this.clueMessage;
	}

	@Column(name = "comprehensiveJudge")
	public String getComprehensiveJudge() {
		return this.comprehensiveJudge;
	}

	@Column(name = "contactName", length = 20)
	public String getContactName() {
		return this.contactName;
	}

	@Column(name = "endSituation", length = 100)
	public String getEndSituation() {
		return this.endSituation;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	@Column(name = "joinDate", length = 30)
	public String getJoinDate() {
		return joinDate;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "clue")
	public List<Judge> getJudges() {
		return this.judges;
	}

	@Column(name = "leaderInstruction")
	public String getLeaderInstruction() {
		return this.leaderInstruction;
	}

	@Column(name = "number", length = 50)
	public String getNumber() {
		return this.number;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "clue")
	public List<Otherperson> getOtherpersons() {
		return this.otherpersons;
	}

	@Column(name = "securityClassification", length = 20)
	public String getSecurityClassification() {
		return this.securityClassification;
	}

	@Column(name = "telphone", length = 30)
	public String getTelphone() {
		return this.telphone;
	}

	@Column(name = "title", length = 30)
	public String getTitle() {
		return this.title;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "clue")
	public List<Troubleshooting> getTroubleshootings() {
		return this.troubleshootings;
	}

	@Column(name = "ctype")
	public Integer getCtype() {
		return this.ctype;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uid")
	public UserRole getUserRole() {
		return this.userRole;
	}

	@Column(name = "handleState")
	public Integer getHandleState() {
		return handleState;
	}

	@Column(name = "isOutOfTime", length = 11)
	public Integer getIsOutOfTime() {
		return isOutOfTime;
	}

	@Column(name = "isNew", length = 11)
	public Integer getIsNew() {
		return isNew;
	}

	public void setIsNew(Integer isNew) {
		this.isNew = isNew;
	}

	public void setIsOutOfTime(Integer isOutOfTime) {
		this.isOutOfTime = isOutOfTime;
	}

	public void setHandleState(Integer handleState) {
		this.handleState = handleState;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public void setCarryTool(String carryTool) {
		this.carryTool = carryTool;
	}

	public void setLawcases(List<Lawcase> lawcases) {
		this.lawcases = lawcases;
	}

	public void setClueMessage(String clueMessage) {
		this.clueMessage = clueMessage;
	}

	public void setComprehensiveJudge(String comprehensiveJudge) {
		this.comprehensiveJudge = comprehensiveJudge;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public void setEndSituation(String endSituation) {
		this.endSituation = endSituation;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public void setNumber(String number) {
		this.number = number;
	}

	public void setOtherpersons(List<Otherperson> otherpersons) {
		this.otherpersons = otherpersons;
	}

	public void setSecurityClassification(String securityClassification) {
		this.securityClassification = securityClassification;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setTroubleshootings(List<Troubleshooting> troubleshootings) {
		this.troubleshootings = troubleshootings;
	}

	public void setCtype(Integer ctype) {
		this.ctype = ctype;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public String getIntelligenceType() {
		return intelligenceType;
	}

	public void setIntelligenceType(String intelligenceType) {
		this.intelligenceType = intelligenceType;
	}

}