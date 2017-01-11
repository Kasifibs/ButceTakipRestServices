package com.dispinar.butcetakip.server.common.service;

import com.dispinar.butcetakip.server.common.entity.User;

public interface UserService {

	public User getUser(Long id);
	
	public User getUserByUsername(String username);
	
}
