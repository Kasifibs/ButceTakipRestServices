package com.dispinar.butcetakip.server.common.entity;

import javax.persistence.*;

@Entity
@Table(name="USER_ROLES")
public class UserRole {
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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
