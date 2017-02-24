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
import com.dispinar.butcetakip.server.itemoperations.entity.ResourceItem;

public class ResourceItemQueryWithParamsPreparator {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public CriteriaQuery<ResourceItem> prepareQueryUsingParams(Long userId, ResourceItemQueryParamsWrapper queryParams){
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ResourceItem> criteriaQuery = criteriaBuilder.createQuery(ResourceItem.class);
		Root<ResourceItem> resItem = criteriaQuery.from(ResourceItem.class);
		Join<ResourceItem, User> user = resItem.join("user");
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		Predicate userCondition = criteriaBuilder.equal(user.get("id"), userId);
		predicates.add(userCondition);
		
		if(queryParams.getName()!=null){
			Predicate nameCondition = criteriaBuilder.like(resItem.<String>get("name"), "%"+queryParams.getName()+"%");
			predicates.add(nameCondition);
		}
		
		criteriaQuery.where(predicates.toArray(new Predicate[0]));
		return criteriaQuery;
	}

}
