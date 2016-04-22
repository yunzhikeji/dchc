package com.yz.model;

import java.io.File;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "socialMan", schema = "dbo", catalog = "dchc")
public class SocialMan implements java.io.Serializable {
	private Integer id;
	private Person person;


	//使用jxl进行报表导入的时候使用
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "socialMan")
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}

}
