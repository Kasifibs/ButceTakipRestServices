package com.dispinar.butcetakip.server.common.service;

import java.util.List;

import com.dispinar.butcetakip.server.common.controller.dto.period.PeriodResourcesInformationDTO;
import com.dispinar.butcetakip.server.common.entity.Period;
import com.dispinar.butcetakip.server.common.query.PeriodQueryParamsWrapper;

public interface PeriodService {

    void savePeriod(Period period, String username);
	
	List<Period> getAllPeriods(String username);
	
	List<Period> queryPeriods(String username, PeriodQueryParamsWrapper queryParams);
	
	Period getPeriod(Long id);
	
	Period updatePeriod(Period detachedPeriod);
	
	void deletePeriod(Long id);

    PeriodResourcesInformationDTO preparePeriodResourcesInformation(String username, Long periodId);
}
