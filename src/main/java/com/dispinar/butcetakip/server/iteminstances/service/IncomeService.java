package com.dispinar.butcetakip.server.iteminstances.service;

import com.dispinar.butcetakip.server.iteminstances.entity.Income;
import com.dispinar.butcetakip.server.iteminstances.query.IncomeQueryParamsWrapper;

import java.util.List;

/**
 * Created by Tolga on 13.04.2017.
 */
public interface IncomeService {

    void saveIncome(Income income, String username);

    List<Income> getAllIncomes(String username);

    List<Income> queryIncomes(String username, IncomeQueryParamsWrapper queryParamsWrapper);

    Income getIncome(Long id);

    Income updateIncome(Income income);

    void deleteIncome(Long id);
}
