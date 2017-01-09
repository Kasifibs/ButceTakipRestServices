package com.dispinar.butcetakip.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="INCOME_ITEM")
public class IncomeItem {

	@Id
	@GeneratedValue
	@Column(name="ID")
	private Long id;
	
	@Column(name="NAME")
	private String name;
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="USER_ID", referencedColumnName="user_id")
	private User user;
	
	public Long getId(){
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void copy(IncomeItem detachedIncomeItem){
		this.name = detachedIncomeItem.getName();
	}
}
