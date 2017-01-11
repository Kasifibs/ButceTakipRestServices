package com.dispinar.butcetakip.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.dispinar.butcetakip.server.entity.ExpenseItem;

public class ExpenseItemDaoJpaImpl implements ExpenseItemDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	public void save(ExpenseItem expenseItem) {
		entityManager.persist(expenseItem);
	}

	public ExpenseItem findById(Long id) {
		return entityManager.find(ExpenseItem.class, id);
	}

	public ExpenseItem findByName(String name) {
		Query findByNameQuery = entityManager.createQuery("select eitem from ExpenseItem eitem where eitem.name = :itemName");
		findByNameQuery.setParameter("itemName", name);
		
		return (ExpenseItem) findByNameQuery.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<ExpenseItem> findAll(Long userId) {
		Query findAllQuery = entityManager.createQuery("select eitem from ExpenseItem eitem where eitem.user.id = :userId")
			     			.setParameter("userId", userId);

		return findAllQuery.getResultList();
	}

	public ExpenseItem update(ExpenseItem detachedExpenseItem) {
		ExpenseItem attachedExpenseItem = entityManager.find(ExpenseItem.class, detachedExpenseItem.getId());
		attachedExpenseItem.copy(detachedExpenseItem);
		
		return attachedExpenseItem;
	}

	public void delete(ExpenseItem expenseItem) {
		entityManager.remove(expenseItem);
	}

}
