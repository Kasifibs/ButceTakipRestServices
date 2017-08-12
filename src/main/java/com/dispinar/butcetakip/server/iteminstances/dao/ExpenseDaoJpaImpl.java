package com.dispinar.butcetakip.server.iteminstances.dao;

import com.dispinar.butcetakip.server.iteminstances.entity.Expense;
import com.dispinar.butcetakip.server.iteminstances.entity.Income;
import com.dispinar.butcetakip.server.iteminstances.query.ExpenseQueryParamsWrapper;
import com.dispinar.butcetakip.server.iteminstances.query.ExpenseQueryWithParamsPreparator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Tolga on 17.04.2017.
 */
public class ExpenseDaoJpaImpl implements  ExpenseDao {

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

    public List<Expense> findAllByPeriodId(Long userId, Long periodId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Expense> criteriaQuery = criteriaBuilder.createQuery(Expense.class);
        Root<Expense> exp = criteriaQuery.from(Expense.class);

        List<Predicate> predicates = new ArrayList<Predicate>();
        Predicate userCondition = criteriaBuilder.equal(exp.get("user").get("id"), userId);
        Predicate periodCondition = criteriaBuilder.equal(exp.get("period").get("id"), periodId);
        predicates.addAll(Arrays.asList(userCondition, periodCondition));
        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Expense> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
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