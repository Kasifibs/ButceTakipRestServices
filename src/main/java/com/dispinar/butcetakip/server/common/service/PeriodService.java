package com.dispinar.butcetakip.server.common.service;

import java.util.List;

import com.dispinar.butcetakip.server.common.controller.dto.period.PeriodExpensesInformationDTO;
import com.dispinar.butcetakip.server.common.controller.dto.period.PeriodIncomesInformationDTO;
import com.dispinar.butcetakip.server.common.controller.dto.period.PeriodResourcesInformationDTO;
import com.dispinar.butcetakip.server.common.entity.Period;
import com.dispinar.butcetakip.server.common.query.PeriodQueryParamsWrapper;
import com.dispinar.butcetakip.server.common.query.QueryResultWrapper;

public interface PeriodService {

    void savePeriod(Period period, String username);
	
	List<Period> getAllPeriods(String username);

    QueryResultWrapper<Period> performQueryAndPrepareResultWrapper(String username, PeriodQueryParamsWrapper queryParams);

	List<Period> queryPeriods(String username, PeriodQueryParamsWrapper queryParams);

    Long queryCountOfPeriods(String username, PeriodQueryParamsWrapper queryParams);
	
	Period getPeriod(Long id);
	
	Period updatePeriod(Period detachedPeriod);
	
	void deletePeriod(Long id);

    PeriodResourcesInformationDTO preparePeriodResourcesInformation(String username, Long periodId);

    PeriodIncomesInformationDTO preparePeriodIncomesInformation(String username, Long periodId);

    PeriodExpensesInformationDTO preparePeriodExpensesInformation(String username, Long periodId);
}
