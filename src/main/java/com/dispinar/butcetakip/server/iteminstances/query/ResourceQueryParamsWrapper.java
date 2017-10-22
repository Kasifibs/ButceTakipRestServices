package com.dispinar.butcetakip.server.iteminstances.query;

import com.dispinar.butcetakip.server.common.query.AbstractQueryParamsWrapper;

import java.math.BigDecimal;

/**
 * Created by Tolga on 19.03.2017.
 */
public class ResourceQueryParamsWrapper extends AbstractQueryParamsWrapper {

    private Long resourceItemId;

    private Long periodId;

    private BigDecimal minAmount;

    private BigDecimal maxAmount;

    public Long getResourceItemId() {
        return resourceItemId;
    }

    public void setResourceItemId(Long resourceItemId) {
        this.resourceItemId = resourceItemId;
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
