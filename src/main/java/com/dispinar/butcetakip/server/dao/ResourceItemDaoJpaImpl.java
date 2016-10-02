package com.dispinar.butcetakip.server.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.dispinar.butcetakip.server.entity.ResourceItem;

public class ResourceItemDaoJpaImpl implements ResourceItemDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	public void save(ResourceItem resourceItem) {
		entityManager.persist(resourceItem);
	}

}
