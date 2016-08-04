package com.yz.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * GuiltSafeguardMan entity.负案在逃、维稳人员
 * 
 * @author lq
 */
@Entity
@Table(name = "guiltSafeguardMan", schema = "dbo", catalog = "dchc")
public class GuiltSafeguardMan implements java.io.Serializable {

	// Fields

	private Integer id;
	private String launchTime;//发起时间
	private String workdUnit;//工作单位
	private String temporaryAddress;//暂住地址
	private String location;//落脚点
	private String criminalRecordPhoto1;//前科照片1
	private String criminalRecordPhoto2;//前科照片2
	private String criminalRecordPhoto3;//前科照片3
	private Person person;

	// Constructors

	/** default constructor */
	public GuiltSafeguardMan() {
	}

	/** full constructor */
	public GuiltSafeguardMan(String launchTime, String workdUnit,
			String temporaryAddress, String location,
			String criminalRecordPhoto1, String criminalRecordPhoto2,
			String criminalRecordPhoto3, Person person) {
		this.launchTime = launchTime;
		this.workdUnit = workdUnit;
		this.temporaryAddress = temporaryAddress;
		this.location = location;
		this.criminalRecordPhoto1 = criminalRecordPhoto1;
		this.criminalRecordPhoto2 = criminalRecordPhoto2;
		this.criminalRecordPhoto3 = criminalRecordPhoto3;
		this.person = person;
	}

	@Column(name = "criminalRecordPhoto1")
	public String getCriminalRecordPhoto1() {
		return this.criminalRecordPhoto1;
	}

	@Column(name = "criminalRecordPhoto2")
	public String getCriminalRecordPhoto2() {
		return this.criminalRecordPhoto2;
	}

	@Column(name = "criminalRecordPhoto3")
	public String getCriminalRecordPhoto3() {
		return this.criminalRecordPhoto3;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	@Column(name = "launchTime")
	public String getLaunchTime() {
		return this.launchTime;
	}

	@Column(name = "location")
	public String getLocation() {
		return this.location;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "guiltSafeguardMan")
	public Person getPerson() {
		return this.person;
	}

	@Column(name = "temporaryAddress")
	public String getTemporaryAddress() {
		return this.temporaryAddress;
	}

	@Column(name = "workdUnit")
	public String getWorkdUnit() {
		return this.workdUnit;
	}

	public void setCriminalRecordPhoto1(String criminalRecordPhoto1) {
		this.criminalRecordPhoto1 = criminalRecordPhoto1;
	}

	public void setCriminalRecordPhoto2(String criminalRecordPhoto2) {
		this.criminalRecordPhoto2 = criminalRecordPhoto2;
	}

	public void setCriminalRecordPhoto3(String criminalRecordPhoto3) {
		this.criminalRecordPhoto3 = criminalRecordPhoto3;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLaunchTime(String launchTime) {
		this.launchTime = launchTime;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public void setTemporaryAddress(String temporaryAddress) {
		this.temporaryAddress = temporaryAddress;
	}

	public void setWorkdUnit(String workdUnit) {
		this.workdUnit = workdUnit;
	}

}