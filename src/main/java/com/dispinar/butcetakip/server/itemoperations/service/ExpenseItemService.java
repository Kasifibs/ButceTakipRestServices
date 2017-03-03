package com.dispinar.butcetakip.server.itemoperations.service;

import java.util.List;

import com.dispinar.butcetakip.server.itemoperations.entity.ExpenseItem;
import com.dispinar.butcetakip.server.itemoperations.query.ExpenseItemQueryParamsWrapper;

public interface ExpenseItemService {
	
	public void saveExpenseItem(ExpenseItem expenseItem, String username);
	
	public List<ExpenseItem> getAllExpenseItems(String username);
	
	public List<ExpenseItem> queryExpenseItems(String username, ExpenseItemQueryParamsWrapper queryParamsWrapper);
	
	public ExpenseItem getExpenseItem(Long id);
	
	public ExpenseItem updateExpenseItem(ExpenseItem expenseItem);
	
	public void deleteExpenseItem(Long id);
}
