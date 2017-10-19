package com.dispinar.butcetakip.server.common.entity;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="PERIOD")
public class Period {

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="BEGIN_DATE")
	private Date beginDate;
	
	@Column(name="END_DATE")
	private Date endDate;

	@Column ( name="BEGIN_AMOUNT", precision = 13, scale = 2 )
	private BigDecimal beginAmount;
	
	@JsonIgnore
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="USER_ID", referencedColumnName="user_id")
	private User user;
	
	public void copy(Period otherPeriod){
		this.name = otherPeriod.getName();
		this.beginDate = otherPeriod.getBeginDate();
		this.endDate = otherPeriod.getEndDate();
		this.beginAmount = otherPeriod.getBeginAmount();
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

	public BigDecimal getBeginAmount() {
		return beginAmount;
	}

	public void setBeginAmount(BigDecimal beginAmount) {
		this.beginAmount = beginAmount;
	}
}
