package com.dispinar.butcetakip.server.common.controller.dto.period;

import com.dispinar.butcetakip.server.iteminstances.entity.Expense;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PeriodExpensesInformationDTO {

    private String periodBeginEndResourceDifference;

    private String periodTotalIncome;

    private String periodTotalExpense;

    private String periodKnownExpensesTotal;

    private List<PeriodExpenseDTO> periodExpensesList;


    public PeriodExpensesInformationDTO(List<Expense> periodExpenses, String periodBeginEndResourceDifference, String periodTotalIncome, String periodTotalExpense, String periodKnownExpensesTotal){
        this.periodBeginEndResourceDifference = periodBeginEndResourceDifference;
        this.periodTotalIncome = periodTotalIncome;
        this.periodTotalExpense = periodTotalExpense;
        this.periodKnownExpensesTotal = periodKnownExpensesTotal;
        this.periodExpensesList = periodExpenses.stream().map(PeriodExpenseDTO::new).collect(Collectors.toList());
    }

    public String getPeriodBeginEndResourceDifference() {
        return periodBeginEndResourceDifference;
    }

    public void setPeriodBeginEndResourceDifference(String periodBeginEndResourceDifference) {
        this.periodBeginEndResourceDifference = periodBeginEndResourceDifference;
    }

    public String getPeriodTotalIncome() {
        return periodTotalIncome;
    }

    public void setPeriodTotalIncome(String periodTotalIncome) {
        this.periodTotalIncome = periodTotalIncome;
    }

    public String getPeriodTotalExpense() {
        return periodTotalExpense;
    }

    public void setPeriodTotalExpense(String periodTotalExpense) {
        this.periodTotalExpense = periodTotalExpense;
    }

    public String getPeriodKnownExpensesTotal() {
        return periodKnownExpensesTotal;
    }

    public void setPeriodKnownExpensesTotal(String periodKnownExpensesTotal) {
        this.periodKnownExpensesTotal = periodKnownExpensesTotal;
    }

    public List<PeriodExpenseDTO> getPeriodExpensesList() {
        return periodExpensesList;
    }

    public void setPeriodExpensesList(List<PeriodExpenseDTO> periodExpensesList) {
        this.periodExpensesList = periodExpensesList;
    }
}
