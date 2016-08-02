package com.yz.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Unit entity. 部门(单位)
 * 
 * @author lq
 */
@Entity
@Table(name = "unit", schema = "dbo", catalog = "dchc")
public class Unit implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer unitLimit;//部门权限
	private String name;//部门名称
	private String number;//部门编号
	private String superior;//所属上级单位
	private String pids;//包含人员
	private String cids;//包含线索
	private String inids;//包含案件
	private List<UserRole> userRoles = new ArrayList<UserRole>();//部门成员

	// Constructors

	/** default constructor */
	public Unit() {
	}

	/** full constructor */
	public Unit(String name, String number, Integer unitLimit, String superior,String pids,String cids,String inids,List<UserRole> userRoles) {
		this.name = name;
		this.number = number;
		this.unitLimit = unitLimit;
		this.superior = superior;
		this.pids = pids;
		this.cids = cids;
		this.inids = inids;
		this.userRoles = userRoles;
	}

	@Column(name = "cids")
	public String getCids() {
		return cids;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	@Column(name = "inids")
	public String getInids() {
		return inids;
	}

	@Column(name = "name", length = 30)
	public String getName() {
		return this.name;
	}

	@Column(name = "number", length = 30)
	public String getNumber() {
		return this.number;
	}
	
	
	@Column(name = "pids")
	public String getPids() {
		return pids;
	}

	@Column(name = "superior", length = 30)
	public String getSuperior() {
		return superior;
	}

	@Column(name = "unitLimit")
	public Integer getUnitLimit() {
		return this.unitLimit;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "unit")
	public List<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setCids(String cids) {
		this.cids = cids;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setInids(String inids) {
		this.inids = inids;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setPids(String pids) {
		this.pids = pids;
	}

	public void setSuperior(String superior) {
		this.superior = superior;
	}

	public void setUnitLimit(Integer unitLimit) {
		this.unitLimit = unitLimit;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	
	

}