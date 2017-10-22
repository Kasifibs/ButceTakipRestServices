package com.dispinar.butcetakip.server.itemoperations.dao;

import java.util.List;

import com.dispinar.butcetakip.server.itemoperations.entity.IncomeItem;
import com.dispinar.butcetakip.server.itemoperations.query.IncomeItemQueryParamsWrapper;

public interface IncomeItemDao {
	
	void save(IncomeItem incomeItem);
	
	IncomeItem findById(Long id);
	
	IncomeItem findByName(String name);
	
	List<IncomeItem> findAll(Long userId);
	
	List<IncomeItem> queryIncomeItems(Long userId, IncomeItemQueryParamsWrapper queryParams);

    Long queryCountOfIncomeItems(Long userId, IncomeItemQueryParamsWrapper queryParams);
	
	IncomeItem update(IncomeItem incomeItem);
	
	void delete(IncomeItem incomeItem);

}
