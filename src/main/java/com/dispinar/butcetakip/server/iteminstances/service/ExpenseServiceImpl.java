package com.dispinar.butcetakip.server.iteminstances.service;

import com.dispinar.butcetakip.server.common.entity.User;
import com.dispinar.butcetakip.server.common.service.UserService;
import com.dispinar.butcetakip.server.iteminstances.dao.ExpenseDao;
import com.dispinar.butcetakip.server.iteminstances.entity.Expense;
import com.dispinar.butcetakip.server.iteminstances.query.ExpenseQueryParamsWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Tolga on 17.04.2017.
 */

@Transactional
public class ExpenseServiceImpl implements  ExpenseService {

    private UserService userService;

    private ExpenseDao expenseDao;

    public void saveExpense(Expense expense, String username) {
        User user = userService.getUserByUsername(username);
        expense.setUser(user);

        expenseDao.save(expense);
    }

    public List<Expense> getAllExpenses(String username) {
        User user = userService.getUserByUsername(username);

        return expenseDao.findAll(user.getId());
    }

    public List<Expense> queryExpenses(String username, ExpenseQueryParamsWrapper queryParamsWrapper) {
        User user = userService.getUserByUsername(username);

        return expenseDao.queryExpenses(user.getId(), queryParamsWrapper);
    }

    public Expense getExpense(Long id) {
        return expenseDao.findById(id);
    }

    public Expense updateExpense(Expense expense) {
        return expenseDao.update(expense);
    }

    public void deleteExpense(Long id) {
        Expense expenseToDelete = expenseDao.findById(id);
        expenseDao.delete(expenseToDelete);
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public ExpenseDao getExpenseDao() {
        return expenseDao;
    }

    public void setExpenseDao(ExpenseDao expenseDao) {
        this.expenseDao = expenseDao;
    }
}
