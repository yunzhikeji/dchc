package com.yz.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Case entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "lawcase", schema = "dbo", catalog = "dchc")
public class Lawcase implements java.io.Serializable {

	// Fields

	private Integer id;
	private Clue clue;
	private Person person;
	private String caseNumber;
	private String caseType;
	private String caseName;
	private String fillUnit;
	private String fillName;
	private String fillTime;
	private String briefCase;

	// Constructors

	/** default constructor */
	public Lawcase() {
	}

	/** full constructor */
	public Lawcase(Clue clue, Person person, String caseNumber, String caseType,
			String caseName, String fillUnit, String fillName, String fillTime,
			String briefCase) {
		this.clue = clue;
		this.person = person;
		this.caseNumber = caseNumber;
		this.caseType = caseType;
		this.caseName = caseName;
		this.fillUnit = fillUnit;
		this.fillName = fillName;
		this.fillTime = fillTime;
		this.briefCase = briefCase;
	}

	@Column(name = "briefCase")
	public String getBriefCase() {
		return this.briefCase;
	}

	@Column(name = "caseName", length = 30)
	public String getCaseName() {
		return this.caseName;
	}

	@Column(name = "caseNumber", length = 30)
	public String getCaseNumber() {
		return this.caseNumber;
	}

	@Column(name = "caseType", length = 30)
	public String getCaseType() {
		return this.caseType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clid")
	public Clue getClue() {
		return this.clue;
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

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	@ManyToOne(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
	@JoinColumn(name = "perid")
	public Person getPerson() {
		return this.person;
	}

	public void setBriefCase(String briefCase) {
		this.briefCase = briefCase;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public void setClue(Clue clue) {
		this.clue = clue;
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

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}