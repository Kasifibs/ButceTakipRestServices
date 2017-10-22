package com.dispinar.butcetakip.server.iteminstances.query;

import com.dispinar.butcetakip.server.iteminstances.entity.Resource;
import com.dispinar.butcetakip.server.iteminstances.query.ResourceQueryParamsWrapper;
import com.dispinar.butcetakip.server.itemoperations.entity.ResourceItem;

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
 * Created by Tolga on 02.04.2017.
 */
public class ResourceQueryWithParamsPreparator {

    @PersistenceContext
    private EntityManager entityManager;

    public CriteriaQuery<Resource> prepareQueryUsingParams(Long userId, ResourceQueryParamsWrapper queryParams){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Resource> criteriaQuery = criteriaBuilder.createQuery(Resource.class);
        Root<Resource> criteriaRoot = criteriaQuery.from(Resource.class);

        List<Predicate> predicates = preparePredicates(criteriaBuilder, criteriaRoot, userId, queryParams);
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        criteriaQuery.orderBy(criteriaBuilder.desc(criteriaRoot.get("id")));
        return criteriaQuery;
    }

    public CriteriaQuery<Long> prepareCountQueryUsingParams(Long userId,  ResourceQueryParamsWrapper queryParams){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Resource> countRoot = countQuery.from(Resource.class);

        countQuery.select(criteriaBuilder.count(countRoot));

        List<Predicate> predicates = preparePredicates(criteriaBuilder, countRoot, userId, queryParams);
        countQuery.where(predicates.toArray(new Predicate[0]));
        return countQuery.distinct(true);
    }

    private List<Predicate> preparePredicates(CriteriaBuilder criteriaBuilder, Root root, Long userId, ResourceQueryParamsWrapper queryParams){
        List<Predicate> predicates = new ArrayList<Predicate>();
        Predicate userCondition = criteriaBuilder.equal(root.get("user").get("id"), userId);
        predicates.add(userCondition);

        if(queryParams.getResourceItemId()!=null){
            Predicate resourceItemCondition = criteriaBuilder.equal(root.get("resourceItem").get("id"), queryParams.getResourceItemId());
            predicates.add(resourceItemCondition);
        }

        if (queryParams.getPeriodId() != null){
            Predicate periodCondion = criteriaBuilder.equal(root.get("period").get("id"), queryParams.getPeriodId());
            predicates.add(periodCondion);
        }

        if (queryParams.getMinAmount() != null){
            Predicate minAmountCondition = criteriaBuilder.greaterThanOrEqualTo(root.<BigDecimal>get("amount"), queryParams.getMinAmount());
            predicates.add(minAmountCondition);
        }

        if (queryParams.getMaxAmount() != null){
            Predicate maxAmountCondition = criteriaBuilder.lessThanOrEqualTo(root.<BigDecimal>get("amount"), queryParams.getMaxAmount());
            predicates.add(maxAmountCondition);
        }

        return predicates;
    }
}
