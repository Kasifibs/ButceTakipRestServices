package com.dispinar.butcetakip.server.common.controller.dto.period;

import com.dispinar.butcetakip.server.iteminstances.entity.Income;

import java.math.BigDecimal;
import java.util.List;

public class PeriodIncomesInformationDTO {

    private List<Income> periodIncomesList;

    private String periodTotalIncomes;

    public PeriodIncomesInformationDTO(List<Income> periodIncomesList, BigDecimal periodTotalIncomes){
        this.periodIncomesList = periodIncomesList;
        this.periodTotalIncomes = periodTotalIncomes.toPlainString();
    }

    public List<Income> getPeriodIncomesList() {
        return periodIncomesList;
    }

    public void setPeriodIncomesList(List<Income> periodIncomesList) {
        this.periodIncomesList = periodIncomesList;
    }

    public String getPeriodTotalIncomes() {
        return periodTotalIncomes;
    }

    public void setPeriodTotalIncomes(String periodTotalIncomes) {
        this.periodTotalIncomes = periodTotalIncomes;
    }
}
