package com.dispinar.butcetakip.server.iteminstances.query;

import com.dispinar.butcetakip.server.common.query.AbstractQueryParamsWrapper;

import java.math.BigDecimal;

/**
 * Created by Tolga on 13.04.2017.
 */
public class IncomeQueryParamsWrapper extends AbstractQueryParamsWrapper {

    private Long incomeItemId;

    private Long periodId;

    private BigDecimal minAmount;

    private BigDecimal maxAmount;

    public Long getIncomeItemId() {
        return incomeItemId;
    }

    public void setIncomeItemId(Long incomeItemId) {
        this.incomeItemId = incomeItemId;
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
