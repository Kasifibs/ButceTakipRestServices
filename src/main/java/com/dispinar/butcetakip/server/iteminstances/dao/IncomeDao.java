package com.dispinar.butcetakip.server.iteminstances.dao;

import com.dispinar.butcetakip.server.iteminstances.entity.Income;
import com.dispinar.butcetakip.server.iteminstances.entity.Resource;
import com.dispinar.butcetakip.server.iteminstances.query.IncomeQueryParamsWrapper;
import com.dispinar.butcetakip.server.iteminstances.query.ResourceQueryParamsWrapper;

import java.util.List;

/**
 * Created by Tolga on 13.04.2017.
 */

public interface IncomeDao {

    void save(Income income);

    Income findById(Long id);

    List<Income> findAll(Long userId);

    List<Income> findAllByPeriodId(Long userId, Long periodId);

    List<Income> queryIncomes(Long userId, IncomeQueryParamsWrapper queryParams);

    Income update(Income income);

    void delete(Income income);
}
