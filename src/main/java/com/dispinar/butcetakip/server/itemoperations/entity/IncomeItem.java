package com.dispinar.butcetakip.server.itemoperations.entity;

import javax.persistence.*;

import com.dispinar.butcetakip.server.common.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="INCOME_ITEM")
public class IncomeItem {

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(name="NAME")
	private String name;
	
	@JsonIgnore
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
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
