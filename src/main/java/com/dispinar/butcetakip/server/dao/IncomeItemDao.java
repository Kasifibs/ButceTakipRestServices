package com.dispinar.butcetakip.server.dao;

import java.util.List;

import com.dispinar.butcetakip.server.entity.IncomeItem;

public interface IncomeItemDao {
	
	public void save(IncomeItem incomeItem);
	
	public IncomeItem findById(Long id);
	
	public IncomeItem findByName(String name);
	
	public List<IncomeItem> findAll(Long userId);
	
	public IncomeItem update(IncomeItem incomeItem);
	
	public void delete(IncomeItem incomeItem);

}
