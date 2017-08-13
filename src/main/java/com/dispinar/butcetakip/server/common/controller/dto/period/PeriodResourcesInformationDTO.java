package com.dispinar.butcetakip.server.common.controller.dto.period;

import com.dispinar.butcetakip.server.iteminstances.entity.Resource;

import java.math.BigDecimal;
import java.util.List;

public class PeriodResourcesInformationDTO {

    private List<Resource> periodResourcesList;

    private String periodBeginAmount;

    private String periodEndAmount;

    private String periodResult;

    public PeriodResourcesInformationDTO(List<Resource> resourceList, BigDecimal beginAmount, BigDecimal endAmount){
        this.periodResourcesList = resourceList;
        this.periodBeginAmount = beginAmount.toPlainString();
        this.periodEndAmount = endAmount.toPlainString();
        this.periodResult = endAmount.subtract(beginAmount).toPlainString();
    }

    public List<Resource> getPeriodResourcesList() {
        return periodResourcesList;
    }

    public void setPeriodResourcesList(List<Resource> periodResourcesList) {
        this.periodResourcesList = periodResourcesList;
    }

    public String getPeriodBeginAmount() {
        return periodBeginAmount;
    }

    public void setPeriodBeginAmount(String periodBeginAmount) {
        this.periodBeginAmount = periodBeginAmount;
    }

    public String getPeriodEndAmount() {
        return periodEndAmount;
    }

    public void setPeriodEndAmount(String periodEndAmount) {
        this.periodEndAmount = periodEndAmount;
    }

    public String getPeriodResult() {
        return periodResult;
    }

    public void setPeriodResult(String periodResult) {
        this.periodResult = periodResult;
    }
}
