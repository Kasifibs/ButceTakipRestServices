package com.dispinar.butcetakip.server.iteminstances.controller;

import com.dispinar.butcetakip.server.iteminstances.entity.Income;
import com.dispinar.butcetakip.server.iteminstances.query.IncomeQueryParamsWrapper;
import com.dispinar.butcetakip.server.iteminstances.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Tolga on 13.04.2017.
 */
@RestController
@RequestMapping("/gelir")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @RequestMapping(value="/kaydet", method= RequestMethod.POST)
    public void saveIncome(@RequestBody Income income){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        incomeService.saveIncome(income, username);
    }

    @RequestMapping(value="/liste", method=RequestMethod.GET)
    public List<Income> getAllIncomes(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        return incomeService.getAllIncomes(username);
    }

    @RequestMapping(value="/donemeGoreGetir", method=RequestMethod.GET)
    public List<Income> getAllIncomesByPeriod(Long periodId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        return incomeService.getAllIncomesByPeriodId(username, periodId);
    }

    @RequestMapping(value="/sorgula", method=RequestMethod.GET)
    public List<Income> queryIncomes(IncomeQueryParamsWrapper queryParamsWrapper){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        return incomeService.queryIncomes(username, queryParamsWrapper);
    }

    @RequestMapping(value="/gelir/{id}", method=RequestMethod.GET)
    public Income getIncome(@PathVariable("id") Long id){
        return incomeService.getIncome(id);
    }

    @RequestMapping(value="/guncelle", method=RequestMethod.PUT)
    public Income updateIncome(@RequestBody Income income){
        return incomeService.updateIncome(income);
    }

    @RequestMapping(value="/sil/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteIncome(@PathVariable("id") Long id){
        incomeService.deleteIncome(id);
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }

}
