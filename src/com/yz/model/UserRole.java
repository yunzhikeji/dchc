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
	private List<Person> persons = new ArrayList<Person>();//录入的人员信息
	private List<Injurycase> injurycases = new ArrayList<Injurycase>();//录入的重伤案件信息
	private List<Clue> clues = new ArrayList<Clue>();//录入的刑侦线索信息

	// Constructors

	/** default constructor */
	public UserRole() {
	}

	/** full constructor */
	public UserRole(Unit unit, String username, String password, String telphone,
			String number, String realname, String photo, List<Person> persons,
			List<Injurycase> injurycases, List<Clue> clues) {
		this.unit = unit;
		this.username = username;
		this.password = password;
		this.telphone = telphone;
		this.number = number;
		this.realname = realname;
		this.photo = photo;
		this.persons = persons;
		this.injurycases = injurycases;
		this.clues = clues;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userRole")
	public List<Clue> getClues() {
		return this.clues;
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

	@Column(name = "number", length = 30)
	public String getNumber() {
		return this.number;
	}

	@Column(name = "password", length = 30)
	public String getPassword() {
		return this.password;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userRole")
	public List<Person> getPersons() {
		return this.persons;
	}

	@Column(name = "photo", length = 30)
	public String getPhoto() {
		return this.photo;
	}

	@Column(name = "realname", length = 30)
	public String getRealname() {
		return this.realname;
	}

	@Column(name = "telphone", length = 30)
	public String getTelphone() {
		return this.telphone;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unitid")
	public Unit getUnit() {
		return this.unit;
	}

	@Column(name = "username", length = 30)
	public String getUsername() {
		return this.username;
	}

	public void setClues(List<Clue> clues) {
		this.clues = clues;
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

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}