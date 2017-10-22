package com.dispinar.butcetakip.server.itemoperations.service;

import java.util.List;

import com.dispinar.butcetakip.server.common.query.QueryResultWrapper;
import com.dispinar.butcetakip.server.itemoperations.entity.ResourceItem;
import com.dispinar.butcetakip.server.itemoperations.query.ResourceItemQueryParamsWrapper;

public interface ResourceItemService {

	void saveResourceItem(ResourceItem resourceItem, String username);
	
	List<ResourceItem> getAllResourceItems(String username);

    QueryResultWrapper<ResourceItem> performQueryAndPrepareResultWrapper(String username, ResourceItemQueryParamsWrapper queryParams);
	
	List<ResourceItem> queryResourceItems(String username, ResourceItemQueryParamsWrapper queryParamsWrapper);

    Long queryCountOfResourceItems(String username, ResourceItemQueryParamsWrapper queryParams);
	
	ResourceItem getResourceItem(Long id);
	
	ResourceItem updateResourceItem(ResourceItem resourceItem);
	
	void deleteResourceItem(Long id);
}
