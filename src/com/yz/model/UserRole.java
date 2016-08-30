package com.yz.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
 * User entity. 角色（用户）
 * 
 * @author lq
 */
@Entity
@Table(name = "userRole", schema = "dbo", catalog = "dchc")
public class UserRole implements java.io.Serializable {

	// Fields
	private Integer id;
	private Unit unit;//单位 
	private String username;//用户名（登陆名）
	private String password;//密码
	private String telphone;//电话
	private String number;//警员编号
	private String realname;//真实姓名
	private String photo;//照片
	private Integer userLimit;//用户权限                 //权限说明：只有权限是0的能够做删除
	private Integer sex;//性别
	private String beforeLoginTime;
	private String currentLoginTime;
	private String cardid;//身份证
	private List<Person> persons = new ArrayList<Person>();//录入的人员信息
	private List<Injurycase> injurycases = new ArrayList<Injurycase>();//录入的重伤案件信息
	private List<Clue> clues = new ArrayList<Clue>();//录入的刑侦线索信息
	private List<Pnotice> pnotices = new ArrayList<Pnotice>();
	// Constructors

	/** default constructor */
	public UserRole() {
	}

	/** full constructor */
	public UserRole(Unit unit, String username, String password, String telphone,
			String number, String realname, String photo,String cardid, List<Person> persons,Integer userLimit,Integer sex,String beforeLoginTime,String currentLoginTime,
			List<Injurycase> injurycases, List<Clue> clues, List<Pnotice> pnotices) {
		this.unit = unit;
		this.username = username;
		this.password = password;
		this.telphone = telphone;
		this.number = number;
		this.realname = realname;
		this.photo = photo;
		this.userLimit = userLimit;
		this.sex = sex;
		this.beforeLoginTime = beforeLoginTime;
		this.currentLoginTime = currentLoginTime;
		this.persons = persons;
		this.injurycases = injurycases;
		this.clues = clues;
		this.pnotices = pnotices;
		this.cardid = cardid;
	}

	
	@Column(name = "beforeLoginTime")
	public String getBeforeLoginTime() {
		return beforeLoginTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userRole")
	public List<Clue> getClues() {
		return this.clues;
	}
	
	
	@Column(name = "currentLoginTime")
	public String getCurrentLoginTime() {
		return currentLoginTime;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userRole")
	public List<Injurycase> getInjurycases() {
		return this.injurycases;
	}

	@Column(name = "number")
	public String getNumber() {
		return this.number;
	}

	@Column(name = "password")
	public String getPassword() {
		return this.password;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userRole")
	public List<Person> getPersons() {
		return this.persons;
	}

	@Column(name = "photo")
	public String getPhoto() {
		return this.photo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userRole")
	public List<Pnotice> getPnotices() {
		return pnotices;
	}

	@Column(name = "realname")
	public String getRealname() {
		return this.realname;
	}

	@Column(name = "sex")
	public Integer getSex() {
		return sex;
	}

	@Column(name = "telphone")
	public String getTelphone() {
		return this.telphone;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unitid")
	public Unit getUnit() {
		return this.unit;
	}

	@Column(name = "userLimit")
	public Integer getUserLimit() {
		return userLimit;
	}

	@Column(name = "username")
	public String getUsername() {
		return this.username;
	}
	
	@Column(name = "cardid")
	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public void setBeforeLoginTime(String beforeLoginTime) {
		this.beforeLoginTime = beforeLoginTime;
	}

	public void setClues(List<Clue> clues) {
		this.clues = clues;
	}

	public void setCurrentLoginTime(String currentLoginTime) {
		this.currentLoginTime = currentLoginTime;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setInjurycases(List<Injurycase> injurycases) {
		this.injurycases = injurycases;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public void setPnotices(List<Pnotice> pnotices) {
		this.pnotices = pnotices;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public void setUserLimit(Integer userLimit) {
		this.userLimit = userLimit;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}