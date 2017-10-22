package com.dispinar.butcetakip.server.common.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import com.dispinar.butcetakip.server.common.entity.Period;
import com.dispinar.butcetakip.server.common.query.PeriodQueryParamsWrapper;
import com.dispinar.butcetakip.server.common.query.PeriodQueryWithParamsPreparator;

public class PeriodDaoImpl implements PeriodDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private PeriodQueryWithParamsPreparator queryWithParamsPreparator;

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
	
	public List<Period> queryPeriods(Long userId, PeriodQueryParamsWrapper queryParams) {
		CriteriaQuery<Period> criteriaQuery = queryWithParamsPreparator.prepareQueryUsingParams(userId, queryParams);
		TypedQuery<Period> query = entityManager.createQuery(criteriaQuery);

        query.setFirstResult((queryParams.getPageNumber() - 1) * queryParams.getPageSize());
        query.setMaxResults(queryParams.getPageSize());
		
		return query.getResultList();
	}

	public Long queryCountOfPeriods(Long userId, PeriodQueryParamsWrapper queryParams){
        CriteriaQuery<Long> countQuery = queryWithParamsPreparator.prepareCountQueryUsingParams(userId, queryParams);
        return entityManager.createQuery(countQuery).getSingleResult();
    }

	public Period update(Period detachedPeriod) {
		Period attachedPeriod = entityManager.find(Period.class, detachedPeriod.getId());
		attachedPeriod.copy(detachedPeriod);
		
		return attachedPeriod;
	}

	public void delete(Period period) {
		entityManager.remove(period);
	}

	public PeriodQueryWithParamsPreparator getQueryWithParamsPreparator() {
		return queryWithParamsPreparator;
	}

	public void setQueryWithParamsPreparator(PeriodQueryWithParamsPreparator queryWithParamsPreparator) {
		this.queryWithParamsPreparator = queryWithParamsPreparator;
	}

}
