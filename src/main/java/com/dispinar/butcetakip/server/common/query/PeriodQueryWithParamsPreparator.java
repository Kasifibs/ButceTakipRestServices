package com.dispinar.butcetakip.server.common.query;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.dispinar.butcetakip.server.common.entity.Period;
import com.dispinar.butcetakip.server.common.entity.User;

public class PeriodQueryWithParamsPreparator {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	public CriteriaQuery<Period> prepareQueryUsingParams(Long userId, PeriodQueryParamsWrapper queryParams){
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Period> criteriaQuery = criteriaBuilder.createQuery(Period.class);
		Root<Period> criteriaRoot = criteriaQuery.from(Period.class);
		Join<Period, User> userJoin = criteriaRoot.join("user");
		
		List<Predicate> predicates = preparePredicates(criteriaBuilder, criteriaRoot, userJoin, userId, queryParams);
		criteriaQuery.where(predicates.toArray(new Predicate[0]));
        criteriaQuery.orderBy(criteriaBuilder.desc(criteriaRoot.get("id")));
		return criteriaQuery;
	}

	public CriteriaQuery<Long> prepareCountQueryUsingParams(Long userId, PeriodQueryParamsWrapper queryParams){
	    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	    CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
	    Root<Period> countRoot = countQuery.from(Period.class);
	    Join<Period, User> userJoin = countRoot.join("user");

	    countQuery.select(criteriaBuilder.count(countRoot));

        List<Predicate> predicates = preparePredicates(criteriaBuilder, countRoot, userJoin, userId, queryParams);
        countQuery.where(predicates.toArray(new Predicate[0]));
        return countQuery.distinct(true);
    }

    private List<Predicate> preparePredicates(CriteriaBuilder criteriaBuilder, Root root, Join userJoin, Long userId, PeriodQueryParamsWrapper queryParams){
        List<Predicate> predicates = new ArrayList<Predicate>();
        Predicate userCondition = criteriaBuilder.equal(userJoin.get("id"), userId);
        predicates.add(userCondition);

        if(queryParams.getName()!=null){
            Predicate nameCondition = criteriaBuilder.like(root.<String>get("name"), "%"+queryParams.getName()+"%");
            predicates.add(nameCondition);
        }
        if(queryParams.getMinBeginDate()!=null){
            Predicate minBeginDateCondition = criteriaBuilder.greaterThanOrEqualTo(root.<Date>get("beginDate"), queryParams.getMinBeginDate());
            predicates.add(minBeginDateCondition);
        }
        if(queryParams.getMaxBeginDate()!=null){
            Predicate maxBeginDateCondition = criteriaBuilder.lessThanOrEqualTo(root.<Date>get("beginDate"), queryParams.getMaxBeginDate());
            predicates.add(maxBeginDateCondition);
        }
        if(queryParams.getMinEndDate()!=null){
            Predicate minEndDateCondition = criteriaBuilder.greaterThanOrEqualTo(root.<Date>get("endDate"), queryParams.getMinEndDate());
            predicates.add(minEndDateCondition);
        }
        if(queryParams.getMaxEndDate()!=null){
            Predicate maxEndDateCondition = criteriaBuilder.lessThanOrEqualTo(root.<Date>get("endDate"), queryParams.getMaxEndDate());
            predicates.add(maxEndDateCondition);
        }

        return predicates;
    }

}
