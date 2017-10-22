package com.dispinar.butcetakip.server.itemoperations.service;

import java.util.List;

import com.dispinar.butcetakip.server.common.query.QueryResultWrapper;
import com.dispinar.butcetakip.server.itemoperations.entity.ExpenseItem;
import com.dispinar.butcetakip.server.itemoperations.query.ExpenseItemQueryParamsWrapper;

public interface ExpenseItemService {
	
	void saveExpenseItem(ExpenseItem expenseItem, String username);
	
	List<ExpenseItem> getAllExpenseItems(String username);

    QueryResultWrapper<ExpenseItem> performQueryAndPrepareResultWrapper(String username, ExpenseItemQueryParamsWrapper queryParams);
	
	List<ExpenseItem> queryExpenseItems(String username, ExpenseItemQueryParamsWrapper queryParamsWrapper);

    Long queryCountOfExpenseItems(String username, ExpenseItemQueryParamsWrapper queryParams);
	
	ExpenseItem getExpenseItem(Long id);
	
	ExpenseItem updateExpenseItem(ExpenseItem expenseItem);
	
	void deleteExpenseItem(Long id);
}
