package com.dispinar.butcetakip.server.iteminstances.query;

import com.dispinar.butcetakip.server.common.query.AbstractQueryParamsWrapper;

import java.math.BigDecimal;

/**
 * Created by Tolga on 16.04.2017.
 */
public class ExpenseQueryParamsWrapper extends AbstractQueryParamsWrapper{

    private Long expenseItemId;

    private Long periodId;

    private BigDecimal minAmount;

    private BigDecimal maxAmount;

    public Long getExpenseItemId() {
        return expenseItemId;
    }

    public void setExpenseItemId(Long expenseItemId) {
        this.expenseItemId = expenseItemId;
    }

    public Long getPeriodId() {
        return periodId;
    }

    public void setPeriodId(Long periodId) {
        this.periodId = periodId;
    }

    public BigDecimal getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }
}
