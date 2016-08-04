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
 * DisappearMan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "disappearMan", schema = "dbo", catalog = "dchc")
public class DisappearMan implements java.io.Serializable {

	// Fields

	private Integer id;
	private String dnanumber;
	private String photo1;
	private String photo2;
	private String photo3;
	private String nickname;//别名
	private String currentAddressArea;
	private String currentAddress;
	private String foreignName;//外文名
	private String otherIdname;//其他证件名称
	private String otherIdnumber;//其他证件号码
	private String unitContactName;//单位联系人
	private String unitContactTelphone;//单位联系电话
	private String reportContactName;//报案联系人姓名
	private String reportContactTelphone;//报案联系电话
	private String missingAddress;//失踪地址
	private String missingStartTime;//失踪日期范围开始
	private String missingEndTime;//失踪日期范围结束
	private String foundMissingTime;//发现失踪日期
	private String missingCause;//
	private String height;
	private String shape;
	private String feature;
	private String footLength;
	private String bloodType;
	private String accent;
	private String specificFeature;
	private String specificFeatureCon;
	private String bodyFeature;
	private String dressSituation;
	private String relativeBlood;
	private String revocateUnit;
	private String revocateName;
	private String revocateTime;
	private String revocateReason;
	private Person person;

	// Constructors

	/** default constructor */
	public DisappearMan() {
	}

	/** full constructor */
	public DisappearMan(String dnanumber, String photo1, String photo2,
			String photo3, String nickname, String currentAddressArea,String currentAddress,
			String foreignName, String otherIdname, String otherIdnumber,
			String unitContactName, String unitContactTelphone,
			String reportContactName, String reportContactTelphone,
			String missingAddress, String missingStartTime,
			String missingEndTime, String foundMissingTime,
			String missingCause, String height, String shape, String feature,
			String footLength, String bloodType, String accent,
			String specificFeature,String specificFeatureCon,String bodyFeature, String dressSituation,
			String relativeBlood, String revocateUnit, String revocateName,
			String revocateTime, String revocateReason, Person person) {
		this.dnanumber = dnanumber;
		this.photo1 = photo1;
		this.photo2 = photo2;
		this.photo3 = photo3;
		this.nickname = nickname;
		this.currentAddressArea = currentAddressArea;
		this.currentAddress = currentAddress;
		this.foreignName = foreignName;
		this.otherIdname = otherIdname;
		this.otherIdnumber = otherIdnumber;
		this.unitContactName = unitContactName;
		this.unitContactTelphone = unitContactTelphone;
		this.reportContactName = reportContactName;
		this.reportContactTelphone = reportContactTelphone;
		this.missingAddress = missingAddress;
		this.missingStartTime = missingStartTime;
		this.missingEndTime = missingEndTime;
		this.foundMissingTime = foundMissingTime;
		this.missingCause = missingCause;
		this.height = height;
		this.shape = shape;
		this.feature = feature;
		this.footLength = footLength;
		this.bloodType = bloodType;
		this.accent = accent;
		this.specificFeature = specificFeature;
		this.specificFeatureCon = specificFeatureCon;
		this.bodyFeature = bodyFeature;
		this.dressSituation = dressSituation;
		this.relativeBlood = relativeBlood;
		this.revocateUnit = revocateUnit;
		this.revocateName = revocateName;
		this.revocateTime = revocateTime;
		this.revocateReason = revocateReason;
		this.person = person;
	}

	@Column(name = "accent")
	public String getAccent() {
		return this.accent;
	}

	@Column(name = "bloodType")
	public String getBloodType() {
		return this.bloodType;
	}

	@Column(name = "bodyFeature")
	public String getBodyFeature() {
		return bodyFeature;
	}

	@Column(name = "currentAddress")
	public String getCurrentAddress() {
		return currentAddress;
	}

	@Column(name = "currentAddressArea")
	public String getCurrentAddressArea() {
		return this.currentAddressArea;
	}

	@Column(name = "DNANumber")
	public String getDnanumber() {
		return this.dnanumber;
	}

	@Column(name = "dressSituation")
	public String getDressSituation() {
		return this.dressSituation;
	}

	@Column(name = "feature")
	public String getFeature() {
		return this.feature;
	}

	@Column(name = "footLength")
	public String getFootLength() {
		return this.footLength;
	}

	@Column(name = "foreignName")
	public String getForeignName() {
		return this.foreignName;
	}

	@Column(name = "foundMissingTime")
	public String getFoundMissingTime() {
		return this.foundMissingTime;
	}

	@Column(name = "height")
	public String getHeight() {
		return this.height;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	@Column(name = "missingAddress")
	public String getMissingAddress() {
		return this.missingAddress;
	}

	@Column(name = "missingCause")
	public String getMissingCause() {
		return this.missingCause;
	}

	@Column(name = "missingEndTime")
	public String getMissingEndTime() {
		return this.missingEndTime;
	}

	@Column(name = "missingStartTime")
	public String getMissingStartTime() {
		return this.missingStartTime;
	}

	@Column(name = "nickname")
	public String getNickname() {
		return this.nickname;
	}

	@Column(name = "otherIDName")
	public String getOtherIdname() {
		return this.otherIdname;
	}

	@Column(name = "otherIDNumber")
	public String getOtherIdnumber() {
		return this.otherIdnumber;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "disappearMan")
	public Person getPerson() {
		return this.person;
	}

	@Column(name = "photo1")
	public String getPhoto1() {
		return this.photo1;
	}

	@Column(name = "photo2")
	public String getPhoto2() {
		return this.photo2;
	}

	@Column(name = "photo3")
	public String getPhoto3() {
		return this.photo3;
	}

	@Column(name = "relativeBlood")
	public String getRelativeBlood() {
		return this.relativeBlood;
	}

	@Column(name = "reportContactName")
	public String getReportContactName() {
		return this.reportContactName;
	}

	@Column(name = "reportContactTelphone")
	public String getReportContactTelphone() {
		return this.reportContactTelphone;
	}

	@Column(name = "revocateName")
	public String getRevocateName() {
		return this.revocateName;
	}

	@Column(name = "revocateReason")
	public String getRevocateReason() {
		return this.revocateReason;
	}

	@Column(name = "revocateTime")
	public String getRevocateTime() {
		return this.revocateTime;
	}

	@Column(name = "revocateUnit")
	public String getRevocateUnit() {
		return this.revocateUnit;
	}

	@Column(name = "shape")
	public String getShape() {
		return this.shape;
	}

	@Column(name = "specificFeature")
	public String getSpecificFeature() {
		return this.specificFeature;
	}
	
	@Column(name = "specificFeatureCon")
	public String getSpecificFeatureCon() {
		return specificFeatureCon;
	}

	@Column(name = "unitContactName")
	public String getUnitContactName() {
		return this.unitContactName;
	}

	@Column(name = "unitContactTelphone")
	public String getUnitContactTelphone() {
		return this.unitContactTelphone;
	}

	public void setAccent(String accent) {
		this.accent = accent;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public void setBodyFeature(String bodyFeature) {
		this.bodyFeature = bodyFeature;
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

	public void setDressSituation(String dressSituation) {
		this.dressSituation = dressSituation;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public void setFootLength(String footLength) {
		this.footLength = footLength;
	}

	public void setForeignName(String foreignName) {
		this.foreignName = foreignName;
	}

	public void setFoundMissingTime(String foundMissingTime) {
		this.foundMissingTime = foundMissingTime;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setMissingAddress(String missingAddress) {
		this.missingAddress = missingAddress;
	}

	public void setMissingCause(String missingCause) {
		this.missingCause = missingCause;
	}

	public void setMissingEndTime(String missingEndTime) {
		this.missingEndTime = missingEndTime;
	}

	public void setMissingStartTime(String missingStartTime) {
		this.missingStartTime = missingStartTime;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setOtherIdname(String otherIdname) {
		this.otherIdname = otherIdname;
	}

	public void setOtherIdnumber(String otherIdnumber) {
		this.otherIdnumber = otherIdnumber;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public void setPhoto1(String photo1) {
		this.photo1 = photo1;
	}

	public void setPhoto2(String photo2) {
		this.photo2 = photo2;
	}

	public void setPhoto3(String photo3) {
		this.photo3 = photo3;
	}

	public void setRelativeBlood(String relativeBlood) {
		this.relativeBlood = relativeBlood;
	}

	public void setReportContactName(String reportContactName) {
		this.reportContactName = reportContactName;
	}

	public void setReportContactTelphone(String reportContactTelphone) {
		this.reportContactTelphone = reportContactTelphone;
	}

	public void setRevocateName(String revocateName) {
		this.revocateName = revocateName;
	}

	public void setRevocateReason(String revocateReason) {
		this.revocateReason = revocateReason;
	}

	public void setRevocateTime(String revocateTime) {
		this.revocateTime = revocateTime;
	}

	public void setRevocateUnit(String revocateUnit) {
		this.revocateUnit = revocateUnit;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	public void setSpecificFeature(String specificFeature) {
		this.specificFeature = specificFeature;
	}

	public void setSpecificFeatureCon(String specificFeatureCon) {
		this.specificFeatureCon = specificFeatureCon;
	}

	public void setUnitContactName(String unitContactName) {
		this.unitContactName = unitContactName;
	}

	public void setUnitContactTelphone(String unitContactTelphone) {
		this.unitContactTelphone = unitContactTelphone;
	}
	

}