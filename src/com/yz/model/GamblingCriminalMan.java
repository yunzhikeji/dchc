package com.yz.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * GamblingCriminalMan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "gamblingCriminalMan", schema = "dbo", catalog = "dchc")
public class GamblingCriminalMan implements java.io.Serializable {

	// Fields

	private Integer id;
	private String dnanumber;// DNA编号
	private String fingerPrintNumber;// 指纹编号
	private String currentAddressArea;// 现住地区划
	private String currentAddress;// 现住地详址
	private String otherId;// 其它身份信息
	private String virtualId;// 虚拟身份
	private String bankCard;// 银行卡信息
	private String nickname;// 绰号
	private String footPrintNumber;// 足迹编号
	private String carLicenseNumber;// 车牌号
	private String engineNumber;// 发动机号
	private String carFrameNumber;// 车架号
	private String imei;// 手机串号
	private String infoExtraction;// 信息提取情况
	private String casinoRole;// 赌场角色
	private String gambleType;// 赌博形式
	private Integer isDrugRelated;// 是否涉毒
	private String equivocation;// 可疑度
	private String interrogateReason;// 盘问原因
	private Person person;

	// Constructors

	/** default constructor */
	public GamblingCriminalMan() {
	}

	/** full constructor */
	public GamblingCriminalMan(String dnanumber, String fingerPrintNumber,
			String currentAddressArea, String currentAddress, String otherId,
			String virtualId, String bankCard, String nickname,
			String footPrintNumber, String carLicenseNumber,
			String engineNumber, String carFrameNumber, String imei,
			String infoExtraction, String casinoRole, String gambleType,
			Integer isDrugRelated, String equivocation,
			String interrogateReason, Person person) {
		this.dnanumber = dnanumber;
		this.fingerPrintNumber = fingerPrintNumber;
		this.currentAddressArea = currentAddressArea;
		this.currentAddress = currentAddress;
		this.otherId = otherId;
		this.virtualId = virtualId;
		this.bankCard = bankCard;
		this.nickname = nickname;
		this.footPrintNumber = footPrintNumber;
		this.carLicenseNumber = carLicenseNumber;
		this.engineNumber = engineNumber;
		this.carFrameNumber = carFrameNumber;
		this.imei = imei;
		this.infoExtraction = infoExtraction;
		this.casinoRole = casinoRole;
		this.gambleType = gambleType;
		this.isDrugRelated = isDrugRelated;
		this.equivocation = equivocation;
		this.interrogateReason = interrogateReason;
		this.person = person;
	}

	@Column(name = "bankCard")
	public String getBankCard() {
		return this.bankCard;
	}

	@Column(name = "carFrameNumber")
	public String getCarFrameNumber() {
		return this.carFrameNumber;
	}

	@Column(name = "carLicenseNumber")
	public String getCarLicenseNumber() {
		return this.carLicenseNumber;
	}

	@Column(name = "casinoRole")
	public String getCasinoRole() {
		return this.casinoRole;
	}

	@Column(name = "currentAddress")
	public String getCurrentAddress() {
		return this.currentAddress;
	}

	@Column(name = "currentAddressArea")
	public String getCurrentAddressArea() {
		return this.currentAddressArea;
	}

	@Column(name = "DNANumber")
	public String getDnanumber() {
		return this.dnanumber;
	}

	@Column(name = "engineNumber")
	public String getEngineNumber() {
		return this.engineNumber;
	}

	@Column(name = "equivocation")
	public String getEquivocation() {
		return this.equivocation;
	}

	@Column(name = "fingerPrintNumber")
	public String getFingerPrintNumber() {
		return this.fingerPrintNumber;
	}

	@Column(name = "footPrintNumber")
	public String getFootPrintNumber() {
		return this.footPrintNumber;
	}

	@Column(name = "gambleType")
	public String getGambleType() {
		return this.gambleType;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	@Column(name = "IMEI")
	public String getImei() {
		return this.imei;
	}

	@Column(name = "infoExtraction",length = 100)
	public String getInfoExtraction() {
		return this.infoExtraction;
	}

	@Column(name = "interrogateReason")
	public String getInterrogateReason() {
		return this.interrogateReason;
	}

	@Column(name = "isDrugRelated")
	public Integer getIsDrugRelated() {
		return this.isDrugRelated;
	}

	@Column(name = "nickname")
	public String getNickname() {
		return this.nickname;
	}

	@Column(name = "otherID")
	public String getOtherId() {
		return this.otherId;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "gamblingCriminalMan")
	public Person getPerson() {
		return this.person;
	}

	@Column(name = "virtualID", length = 50)
	public String getVirtualId() {
		return this.virtualId;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public void setCarFrameNumber(String carFrameNumber) {
		this.carFrameNumber = carFrameNumber;
	}

	public void setCarLicenseNumber(String carLicenseNumber) {
		this.carLicenseNumber = carLicenseNumber;
	}

	public void setCasinoRole(String casinoRole) {
		this.casinoRole = casinoRole;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}

	public void setCurrentAddressArea(String currentAddressArea) {
		this.currentAddressArea = currentAddressArea;
	}

	public void setDnanumber(String dnanumber) {
		this.dnanumber = dnanumber;
	}

	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}

	public void setEquivocation(String equivocation) {
		this.equivocation = equivocation;
	}

	public void setFingerPrintNumber(String fingerPrintNumber) {
		this.fingerPrintNumber = fingerPrintNumber;
	}

	public void setFootPrintNumber(String footPrintNumber) {
		this.footPrintNumber = footPrintNumber;
	}

	public void setGambleType(String gambleType) {
		this.gambleType = gambleType;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public void setInfoExtraction(String infoExtraction) {
		this.infoExtraction = infoExtraction;
	}

	public void setInterrogateReason(String interrogateReason) {
		this.interrogateReason = interrogateReason;
	}

	public void setIsDrugRelated(Integer isDrugRelated) {
		this.isDrugRelated = isDrugRelated;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setOtherId(String otherId) {
		this.otherId = otherId;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public void setVirtualId(String virtualId) {
		this.virtualId = virtualId;
	}

}