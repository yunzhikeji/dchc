package com.yz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Troubleshooting entity.疑难解答
 * 
 * @author lq
 */
@Entity
@Table(name = "troubleshooting", schema = "dbo", catalog = "dchc")
public class Troubleshooting implements java.io.Serializable {

	// Fields

	private Integer id;
	private Clue clue;//刑侦线索信息
	private Injurycase injurycase;//重伤案件信息
	private Person person;//人员信息
	private String title;//疑难标题
	private String flowNumber;//流水编号
	private String issuer;//发布人
	private String releaseTime;//发布时间
	private String question;//疑难问题
	private String answer;//疑难回答

	// Constructors

	/** default constructor */
	public Troubleshooting() {
	}

	/** full constructor */
	public Troubleshooting(Clue clue, Injurycase injurycase, Person person,
			String title, String flowNumber, String issuer, String releaseTime,
			String question, String answer) {
		this.clue = clue;
		this.injurycase = injurycase;
		this.person = person;
		this.title = title;
		this.flowNumber = flowNumber;
		this.issuer = issuer;
		this.releaseTime = releaseTime;
		this.question = question;
		this.answer = answer;
	}

	@Column(name = "answer")
	public String getAnswer() {
		return answer;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clid")
	public Clue getClue() {
		return this.clue;
	}

	@Column(name = "flowNumber", length = 30)
	public String getFlowNumber() {
		return this.flowNumber;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "inid")
	public Injurycase getInjurycase() {
		return this.injurycase;
	}

	@Column(name = "issuer", length = 30)
	public String getIssuer() {
		return this.issuer;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "perid")
	public Person getPerson() {
		return this.person;
	}

	@Column(name = "question", length = 255)
	public String getQuestion() {
		return question;
	}

	@Column(name = "releaseTime", length = 30)
	public String getReleaseTime() {
		return this.releaseTime;
	}

	@Column(name = "title", length = 30)
	public String getTitle() {
		return this.title;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public void setClue(Clue clue) {
		this.clue = clue;
	}

	public void setFlowNumber(String flowNumber) {
		this.flowNumber = flowNumber;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setInjurycase(Injurycase injurycase) {
		this.injurycase = injurycase;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	
	
	public void setPerson(Person person) {
		this.person = person;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}

	public void setTitle(String title) {
		this.title = title;
	}


}