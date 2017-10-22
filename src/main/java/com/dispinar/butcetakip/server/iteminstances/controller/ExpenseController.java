package com.dispinar.butcetakip.server.iteminstances.controller;

import com.dispinar.butcetakip.server.common.query.QueryResultWrapper;
import com.dispinar.butcetakip.server.iteminstances.entity.Expense;
import com.dispinar.butcetakip.server.iteminstances.entity.Income;
import com.dispinar.butcetakip.server.iteminstances.query.ExpenseQueryParamsWrapper;
import com.dispinar.butcetakip.server.iteminstances.query.IncomeQueryParamsWrapper;
import com.dispinar.butcetakip.server.iteminstances.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Tolga on 16.04.2017.
 */
@RestController
@RequestMapping("/gider")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @RequestMapping(value="/kaydet", method= RequestMethod.POST)
    public void saveExpense(@RequestBody Expense expense){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        expenseService.saveExpense(expense, username);
    }

    @RequestMapping(value="/liste", method=RequestMethod.GET)
    public List<Expense> getAllExpenses(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        return expenseService.getAllExpenses(username);
    }

    @RequestMapping(value="/sorgula", method=RequestMethod.GET)
    public QueryResultWrapper<Expense> queryExpenses(ExpenseQueryParamsWrapper queryParamsWrapper){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        return expenseService.performQueryAndPrepareResultWrapper(username, queryParamsWrapper);
    }

    @RequestMapping(value="/gider/{id}", method=RequestMethod.GET)
    public Expense getExpense(@PathVariable("id") Long id){
        return expenseService.getExpense(id);
    }

    @RequestMapping(value="/guncelle", method=RequestMethod.PUT)
    public Expense updateExpense(@RequestBody Expense expense){
        return expenseService.updateExpense(expense);
    }

    @RequestMapping(value="/sil/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteExpense(@PathVariable("id") Long id){
        expenseService.deleteExpense(id);
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }
}
