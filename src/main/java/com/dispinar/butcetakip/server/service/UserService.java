package com.dispinar.butcetakip.server.service;

import com.dispinar.butcetakip.server.entity.User;

public interface UserService {

	public User getUser(Long id);
	
	public User getUserByUsername(String username);
	
}
