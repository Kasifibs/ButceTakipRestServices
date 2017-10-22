package com.dispinar.butcetakip.server.itemoperations.service;

import java.util.List;

import com.dispinar.butcetakip.server.common.query.QueryResultWrapper;
import com.dispinar.butcetakip.server.itemoperations.entity.IncomeItem;
import com.dispinar.butcetakip.server.itemoperations.query.IncomeItemQueryParamsWrapper;

public interface IncomeItemService {

	void saveIncomeItem(IncomeItem incomeItem, String username);
	
	List<IncomeItem> getAllIncomeItems(String username);

    QueryResultWrapper<IncomeItem> performQueryAndPrepareResultWrapper(String username, IncomeItemQueryParamsWrapper queryParams);
	
	List<IncomeItem> queryIncomeItems(String username, IncomeItemQueryParamsWrapper queryParamsWrapper);

    Long queryCountOfIncomeItems(String username, IncomeItemQueryParamsWrapper queryParams);
	
	IncomeItem getIncomeItem(Long id);
	
	IncomeItem updateIncomeItem(IncomeItem incomeItem);
	
	void deleteIncomeItem(Long id);
}
