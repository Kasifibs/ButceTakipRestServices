package com.dispinar.butcetakip.server.itemoperations.dao;

import java.util.List;

import com.dispinar.butcetakip.server.itemoperations.entity.ExpenseItem;

public interface ExpenseItemDao {
	
	public void save(ExpenseItem expenseItem);

	public ExpenseItem findById(Long id);
	
	public ExpenseItem findByName(String name);
	
	public List<ExpenseItem> findAll(Long userId);
	
	public ExpenseItem update(ExpenseItem expenseItem);
	
	public void delete(ExpenseItem expenseItem);
}