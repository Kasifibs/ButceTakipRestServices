package com.dispinar.butcetakip.server.itemoperations.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import com.dispinar.butcetakip.server.itemoperations.entity.ExpenseItem;
import com.dispinar.butcetakip.server.itemoperations.query.ExpenseItemQueryParamsWrapper;
import com.dispinar.butcetakip.server.itemoperations.query.ExpenseItemQueryWithParamsPreparator;

public class ExpenseItemDaoJpaImpl implements ExpenseItemDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	private ExpenseItemQueryWithParamsPreparator queryWithParamsPreparator;
	
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
	
	public List<ExpenseItem> queryExpenseItems(Long userId, ExpenseItemQueryParamsWrapper queryParams) {
		CriteriaQuery<ExpenseItem> criteriaQuery = queryWithParamsPreparator.prepareQueryUsingParams(userId, queryParams);
		TypedQuery<ExpenseItem> query = entityManager.createQuery(criteriaQuery);
		
		return query.getResultList();
	}

	public ExpenseItem update(ExpenseItem detachedExpenseItem) {
		ExpenseItem attachedExpenseItem = entityManager.find(ExpenseItem.class, detachedExpenseItem.getId());
		attachedExpenseItem.copy(detachedExpenseItem);
		
		return attachedExpenseItem;
	}

	public void delete(ExpenseItem expenseItem) {
		entityManager.remove(expenseItem);
	}

	public ExpenseItemQueryWithParamsPreparator getQueryWithParamsPreparator() {
		return queryWithParamsPreparator;
	}

	public void setQueryWithParamsPreparator(ExpenseItemQueryWithParamsPreparator queryWithParamsPreparator) {
		this.queryWithParamsPreparator = queryWithParamsPreparator;
	}


}
