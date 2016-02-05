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
	private UserRole userRole;
	private String number;
	private String securityClassification;
	private String title;
	private String contactName;
	private String telphone;
	private String carrier;
	private String carryTool;
	private String clueMessage;
	private String endSituation;
	private String comprehensiveJudge;
	private String leaderInstruction;
	private Integer type;
	private List<Lawcase> lawcases = new ArrayList<Lawcase>();
	private List<Troubleshooting> troubleshootings = new ArrayList<Troubleshooting>();
	private List<Otherperson> otherpersons = new ArrayList<Otherperson>();
	private List<Judge> judges = new ArrayList<Judge>();
	private String joinDate;
	private Integer handleState;//办理状态

	// Constructors

	/** default constructor */
	public Clue() {
	}

	/** full constructor */
	public Clue(UserRole userRole, String number, String securityClassification,
			String title, String contactName, String telphone, String carrier,
			String carryTool, String clueMessage, String endSituation,
			String comprehensiveJudge, String leaderInstruction, Integer type,String joinDate,Integer handleState,
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
		this.type = type;
		this.joinDate = joinDate;
		this.handleState = handleState;
		this.lawcases = lawcases;
		this.troubleshootings = troubleshootings;
		this.otherpersons = otherpersons;
		this.judges = judges;
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

	@Column(name = "joinDate",length=30)
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
	
	@Column(name = "type")
	public Integer getType() {
		return this.type;
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

	public void setType(Integer type) {
		this.type = type;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

}