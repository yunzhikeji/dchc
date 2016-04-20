package com.yz.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "socialMan", schema = "dbo", catalog = "dchc")
public class SocialMan implements java.io.Serializable {
	private Integer id;
	private String number; //人员编号
	private String name; //姓名
	private String sex; //性别
	private String birthday; //出生日期
	private String qq; //QQ号
	private String wechat; //微信号
	private String idcard; //身份证号
	private String phonenumber; //电话号码
	private String registerAddress; //户籍地址
	private String registerArea; //户籍区域划分
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getRegisterAddress() {
		return registerAddress;
	}
	public void setRegisterAddress(String registerAddress) {
		this.registerAddress = registerAddress;
	}
	public String getRegisterArea() {
		return registerArea;
	}
	public void setRegisterArea(String registerArea) {
		this.registerArea = registerArea;
	}
	
}
