package com.dispinar.butcetakip.server.iteminstances.service;

import com.dispinar.butcetakip.server.common.entity.User;
import com.dispinar.butcetakip.server.common.query.QueryResultWrapper;
import com.dispinar.butcetakip.server.common.service.UserService;
import com.dispinar.butcetakip.server.iteminstances.dao.ResourceDao;
import com.dispinar.butcetakip.server.iteminstances.entity.Resource;
import com.dispinar.butcetakip.server.iteminstances.query.ResourceQueryParamsWrapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class ResourceServiceImpl implements ResourceService{
	
	private ResourceDao resourceDao;
	private UserService userService;

	public void saveResource(Resource resource, String username) {
		User user = userService.getUserByUsername(username);
		resource.setUser(user);
		
		resourceDao.save(resource);
		
	}

	public List<Resource> getAllResources(String username) {
		User user = userService.getUserByUsername(username);
		
		return resourceDao.findAll(user.getId());
	}

	public List<Resource> getAllResourcesByPeriod(String username, Long periodId) {
		User user = userService.getUserByUsername(username);

		return resourceDao.findAllByPeriod(user.getId(), periodId);
	}

	public QueryResultWrapper<Resource> performQueryAndPrepareResultWrapper(String username, ResourceQueryParamsWrapper queryParams){
        List<Resource> resources = queryResources(username, queryParams);
        Long countOfResources = queryCountOfResources(username, queryParams);

        return new QueryResultWrapper<Resource>(countOfResources, resources);
    }

	public List<Resource> queryResources(String username, ResourceQueryParamsWrapper queryParamsWrapper) {
		User user = getUserService().getUserByUsername(username);

		return resourceDao.queryResources(user.getId(), queryParamsWrapper);
	}

	public Long queryCountOfResources(String username, ResourceQueryParamsWrapper queryParams){
        User user = getUserService().getUserByUsername(username);

        return resourceDao.queryCountOfResources(user.getId(), queryParams);
    }

	public Resource getResource(Long id) {
		return resourceDao.findById(id);
	}

	public Resource updateResource(Resource resource) {
		return resourceDao.update(resource);
	}

	public void deleteResource(Long id) {
		Resource resource = resourceDao.findById(id);
		resourceDao.delete(resource);
	}

	public ResourceDao getResourceDao() {
		return resourceDao;
	}

	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
