package com.dispinar.butcetakip.server.common.dao;

import com.dispinar.butcetakip.server.common.entity.User;

public interface UserDao {

	public User findById(Long id);
	
	public User findByUserName(String username);
}
