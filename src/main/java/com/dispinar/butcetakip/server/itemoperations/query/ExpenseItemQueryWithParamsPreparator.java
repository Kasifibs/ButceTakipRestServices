package com.dispinar.butcetakip.server.itemoperations.query;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.dispinar.butcetakip.server.common.entity.User;
import com.dispinar.butcetakip.server.itemoperations.entity.ExpenseItem;

public class ExpenseItemQueryWithParamsPreparator {

	@PersistenceContext
	private EntityManager entityManager;
	
	public CriteriaQuery<ExpenseItem> prepareQueryUsingParams(Long userId, ExpenseItemQueryParamsWrapper queryParams){
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ExpenseItem> criteriaQuery = criteriaBuilder.createQuery(ExpenseItem.class);
		Root<ExpenseItem> criteriaRoot = criteriaQuery.from(ExpenseItem.class);
		Join<ExpenseItem, User> userJoin = criteriaRoot.join("user");
		
		List<Predicate> predicates = preparePredicates(criteriaBuilder, criteriaRoot, userJoin, userId, queryParams);
		criteriaQuery.where(predicates.toArray(new Predicate[0]));
        criteriaQuery.orderBy(criteriaBuilder.desc(criteriaRoot.get("id")));
		return criteriaQuery;
	}

	public CriteriaQuery<Long> prepareCountQueryUsingParams(Long userId, ExpenseItemQueryParamsWrapper queryParams){
	    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	    CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
	    Root<ExpenseItem> countRoot = countQuery.from(ExpenseItem.class);
	    Join<ExpenseItem, User> userJoin = countRoot.join("user");

	    countQuery.select(criteriaBuilder.count(countRoot));

        List<Predicate> predicates = preparePredicates(criteriaBuilder, countRoot, userJoin, userId, queryParams);
        countQuery.where(predicates.toArray(new Predicate[0]));
        return countQuery.distinct(true);
    }

    private List<Predicate> preparePredicates(CriteriaBuilder criteriaBuilder, Root root, Join userJoin, Long userId, ExpenseItemQueryParamsWrapper queryParams){
        List<Predicate> predicates = new ArrayList<Predicate>();
        Predicate userCondition = criteriaBuilder.equal(userJoin.get("id"), userId);
        predicates.add(userCondition);

        if(queryParams.getName()!=null){
            Predicate nameCondition = criteriaBuilder.like(root.<String>get("name"), "%"+queryParams.getName()+"%");
            predicates.add(nameCondition);
        }

        return predicates;
    }
}
