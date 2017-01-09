package com.dispinar.butcetakip.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.dispinar.butcetakip.server.entity.IncomeItem;

public class IncomeItemDaoJpaImpl implements IncomeItemDao
{
	@PersistenceContext
	private EntityManager entityManager;

	public void save(IncomeItem incomeItem) {
		entityManager.persist(incomeItem);
	}

	public IncomeItem findById(Long id) {
		return entityManager.find(IncomeItem.class, id);
	}

	public IncomeItem findByName(String name) {
		Query findByNameQuery = entityManager.createQuery("select initem from IncomeItem initem where initem.name = :itemName");
		findByNameQuery.setParameter("itemName", name);
		
		return (IncomeItem) findByNameQuery.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<IncomeItem> findAll(Long userId) {
		Query findAllQuery = entityManager.createQuery("select initem from IncomeItem initem where initem.user.id = :userId");
		findAllQuery.setParameter("userId", userId);
		
		return findAllQuery.getResultList();
	}

	public IncomeItem update(IncomeItem detachedIncomeItem) {
		IncomeItem attachedIncomeItem = entityManager.find(IncomeItem.class, detachedIncomeItem.getId());
		attachedIncomeItem.copy(detachedIncomeItem);
		
		return attachedIncomeItem;
	}

	public void delete(IncomeItem incomeItem) {
		entityManager.remove(incomeItem);
	}

}
