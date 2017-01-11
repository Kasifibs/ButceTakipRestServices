package com.dispinar.butcetakip.server.common.service;

import javax.transaction.Transactional;

import com.dispinar.butcetakip.server.common.dao.UserDao;
import com.dispinar.butcetakip.server.common.entity.User;

@Transactional
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	
	public User getUser(Long id) {
		return userDao.findById(id);
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public User getUserByUsername(String username) {
		return userDao.findByUserName(username);
	}

}
