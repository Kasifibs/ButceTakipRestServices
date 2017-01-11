package com.dispinar.butcetakip.server.common.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.dispinar.butcetakip.server.common.entity.User;

public class UserDaoJpaImpl implements UserDao{
	
	@PersistenceContext
	private EntityManager entityManager;

	public User findById(Long id) {
		return entityManager.find(User.class, id);
	}

	public User findByUserName(String username) {
		Query findByNameQuery = entityManager.createQuery("select u from User u where u.username = ?");
		findByNameQuery.setParameter(1, username);
		
		return (User) findByNameQuery.getSingleResult();
	}

}
