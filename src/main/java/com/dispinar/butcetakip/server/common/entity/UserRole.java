package com.dispinar.butcetakip.server.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USER_ROLES")
public class UserRole {
	
	@Id
	@GeneratedValue
	@Column(name="USER_ROLE_ID")
	private Long id;
	
	@Column(name="AUTHORITY")
	private String roleName;
	
	public Long getId(){
		return id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
