package com.dispinar.butcetakip.server.iteminstances.query;

import com.dispinar.butcetakip.server.iteminstances.entity.Income;

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
 * Created by Tolga on 13.04.2017.
 */
public class IncomeQueryWithParamsPreparator {

    @PersistenceContext
    private EntityManager entityManager;

    public CriteriaQuery<Income> prepareQueryUsingParams(Long userId, IncomeQueryParamsWrapper queryParams){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Income> criteriaQuery = criteriaBuilder.createQuery(Income.class);
        Root<Income> inc = criteriaQuery.from(Income.class);

        List<Predicate> predicates = new ArrayList<Predicate>();
        Predicate userCondition = criteriaBuilder.equal(inc.get("user").get("id"), userId);
        predicates.add(userCondition);

        if(queryParams.getIncomeItemId()!=null){
            Predicate incomeItemCondition = criteriaBuilder.equal(inc.get("incomeItem").get("id"), queryParams.getIncomeItemId());
            predicates.add(incomeItemCondition);
        }

        if (queryParams.getPeriodId() != null){
            Predicate periodCondion = criteriaBuilder.equal(inc.get("period").get("id"), queryParams.getPeriodId());
            predicates.add(periodCondion);
        }

        if (queryParams.getMinAmount() != null){
            Predicate minAmountCondition = criteriaBuilder.greaterThanOrEqualTo(inc.<BigDecimal>get("amount"), queryParams.getMinAmount());
            predicates.add(minAmountCondition);
        }

        if (queryParams.getMaxAmount() != null){
            Predicate maxAmountCondition = criteriaBuilder.lessThanOrEqualTo(inc.<BigDecimal>get("amount"), queryParams.getMaxAmount());
            predicates.add(maxAmountCondition);
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        return criteriaQuery;
    }
}
