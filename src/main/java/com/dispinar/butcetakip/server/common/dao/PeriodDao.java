package com.dispinar.butcetakip.server.common.dao;

import java.util.List;

import com.dispinar.butcetakip.server.common.entity.Period;
import com.dispinar.butcetakip.server.common.query.PeriodQueryParamsWrapper;

public interface PeriodDao {

	public void save(Period period);
	
	public Period findById(Long id);
	
	public List<Period> findAll(Long userId);
	
	public List<Period> queryPeriods(Long userId, PeriodQueryParamsWrapper queryParams);
	
	public Period update(Period period);
	
	public void delete(Period period);
	
}
