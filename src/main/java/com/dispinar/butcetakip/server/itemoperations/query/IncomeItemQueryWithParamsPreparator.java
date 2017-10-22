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
import com.dispinar.butcetakip.server.itemoperations.entity.IncomeItem;

public class IncomeItemQueryWithParamsPreparator {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public CriteriaQuery<IncomeItem> prepareQueryUsingParams(Long userId, IncomeItemQueryParamsWrapper queryParams){
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<IncomeItem> criteriaQuery = criteriaBuilder.createQuery(IncomeItem.class);
		Root<IncomeItem> criteriaRoot = criteriaQuery.from(IncomeItem.class);
		Join<IncomeItem, User> userJoin = criteriaRoot.join("user");
		
		List<Predicate> predicates = preparePredicates(criteriaBuilder, criteriaRoot, userJoin, userId, queryParams);
		criteriaQuery.where(predicates.toArray(new Predicate[0]));
        criteriaQuery.orderBy(criteriaBuilder.desc(criteriaRoot.get("id")));
		return criteriaQuery;
	}

	public CriteriaQuery<Long> prepareCountQueryUsingParams(Long userId, IncomeItemQueryParamsWrapper queryParams){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<IncomeItem> countRoot = countQuery.from(IncomeItem.class);
        Join<IncomeItem, User> userJoin = countRoot.join("user");

        countQuery.select(criteriaBuilder.count(countRoot));

        List<Predicate> predicates = preparePredicates(criteriaBuilder, countRoot, userJoin, userId, queryParams);
        countQuery.where(predicates.toArray(new Predicate[0]));
        return countQuery.distinct(true);
    }

    private List<Predicate> preparePredicates(CriteriaBuilder criteriaBuilder, Root root, Join userJoin, Long userId, IncomeItemQueryParamsWrapper queryParams){
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
