package com.dispinar.butcetakip.server.common.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.dispinar.butcetakip.server.common.entity.Period;

public class PeriodDaoImpl implements PeriodDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	public void save(Period period) {
		entityManager.persist(period);
	}

	public Period findById(Long id) {
		return entityManager.find(Period.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Period> findAll(Long userId) {
		Query findAllQuery = entityManager.createQuery("select p from Period p where p.user.id = :userId")
										  .setParameter("userId", userId);
		
		return findAllQuery.getResultList();
	}

	public Period update(Period detachedPeriod) {
		Period attachedPeriod = entityManager.find(Period.class, detachedPeriod.getId());
		attachedPeriod.copy(detachedPeriod);
		
		return attachedPeriod;
	}

	public void delete(Period period) {
		entityManager.remove(period);
	}

}
