package com.dispinar.butcetakip.server.service;

import java.util.List;

import com.dispinar.butcetakip.server.entity.ExpenseItem;

public interface ExpenseItemService {
	
	public void saveExpenseItem(ExpenseItem expenseItem, String username);
	
	public List<ExpenseItem> getAllExpenseItems(String username);
	
	public ExpenseItem getExpenseItem(Long id);
	
	public ExpenseItem updateExpenseItem(ExpenseItem expenseItem);
	
	public void deleteExpenseItem(Long id);
}
