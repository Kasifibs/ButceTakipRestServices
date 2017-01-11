package com.dispinar.butcetakip.server.itemoperations.service;

import java.util.List;

import com.dispinar.butcetakip.server.itemoperations.entity.IncomeItem;

public interface IncomeItemService {

	public void saveIncomeItem(IncomeItem incomeItem, String username);
	
	public List<IncomeItem> getAllIncomeItems(String username);
	
	public IncomeItem getIncomeItem(Long id);
	
	public IncomeItem updateIncomeItem(IncomeItem incomeItem);
	
	public void deleteIncomeItem(Long id);
}
