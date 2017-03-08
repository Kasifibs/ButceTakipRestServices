package com.dispinar.butcetakip.server.iteminstances.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.dispinar.butcetakip.server.common.entity.Period;
import com.dispinar.butcetakip.server.common.entity.User;
import com.dispinar.butcetakip.server.itemoperations.entity.ResourceItem;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="RESOURCE")
public class Resource {

	@Id
	@GeneratedValue
	@Column(name="ID")
	private Long id;
	
	@JsonIgnore
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="USER_ID", referencedColumnName="user_id")
	private User user;
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="RES_ITEM_ID", referencedColumnName="ID")
	private ResourceItem resourceItem;
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="PERIOD_ID", referencedColumnName="ID")
	private Period period;
	
	@Column ( name="AMOUNT", precision = 13, scale = 2 )
	private BigDecimal amount;
	
	public void copy(Resource detachedResource){
		this.resourceItem = detachedResource.getResourceItem();
		this.period = detachedResource.getPeriod();
		this.amount = detachedResource.getAmount();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ResourceItem getResourceItem() {
		return resourceItem;
	}

	public void setResourceItem(ResourceItem resourceItem) {
		this.resourceItem = resourceItem;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
}
