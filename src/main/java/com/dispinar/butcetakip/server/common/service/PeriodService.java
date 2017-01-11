package com.dispinar.butcetakip.server.common.service;

import java.util.List;

import com.dispinar.butcetakip.server.common.entity.Period;

public interface PeriodService {

	public void savePeriod(Period period, String username);
	
	public List<Period> getAllPeriods(String username);
	
	public Period getPeriod(Long id);
	
	public Period updatePeriod(Period detachedPeriod);
	
	public void deletePeriod(Long id);
}
