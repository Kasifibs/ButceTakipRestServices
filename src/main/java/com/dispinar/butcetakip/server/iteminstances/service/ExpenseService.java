package com.dispinar.butcetakip.server.iteminstances.service;

import com.dispinar.butcetakip.server.common.query.QueryResultWrapper;
import com.dispinar.butcetakip.server.iteminstances.entity.Expense;
import com.dispinar.butcetakip.server.iteminstances.entity.Income;
import com.dispinar.butcetakip.server.iteminstances.query.ExpenseQueryParamsWrapper;
import com.dispinar.butcetakip.server.iteminstances.query.IncomeQueryParamsWrapper;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Created by Tolga on 16.04.2017.
 */
public interface ExpenseService {

    void saveExpense(Expense expense, String username);

    List<Expense> getAllExpenses(String username);

    List<Expense> getAllExpensesByPeriod(String username, Long periodId);

    QueryResultWrapper<Expense> performQueryAndPrepareResultWrapper(String username, ExpenseQueryParamsWrapper queryParams);

    List<Expense> queryExpenses(String username, ExpenseQueryParamsWrapper queryParamsWrapper);

    Long queryCountOfExpenses(String username, ExpenseQueryParamsWrapper queryParams);

    Expense getExpense(Long id);

    Expense updateExpense(Expense expense);

    void deleteExpense(Long id);
}
