package com.dispinar.butcetakip.server.dao;

import com.dispinar.butcetakip.server.entity.User;

public interface UserDao {

	public User findById(Long id);
	
	public User findByUserName(String username);
}
