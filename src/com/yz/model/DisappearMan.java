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
	private String nickname;
	private String currentAddressArea;
	private String foreignName;
	private String otherIdname;
	private String otherIdnumber;
	private String unitContactName;
	private String unitContactTelphone;
	private String reportContactName;
	private String reportContactTelphone;
	private String missingAddress;
	private String missingStartTime;
	private String missingEndTime;
	private String foundMissingTime;
	private String missingCause;
	private Integer height;
	private String shape;
	private String feature;
	private Integer footLength;
	private String bloodType;
	private String accent;
	private String specificFeature;
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
			String photo3, String nickname, String currentAddressArea,
			String foreignName, String otherIdname, String otherIdnumber,
			String unitContactName, String unitContactTelphone,
			String reportContactName, String reportContactTelphone,
			String missingAddress, String missingStartTime,
			String missingEndTime, String foundMissingTime,
			String missingCause, Integer height, String shape, String feature,
			Integer footLength, String bloodType, String accent,
			String specificFeature, String dressSituation,
			String relativeBlood, String revocateUnit, String revocateName,
			String revocateTime, String revocateReason, Person person) {
		this.dnanumber = dnanumber;
		this.photo1 = photo1;
		this.photo2 = photo2;
		this.photo3 = photo3;
		this.nickname = nickname;
		this.currentAddressArea = currentAddressArea;
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
		this.dressSituation = dressSituation;
		this.relativeBlood = relativeBlood;
		this.revocateUnit = revocateUnit;
		this.revocateName = revocateName;
		this.revocateTime = revocateTime;
		this.revocateReason = revocateReason;
		this.person = person;
	}

	@Column(name = "accent", length = 20)
	public String getAccent() {
		return this.accent;
	}

	@Column(name = "bloodType", length = 20)
	public String getBloodType() {
		return this.bloodType;
	}

	@Column(name = "currentAddressArea", length = 50)
	public String getCurrentAddressArea() {
		return this.currentAddressArea;
	}

	@Column(name = "DNANumber", length = 30)
	public String getDnanumber() {
		return this.dnanumber;
	}

	@Column(name = "dressSituation", length = 50)
	public String getDressSituation() {
		return this.dressSituation;
	}

	@Column(name = "feature", length = 20)
	public String getFeature() {
		return this.feature;
	}

	@Column(name = "footLength")
	public Integer getFootLength() {
		return this.footLength;
	}

	@Column(name = "foreignName", length = 25)
	public String getForeignName() {
		return this.foreignName;
	}

	@Column(name = "foundMissingTime", length = 30)
	public String getFoundMissingTime() {
		return this.foundMissingTime;
	}

	@Column(name = "height")
	public Integer getHeight() {
		return this.height;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	@Column(name = "missingAddress", length = 50)
	public String getMissingAddress() {
		return this.missingAddress;
	}

	@Column(name = "missingCause")
	public String getMissingCause() {
		return this.missingCause;
	}

	@Column(name = "missingEndTime", length = 30)
	public String getMissingEndTime() {
		return this.missingEndTime;
	}

	@Column(name = "missingStartTime", length = 30)
	public String getMissingStartTime() {
		return this.missingStartTime;
	}

	@Column(name = "nickname", length = 20)
	public String getNickname() {
		return this.nickname;
	}

	@Column(name = "otherIDName", length = 25)
	public String getOtherIdname() {
		return this.otherIdname;
	}

	@Column(name = "otherIDNumber", length = 50)
	public String getOtherIdnumber() {
		return this.otherIdnumber;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "disappearMan")
	public Person getPerson() {
		return this.person;
	}

	@Column(name = "photo1", length = 30)
	public String getPhoto1() {
		return this.photo1;
	}

	@Column(name = "photo2", length = 30)
	public String getPhoto2() {
		return this.photo2;
	}

	@Column(name = "photo3", length = 30)
	public String getPhoto3() {
		return this.photo3;
	}

	@Column(name = "relativeBlood")
	public String getRelativeBlood() {
		return this.relativeBlood;
	}

	@Column(name = "reportContactName", length = 25)
	public String getReportContactName() {
		return this.reportContactName;
	}

	@Column(name = "reportContactTelphone", length = 30)
	public String getReportContactTelphone() {
		return this.reportContactTelphone;
	}

	@Column(name = "revocateName", length = 30)
	public String getRevocateName() {
		return this.revocateName;
	}

	@Column(name = "revocateReason")
	public String getRevocateReason() {
		return this.revocateReason;
	}

	@Column(name = "revocateTime", length = 30)
	public String getRevocateTime() {
		return this.revocateTime;
	}

	@Column(name = "revocateUnit", length = 30)
	public String getRevocateUnit() {
		return this.revocateUnit;
	}

	@Column(name = "shape", length = 20)
	public String getShape() {
		return this.shape;
	}

	@Column(name = "specificFeature")
	public String getSpecificFeature() {
		return this.specificFeature;
	}

	@Column(name = "unitContactName", length = 25)
	public String getUnitContactName() {
		return this.unitContactName;
	}

	@Column(name = "unitContactTelphone", length = 30)
	public String getUnitContactTelphone() {
		return this.unitContactTelphone;
	}

	public void setAccent(String accent) {
		this.accent = accent;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
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

	public void setFootLength(Integer footLength) {
		this.footLength = footLength;
	}

	public void setForeignName(String foreignName) {
		this.foreignName = foreignName;
	}

	public void setFoundMissingTime(String foundMissingTime) {
		this.foundMissingTime = foundMissingTime;
	}

	public void setHeight(Integer height) {
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

	public void setUnitContactName(String unitContactName) {
		this.unitContactName = unitContactName;
	}

	public void setUnitContactTelphone(String unitContactTelphone) {
		this.unitContactTelphone = unitContactTelphone;
	}

}