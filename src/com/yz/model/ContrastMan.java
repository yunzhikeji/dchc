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
 * ContrastMan entity.
 * 
 * @author MyEclipse Persistence Tools 技术比中人员
 */
@Entity
@Table(name = "contrastMan", schema = "dbo", catalog = "dchc")
public class ContrastMan implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer contrastType;//
	private String dnanumber;//
	private String fingerPrintNumber;
	private String contrastUnit;
	private String contrastName;
	private String contrastTime;
	private String collectionTime;
	private String criminalRecordSituation;
	private Integer manageUnit;
	private String registerAddressPhoto;
	private String criminalRecordPhoto;
	private Person person;

	// Constructors

	/** default constructor */
	public ContrastMan() {
	}

	/** full constructor */
	public ContrastMan(Integer contrastType, String dnanumber,
			String fingerPrintNumber, String contrastUnit, String contrastName,
			String contrastTime, String collectionTime,
			String criminalRecordSituation, Integer manageUnit,
			String registerAddressPhoto, String criminalRecordPhoto,
			Person person) {
		this.contrastType = contrastType;
		this.dnanumber = dnanumber;
		this.fingerPrintNumber = fingerPrintNumber;
		this.contrastUnit = contrastUnit;
		this.contrastName = contrastName;
		this.contrastTime = contrastTime;
		this.collectionTime = collectionTime;
		this.criminalRecordSituation = criminalRecordSituation;
		this.manageUnit = manageUnit;
		this.registerAddressPhoto = registerAddressPhoto;
		this.criminalRecordPhoto = criminalRecordPhoto;
		this.person = person;
	}

	@Column(name = "collectionTime")
	public String getCollectionTime() {
		return this.collectionTime;
	}

	@Column(name = "contrastName")
	public String getContrastName() {
		return this.contrastName;
	}

	@Column(name = "contrastTime")
	public String getContrastTime() {
		return this.contrastTime;
	}

	@Column(name = "contrastType")
	public Integer getContrastType() {
		return this.contrastType;
	}

	@Column(name = "contrastUnit")
	public String getContrastUnit() {
		return this.contrastUnit;
	}

	@Column(name = "criminalRecordPhoto")
	public String getCriminalRecordPhoto() {
		return this.criminalRecordPhoto;
	}

	@Column(name = "criminalRecordSituation")
	public String getCriminalRecordSituation() {
		return this.criminalRecordSituation;
	}

	@Column(name = "DNANumber")
	public String getDnanumber() {
		return this.dnanumber;
	}

	@Column(name = "fingerPrintNumber")
	public String getFingerPrintNumber() {
		return this.fingerPrintNumber;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	@Column(name = "manageUnit")
	public Integer getManageUnit() {
		return this.manageUnit;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "contrastMan")
	public Person getPerson() {
		return this.person;
	}

	@Column(name = "registerAddressPhoto")
	public String getRegisterAddressPhoto() {
		return this.registerAddressPhoto;
	}

	public void setCollectionTime(String collectionTime) {
		this.collectionTime = collectionTime;
	}

	public void setContrastName(String contrastName) {
		this.contrastName = contrastName;
	}

	public void setContrastTime(String contrastTime) {
		this.contrastTime = contrastTime;
	}

	public void setContrastType(Integer contrastType) {
		this.contrastType = contrastType;
	}

	public void setContrastUnit(String contrastUnit) {
		this.contrastUnit = contrastUnit;
	}

	public void setCriminalRecordPhoto(String criminalRecordPhoto) {
		this.criminalRecordPhoto = criminalRecordPhoto;
	}

	public void setCriminalRecordSituation(String criminalRecordSituation) {
		this.criminalRecordSituation = criminalRecordSituation;
	}

	public void setDnanumber(String dnanumber) {
		this.dnanumber = dnanumber;
	}

	public void setFingerPrintNumber(String fingerPrintNumber) {
		this.fingerPrintNumber = fingerPrintNumber;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setManageUnit(Integer manageUnit) {
		this.manageUnit = manageUnit;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public void setRegisterAddressPhoto(String registerAddressPhoto) {
		this.registerAddressPhoto = registerAddressPhoto;
	}

}