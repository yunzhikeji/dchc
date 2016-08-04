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
 * CommonClue entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "commonClue", schema = "dbo", catalog = "dchc")
public class CommonClue implements java.io.Serializable {

	// Fields

	private Integer id;
	private String nickname;
	private String launchTime;
	private String virtualId;
	private String bankCard;
	private String askHelpContent;
	private String registerAddressPhoto;
	private String criminalRecordPhoto;
	private Person person;

	// Constructors

	/** default constructor */
	public CommonClue() {
	}

	/** full constructor */
	public CommonClue(String launchTime, String virtualId, String bankCard,String nickname,
			String askHelpContent, String registerAddressPhoto,
			String criminalRecordPhoto, Person person) {
		this.nickname = nickname;
		this.launchTime = launchTime;
		this.virtualId = virtualId;
		this.bankCard = bankCard;
		this.askHelpContent = askHelpContent;
		this.registerAddressPhoto = registerAddressPhoto;
		this.criminalRecordPhoto = criminalRecordPhoto;
		this.person = person;
	}

	
	
	@Column(name = "askHelpContent")
	public String getAskHelpContent() {
		return this.askHelpContent;
	}

	@Column(name = "bankCard")
	public String getBankCard() {
		return this.bankCard;
	}

	@Column(name = "criminalRecordPhoto")
	public String getCriminalRecordPhoto() {
		return this.criminalRecordPhoto;
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

	@Column(name = "nickname")
	public String getNickname() {
		return nickname;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "commonClue")
	public Person getPerson() {
		return this.person;
	}

	@Column(name = "registerAddressPhoto")
	public String getRegisterAddressPhoto() {
		return this.registerAddressPhoto;
	}

	@Column(name = "virtualID")
	public String getVirtualId() {
		return this.virtualId;
	}

	public void setAskHelpContent(String askHelpContent) {
		this.askHelpContent = askHelpContent;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public void setCriminalRecordPhoto(String criminalRecordPhoto) {
		this.criminalRecordPhoto = criminalRecordPhoto;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLaunchTime(String launchTime) {
		this.launchTime = launchTime;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public void setRegisterAddressPhoto(String registerAddressPhoto) {
		this.registerAddressPhoto = registerAddressPhoto;
	}

	public void setVirtualId(String virtualId) {
		this.virtualId = virtualId;
	}

}