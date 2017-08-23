package com.dispinar.butcetakip.server.common.controller.dto.period;

import com.dispinar.butcetakip.server.iteminstances.entity.Expense;

public class PeriodExpenseDTO {

    private Long id;

    private String amount;

    private String expenseItemName;

    public PeriodExpenseDTO(Expense expense){
        this.id = expense.getId();
        this.amount = expense.getAmount().toPlainString();
        this.expenseItemName = expense.getExpenseItem().getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getExpenseItemName() {
        return expenseItemName;
    }

    public void setExpenseItemName(String expenseItemName) {
        this.expenseItemName = expenseItemName;
    }
}
