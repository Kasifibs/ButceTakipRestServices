package com.dispinar.butcetakip.server.iteminstances.service;

import com.dispinar.butcetakip.server.common.entity.User;
import com.dispinar.butcetakip.server.common.service.UserService;
import com.dispinar.butcetakip.server.iteminstances.dao.IncomeDao;
import com.dispinar.butcetakip.server.iteminstances.entity.Income;
import com.dispinar.butcetakip.server.iteminstances.query.IncomeQueryParamsWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Tolga on 13.04.2017.
 */

@Transactional
public class IncomeServiceImpl implements IncomeService {

    private UserService userService;

    private IncomeDao incomeDao;

    public void saveIncome(Income income, String username) {
        User user = userService.getUserByUsername(username);
        income.setUser(user);

        incomeDao.save(income);
    }

    public List<Income> getAllIncomes(String username) {
        User user = userService.getUserByUsername(username);

        return incomeDao.findAll(user.getId());
    }

    public List<Income> queryIncomes(String username, IncomeQueryParamsWrapper queryParamsWrapper) {
        User user = userService.getUserByUsername(username);

        return incomeDao.queryIncomes(user.getId(), queryParamsWrapper);
    }

    public Income getIncome(Long id) {
        return incomeDao.findById(id);
    }

    public Income updateIncome(Income income) {
        return incomeDao.update(income);
    }

    public void deleteIncome(Long id) {
        Income income = incomeDao.findById(id);
        incomeDao.delete(income);
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public IncomeDao getIncomeDao() {
        return incomeDao;
    }

    public void setIncomeDao(IncomeDao incomeDao) {
        this.incomeDao = incomeDao;
    }
}
