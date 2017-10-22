package com.dispinar.butcetakip.server.iteminstances.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.dispinar.butcetakip.server.iteminstances.entity.Resource;
import com.dispinar.butcetakip.server.iteminstances.query.ResourceQueryParamsWrapper;
import com.dispinar.butcetakip.server.iteminstances.query.ResourceQueryWithParamsPreparator;


public class ResourceDaoJpaImpl implements ResourceDao{

	@PersistenceContext
	private EntityManager entityManager;

	private ResourceQueryWithParamsPreparator queryWithParamsPreparator;
	
	public void save(Resource resource) {
		entityManager.persist(resource);
	}

	public Resource findById(Long id) {
		return  entityManager.find(Resource.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Resource> findAll(Long userId) {
		Query findAllQuery = entityManager.createQuery("select res from Resource res where res.user.id = :userId")
			     .setParameter("userId", userId);

		return findAllQuery.getResultList();
		
	}

	public List<Resource> findAllByPeriod(Long userId, Long periodId) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Resource> criteriaQuery = criteriaBuilder.createQuery(Resource.class);
		Root<Resource> res = criteriaQuery.from(Resource.class);

        List<Predicate> predicates = new ArrayList<Predicate>();
        Predicate userCondition = criteriaBuilder.equal(res.get("user").get("id"), userId);
        Predicate periodCondition = criteriaBuilder.equal(res.get("period").get("id"), periodId);
        predicates.addAll(Arrays.asList(userCondition, periodCondition));
        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Resource> query = entityManager.createQuery(criteriaQuery);
		return query.getResultList();
	}

	public List<Resource> queryResources(Long userId, ResourceQueryParamsWrapper queryParams) {
		CriteriaQuery<Resource> resourceQuery = queryWithParamsPreparator.prepareQueryUsingParams(userId, queryParams);
		TypedQuery<Resource>  query = entityManager.createQuery(resourceQuery);

        query.setFirstResult((queryParams.getPageNumber() - 1) * queryParams.getPageSize());
        query.setMaxResults(queryParams.getPageSize());

		return query.getResultList();
	}

	public Long queryCountOfResources(Long userId, ResourceQueryParamsWrapper queryParams){
	    CriteriaQuery<Long> countQuery = queryWithParamsPreparator.prepareCountQueryUsingParams(userId, queryParams);
	    return entityManager.createQuery(countQuery).getSingleResult();
    }

	public Resource update(Resource detachedResource) {
		Resource attachedResource = entityManager.find(Resource.class, detachedResource.getId());
		attachedResource.copy(detachedResource);
		
		return attachedResource;
	}

	public void delete(Resource resource) {
		entityManager.remove(resource);
	}

	public void setQueryWithParamsPreparator(ResourceQueryWithParamsPreparator queryWithParamsPreparator) {
		this.queryWithParamsPreparator = queryWithParamsPreparator;
	}
}
