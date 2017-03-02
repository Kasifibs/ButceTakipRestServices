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
		Root<IncomeItem> incItem = criteriaQuery.from(IncomeItem.class);
		Join<IncomeItem, User> user = incItem.join("user");
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		Predicate userCondition = criteriaBuilder.equal(user.get("id"), userId);
		predicates.add(userCondition);
		
		if(queryParams.getName()!=null){
			Predicate nameCondition = criteriaBuilder.like(incItem.<String>get("name"), "%"+queryParams.getName()+"%");
			predicates.add(nameCondition);
		}
		
		criteriaQuery.where(predicates.toArray(new Predicate[0]));
		return criteriaQuery;
	}

}
