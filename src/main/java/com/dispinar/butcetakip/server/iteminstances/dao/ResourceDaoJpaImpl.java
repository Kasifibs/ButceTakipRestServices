package com.dispinar.butcetakip.server.iteminstances.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.dispinar.butcetakip.server.iteminstances.entity.Resource;

public class ResourceDaoJpaImpl implements ResourceDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	public void save(Resource resource) {
		entityManager.persist(resource);
	}

	public Resource findById(Long id) {
		return  entityManager.find(Resource.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Resource> findAll(Long userId) {
		Query findAllQuery = entityManager.createQuery("select res from Resource res where res.user.id = :userId")
			     .setParameter("userId", userId);

		return findAllQuery.getResultList();
		
	}

	public Resource update(Resource detachedResource) {
		Resource attachedResource = entityManager.find(Resource.class, detachedResource.getId());
		attachedResource.copy(detachedResource);
		
		return attachedResource;
	}

	public void delete(Resource resource) {
		entityManager.remove(resource);
	}

}
