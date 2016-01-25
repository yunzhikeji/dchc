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
	private List<UserRole> userRoles = new ArrayList<UserRole>();//部门成员

	// Constructors

	/** default constructor */
	public Unit() {
	}

	/** full constructor */
	public Unit(String name, String number, Integer unitLimit, String superior,List<UserRole> userRoles) {
		this.name = name;
		this.number = number;
		this.unitLimit = unitLimit;
		this.superior = superior;
		this.userRoles = userRoles;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	@Column(name = "unitLimit")
	public Integer getUnitLimit() {
		return this.unitLimit;
	}

	@Column(name = "name", length = 30)
	public String getName() {
		return this.name;
	}

	@Column(name = "number", length = 30)
	public String getNumber() {
		return this.number;
	}

	@Column(name = "superior", length = 30)
	public String getSuperior() {
		return superior;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "unit")
	public List<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUnitLimit(Integer unitLimit) {
		this.unitLimit = unitLimit;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setSuperior(String superior) {
		this.superior = superior;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	
	

}