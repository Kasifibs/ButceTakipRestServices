package com.dispinar.butcetakip.server.itemoperations.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import com.dispinar.butcetakip.server.itemoperations.entity.IncomeItem;
import com.dispinar.butcetakip.server.itemoperations.query.IncomeItemQueryParamsWrapper;
import com.dispinar.butcetakip.server.itemoperations.query.IncomeItemQueryWithParamsPreparator;

public class IncomeItemDaoJpaImpl implements IncomeItemDao
{
	@PersistenceContext
	private EntityManager entityManager;
	
	private IncomeItemQueryWithParamsPreparator queryWithParamsPreparator;

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
	
	public List<IncomeItem> queryIncomeItems(Long userId, IncomeItemQueryParamsWrapper queryParams) {
		CriteriaQuery<IncomeItem> criteriaQuery = queryWithParamsPreparator.prepareQueryUsingParams(userId, queryParams);
		TypedQuery<IncomeItem> query = entityManager.createQuery(criteriaQuery);

        query.setFirstResult((queryParams.getPageNumber() - 1) * queryParams.getPageSize());
        query.setMaxResults(queryParams.getPageSize());
		
		return query.getResultList();
	}

	public Long queryCountOfIncomeItems(Long userId, IncomeItemQueryParamsWrapper queryParams){
        CriteriaQuery<Long> countQuery = queryWithParamsPreparator.prepareCountQueryUsingParams(userId, queryParams);
        return entityManager.createQuery(countQuery).getSingleResult();
    }

	public IncomeItem update(IncomeItem detachedIncomeItem) {
		IncomeItem attachedIncomeItem = entityManager.find(IncomeItem.class, detachedIncomeItem.getId());
		attachedIncomeItem.copy(detachedIncomeItem);
		
		return attachedIncomeItem;
	}

	public void delete(IncomeItem incomeItem) {
		entityManager.remove(incomeItem);
	}

	public IncomeItemQueryWithParamsPreparator getQueryWithParamsPreparator() {
		return queryWithParamsPreparator;
	}

	public void setQueryWithParamsPreparator(IncomeItemQueryWithParamsPreparator queryWithParamsPreparator) {
		this.queryWithParamsPreparator = queryWithParamsPreparator;
	}

}
