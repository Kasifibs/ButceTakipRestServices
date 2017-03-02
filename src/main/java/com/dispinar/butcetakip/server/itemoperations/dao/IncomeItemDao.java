package com.dispinar.butcetakip.server.itemoperations.dao;

import java.util.List;

import com.dispinar.butcetakip.server.itemoperations.entity.IncomeItem;
import com.dispinar.butcetakip.server.itemoperations.query.IncomeItemQueryParamsWrapper;

public interface IncomeItemDao {
	
	public void save(IncomeItem incomeItem);
	
	public IncomeItem findById(Long id);
	
	public IncomeItem findByName(String name);
	
	public List<IncomeItem> findAll(Long userId);
	
	public List<IncomeItem> queryIncomeItems(Long userId, IncomeItemQueryParamsWrapper queryParams);
	
	public IncomeItem update(IncomeItem incomeItem);
	
	public void delete(IncomeItem incomeItem);

}
