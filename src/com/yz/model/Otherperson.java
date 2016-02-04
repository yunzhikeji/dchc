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
 * Otherperson entity.同案人，嫌疑人，关系人
 * 
 * @author lq
 */
@Entity
@Table(name = "otherperson", schema = "dbo", catalog = "dchc")
public class Otherperson implements java.io.Serializable {

	// Fields

	private Integer id;
	private Clue clue;//所属刑侦线索
	private Injurycase injurycase;//所属重伤案件
	private Person person;//所属人员
	private String name;//姓名
	private String idcard;//身份证
	private String birthday;//出生日期
	private String telphone;//电话
	private String qq;//QQ
	private String wechat;//微信
	private Integer sex;//性别
	private String registerAddress;//户籍地址
	private String workdUnit;//工作单位
	private String currentAddressArea;//现居住地址所属地区
	private String currentAddress;//现居住地址
	private String relation;//关系（在关系人中被使用）
	private String frontPhoto;//正面照片
	private String leftPhoto;//左侧照片
	private String rightPhoto;//右侧照片
	private Integer isArrest;//是否被抓获
	private Integer otype;//类型(1:同案人 2：嫌疑人 3：关系人)

	// Constructors

	/** default constructor */
	public Otherperson() {
	}

	/** full constructor */
	public Otherperson(Clue clue, Injurycase injurycase, Person person,
			String name, String idcard, String birthday, String telphone,
			String qq, String wechat, Integer sex, String registerAddress,
			String workdUnit, String currentAddressArea, String currentAddress,
			String relation, String frontPhoto, String leftPhoto,
			String rightPhoto, Integer isArrest, Integer otype) {
		this.clue = clue;
		this.injurycase = injurycase;
		this.person = person;
		this.name = name;
		this.idcard = idcard;
		this.birthday = birthday;
		this.telphone = telphone;
		this.qq = qq;
		this.wechat = wechat;
		this.sex = sex;
		this.registerAddress = registerAddress;
		this.workdUnit = workdUnit;
		this.currentAddressArea = currentAddressArea;
		this.currentAddress = currentAddress;
		this.relation = relation;
		this.frontPhoto = frontPhoto;
		this.leftPhoto = leftPhoto;
		this.rightPhoto = rightPhoto;
		this.isArrest = isArrest;
		this.otype = otype;
	}

	@Column(name = "birthday", length = 20)
	public String getBirthday() {
		return this.birthday;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clid")
	public Clue getClue() {
		return this.clue;
	}

	@Column(name = "currentAddress", length = 50)
	public String getCurrentAddress() {
		return this.currentAddress;
	}

	@Column(name = "currentAddressArea", length = 50)
	public String getCurrentAddressArea() {
		return this.currentAddressArea;
	}

	@Column(name = "frontPhoto", length = 30)
	public String getFrontPhoto() {
		return this.frontPhoto;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	@Column(name = "IDcard", length = 50)
	public String getIdcard() {
		return this.idcard;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "inid")
	public Injurycase getInjurycase() {
		return this.injurycase;
	}

	@Column(name = "isArrest")
	public Integer getIsArrest() {
		return this.isArrest;
	}

	@Column(name = "leftPhoto", length = 30)
	public String getLeftPhoto() {
		return this.leftPhoto;
	}

	@Column(name = "name", length = 30)
	public String getName() {
		return this.name;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "perid")
	public Person getPerson() {
		return this.person;
	}

	@Column(name = "qq", length = 15)
	public String getQq() {
		return this.qq;
	}

	@Column(name = "registerAddress", length = 60)
	public String getRegisterAddress() {
		return this.registerAddress;
	}

	@Column(name = "relation", length = 30)
	public String getRelation() {
		return this.relation;
	}

	@Column(name = "rightPhoto", length = 30)
	public String getRightPhoto() {
		return this.rightPhoto;
	}

	@Column(name = "sex")
	public Integer getSex() {
		return this.sex;
	}

	@Column(name = "telphone", length = 20)
	public String getTelphone() {
		return this.telphone;
	}

	@Column(name = "otype")
	public Integer getOtype() {
		return this.otype;
	}

	@Column(name = "wechat", length = 15)
	public String getWechat() {
		return this.wechat;
	}

	@Column(name = "workdUnit", length = 20)
	public String getWorkdUnit() {
		return this.workdUnit;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public void setClue(Clue clue) {
		this.clue = clue;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}

	public void setCurrentAddressArea(String currentAddressArea) {
		this.currentAddressArea = currentAddressArea;
	}

	public void setFrontPhoto(String frontPhoto) {
		this.frontPhoto = frontPhoto;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public void setInjurycase(Injurycase injurycase) {
		this.injurycase = injurycase;
	}

	public void setIsArrest(Integer isArrest) {
		this.isArrest = isArrest;
	}

	public void setLeftPhoto(String leftPhoto) {
		this.leftPhoto = leftPhoto;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public void setRegisterAddress(String registerAddress) {
		this.registerAddress = registerAddress;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public void setRightPhoto(String rightPhoto) {
		this.rightPhoto = rightPhoto;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public void setOtype(Integer otype) {
		this.otype = otype;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public void setWorkdUnit(String workdUnit) {
		this.workdUnit = workdUnit;
	}

}