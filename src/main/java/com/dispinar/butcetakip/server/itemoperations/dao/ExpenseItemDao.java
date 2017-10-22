package com.dispinar.butcetakip.server.itemoperations.dao;

import java.util.List;

import com.dispinar.butcetakip.server.itemoperations.entity.ExpenseItem;
import com.dispinar.butcetakip.server.itemoperations.query.ExpenseItemQueryParamsWrapper;

public interface ExpenseItemDao {
	
	void save(ExpenseItem expenseItem);

	ExpenseItem findById(Long id);
	
	ExpenseItem findByName(String name);
	
	List<ExpenseItem> findAll(Long userId);
	
	List<ExpenseItem> queryExpenseItems(Long userId, ExpenseItemQueryParamsWrapper queryParams);

    Long queryCountOfExpenseItems(Long userId, ExpenseItemQueryParamsWrapper queryParams);
	
	ExpenseItem update(ExpenseItem expenseItem);
	
	void delete(ExpenseItem expenseItem);
}
