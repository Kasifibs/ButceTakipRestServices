package com.dispinar.butcetakip.server.common.dao;

import java.util.List;

import com.dispinar.butcetakip.server.common.entity.Period;
import com.dispinar.butcetakip.server.common.query.PeriodQueryParamsWrapper;

public interface PeriodDao {

	void save(Period period);
	
	Period findById(Long id);
	
	List<Period> findAll(Long userId);
	
	List<Period> queryPeriods(Long userId, PeriodQueryParamsWrapper queryParams);

    Long queryCountOfPeriods(Long userId, PeriodQueryParamsWrapper queryParams);
	
	Period update(Period period);
	
	void delete(Period period);
	
}
