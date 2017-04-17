package com.dispinar.butcetakip.server.iteminstances.dao;

import com.dispinar.butcetakip.server.iteminstances.entity.Expense;
import com.dispinar.butcetakip.server.iteminstances.entity.Income;
import com.dispinar.butcetakip.server.iteminstances.query.ExpenseQueryParamsWrapper;
import com.dispinar.butcetakip.server.iteminstances.query.ExpenseQueryWithParamsPreparator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Created by Tolga on 17.04.2017.
 */
public class ExpenseDaoImpl implements  ExpenseDao {

    @PersistenceContext
    private EntityManager entityManager;

    private ExpenseQueryWithParamsPreparator queryWithParamsPreparator;

    public void save(Expense expense) {
        entityManager.persist(expense);
    }

    public Expense findById(Long id) {
        return entityManager.find(Expense.class, id);
    }

    public List<Expense> findAll(Long userId) {
        Query findAllQuery = entityManager.createQuery("select exp from Expense exp where exp.user.id = :userId")
                .setParameter("userId", userId);

        return findAllQuery.getResultList();
    }

    public List<Expense> queryExpenses(Long userId, ExpenseQueryParamsWrapper queryParams) {
        CriteriaQuery<Expense> criteriaQuery = queryWithParamsPreparator.prepareQueryUsingParams(userId, queryParams);
        TypedQuery<Expense> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();
    }

    public Expense update(Expense detachedExpense) {
        Expense attacheExpense = entityManager.find(Expense.class, detachedExpense.getId());
        attacheExpense.copy(detachedExpense);

        return attacheExpense;
    }

    public void delete(Expense expense) {
        entityManager.remove(expense);
    }

    public ExpenseQueryWithParamsPreparator getQueryWithParamsPreparator() {
        return queryWithParamsPreparator;
    }

    public void setQueryWithParamsPreparator(ExpenseQueryWithParamsPreparator queryWithParamsPreparator) {
        this.queryWithParamsPreparator = queryWithParamsPreparator;
    }
}