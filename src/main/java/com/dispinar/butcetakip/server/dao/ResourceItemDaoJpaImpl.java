package com.dispinar.butcetakip.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.dispinar.butcetakip.server.entity.ResourceItem;

public class ResourceItemDaoJpaImpl implements ResourceItemDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	public void save(ResourceItem resourceItem) {
		entityManager.persist(resourceItem);
	}

	public ResourceItem findById(Long id) {
		return entityManager.find(ResourceItem.class, id);
	}

	public ResourceItem findByName(String name) {
		Query findByNameQuery = entityManager.createQuery("select ritem from ResourceItem ritem where ritem.name = ?");
		findByNameQuery.setParameter(1, name);
		
		return (ResourceItem) findByNameQuery.getSingleResult();
	}
	
	public List<ResourceItem> findAll() {
		Query findAllQuery = entityManager.createQuery("select ritem from ResourceItem ritem");
		
		return findAllQuery.getResultList();
	}

	public ResourceItem update(ResourceItem detachedItem) {
		return entityManager.merge(detachedItem);
	}

	public void delete(ResourceItem resourceItem) {
		entityManager.remove(resourceItem);
	}

}
