package com.dispinar.butcetakip.server.iteminstances.dao;

import com.dispinar.butcetakip.server.iteminstances.entity.Income;
import com.dispinar.butcetakip.server.iteminstances.query.IncomeQueryParamsWrapper;
import com.dispinar.butcetakip.server.iteminstances.query.IncomeQueryWithParamsPreparator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Created by Tolga on 13.04.2017.
 */
public class IncomeDaoImpl implements  IncomeDao {

    @PersistenceContext
    private EntityManager entityManager;

    private IncomeQueryWithParamsPreparator queryWithParamsPreparator;

    public void save(Income income) {
        entityManager.persist(income);
    }

    public Income findById(Long id) {
        return entityManager.find(Income.class, id);
    }

    public List<Income> findAll(Long userId) {
        Query findAllQuery = entityManager.createQuery("select inc from Income inc where inc.user.id = :userId")
                .setParameter("userId", userId);

        return findAllQuery.getResultList();
    }

    public List<Income> queryIncomes(Long userId, IncomeQueryParamsWrapper queryParams) {
        CriteriaQuery<Income> criteriaQuery = queryWithParamsPreparator.prepareQueryUsingParams(userId, queryParams);
        TypedQuery<Income> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();
    }

    public Income update(Income detachedIncome) {
        Income attachedIncome = entityManager.find(Income.class, detachedIncome.getId());
        attachedIncome.copy(detachedIncome);

        return attachedIncome;
    }

    public void delete(Income income) {
        entityManager.remove(income);
    }


    public IncomeQueryWithParamsPreparator getQueryWithParamsPreparator() {
        return queryWithParamsPreparator;
    }

    public void setQueryWithParamsPreparator(IncomeQueryWithParamsPreparator queryWithParamsPreparator) {
        this.queryWithParamsPreparator = queryWithParamsPreparator;
    }
}
