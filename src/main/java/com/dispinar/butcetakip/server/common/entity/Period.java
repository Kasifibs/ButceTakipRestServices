package com.dispinar.butcetakip.server.common.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="PERIOD")
public class Period {

	@Id
	@GeneratedValue
	@Column(name="ID")
	private Long id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="BEGIN_DATE")
	private Date beginDate;
	
	@Column(name="END_DATE")
	private Date endDate;
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="USER_ID", referencedColumnName="user_id")
	private User user;
	
	public void copy(Period otherPeriod){
		this.name = otherPeriod.getName();
		this.beginDate = otherPeriod.getBeginDate();
		this.endDate = otherPeriod.getEndDate();
	}
	
	public Long getId(){
		return id;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
