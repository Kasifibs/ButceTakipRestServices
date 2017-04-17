package com.dispinar.butcetakip.server.iteminstances.query;

import com.dispinar.butcetakip.server.iteminstances.entity.Expense;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tolga on 17.04.2017.
 */
public class ExpenseQueryWithParamsPreparator {
    @PersistenceContext
    private EntityManager entityManager;

    public CriteriaQuery<Expense> prepareQueryUsingParams(Long userId, ExpenseQueryParamsWrapper queryParams) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Expense> criteriaQuery = criteriaBuilder.createQuery(Expense.class);
        Root<Expense> exp = criteriaQuery.from(Expense.class);

        List<Predicate> predicates = new ArrayList<Predicate>();
        Predicate userCondition = criteriaBuilder.equal(exp.get("user").get("id"), userId);
        predicates.add(userCondition);

        if (queryParams.getExpenseItemId() != null) {
            Predicate expenseItemCondition = criteriaBuilder.equal(exp.get("incomeItem").get("id"), queryParams.getExpenseItemId());
            predicates.add(expenseItemCondition);
        }

        if (queryParams.getPeriodId() != null) {
            Predicate periodCondion = criteriaBuilder.equal(exp.get("period").get("id"), queryParams.getPeriodId());
            predicates.add(periodCondion);
        }

        if (queryParams.getMinAmount() != null) {
            Predicate minAmountCondition = criteriaBuilder.greaterThanOrEqualTo(exp.<BigDecimal>get("amount"), queryParams.getMinAmount());
            predicates.add(minAmountCondition);
        }

        if (queryParams.getMaxAmount() != null) {
            Predicate maxAmountCondition = criteriaBuilder.lessThanOrEqualTo(exp.<BigDecimal>get("amount"), queryParams.getMaxAmount());
            predicates.add(maxAmountCondition);
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        return criteriaQuery;
    }
}
