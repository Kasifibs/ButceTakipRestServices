package com.dispinar.butcetakip.server.iteminstances.dao;

import com.dispinar.butcetakip.server.iteminstances.entity.Expense;
import com.dispinar.butcetakip.server.iteminstances.query.ExpenseQueryParamsWrapper;

import java.util.List;

/**
 * Created by Tolga on 17.04.2017.
 */
public interface ExpenseDao {

    void save(Expense expense);

    Expense findById(Long id);

    List<Expense> findAll(Long userId);

    List<Expense> findAllByPeriodId(Long userId, Long periodId);

    List<Expense> queryExpenses(Long userId, ExpenseQueryParamsWrapper queryParams);

    Expense update(Expense expense);

    void delete(Expense expense);
}
