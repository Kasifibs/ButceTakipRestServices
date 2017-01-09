package com.dispinar.butcetakip.server.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
public class User {

	@Id
	@GeneratedValue
	@Column(name="USER_ID")
	private Long id;
	
	@Column(name="USER_NAME")
	private String username;
	
	@Column(name="ENABLED")
	private boolean enabled;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="USER_ID")
	private Set<UserRole> roles = new HashSet<UserRole>();
	
	public Long getId(){
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public boolean getEnabled(){
		return enabled;
	}
	
	public void setEnabled(boolean enabled){
		this.enabled = enabled;
	}

	public Set<UserRole> getRoles() {
		return roles;
	}

}
