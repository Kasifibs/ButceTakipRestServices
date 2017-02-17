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
		Root<Period> period = criteriaQuery.from(Period.class);
		Join<Period, User> user = period.join("user");
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		Predicate userCondition = criteriaBuilder.equal(user.get("id"), userId);
		predicates.add(userCondition);
		
		if(queryParams.getName()!=null){
			Predicate nameCondition = criteriaBuilder.like(period.<String>get("name"), "%"+queryParams.getName()+"%");
			predicates.add(nameCondition);
		}
		if(queryParams.getMinBeginDate()!=null){
			Predicate minBeginDateCondition = criteriaBuilder.greaterThanOrEqualTo(period.<Date>get("beginDate"), queryParams.getMinBeginDate());
			predicates.add(minBeginDateCondition);
		}
		if(queryParams.getMaxBeginDate()!=null){
			Predicate maxBeginDateCondition = criteriaBuilder.lessThanOrEqualTo(period.<Date>get("beginDate"), queryParams.getMaxBeginDate());
			predicates.add(maxBeginDateCondition);
		}
		if(queryParams.getMinEndDate()!=null){
			Predicate minEndDateCondition = criteriaBuilder.greaterThanOrEqualTo(period.<Date>get("endDate"), queryParams.getMinEndDate());
			predicates.add(minEndDateCondition);
		}
		if(queryParams.getMaxEndDate()!=null){
			Predicate maxEndDateCondition = criteriaBuilder.lessThanOrEqualTo(period.<Date>get("endDate"), queryParams.getMaxEndDate());
			predicates.add(maxEndDateCondition);
		}
		
		criteriaQuery.where(predicates.toArray(new Predicate[0]));
		return criteriaQuery;
	}

}
