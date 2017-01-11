package com.dispinar.butcetakip.server.common.service;

import java.util.List;

import javax.transaction.Transactional;

import com.dispinar.butcetakip.server.common.dao.PeriodDao;
import com.dispinar.butcetakip.server.common.entity.Period;
import com.dispinar.butcetakip.server.common.entity.User;

@Transactional
public class PeriodServiceImpl implements PeriodService{

	private PeriodDao periodDao;
	private UserService userService;
	
	public void savePeriod(Period period, String username) {
		User user = getUserService().getUserByUsername(username);
		period.setUser(user);
		
		getPeriodDao().save(period);
	}

	public List<Period> getAllPeriods(String username) {
		User user = getUserService().getUserByUsername(username);
		
		return getPeriodDao().findAll(user.getId());
	}

	public Period getPeriod(Long id) {
		return getPeriodDao().findById(id);
	}

	public Period updatePeriod(Period detachedPeriod) {
		return getPeriodDao().update(detachedPeriod);
	}

	public void deletePeriod(Long id) {
		Period period = getPeriodDao().findById(id);
		getPeriodDao().delete(period);
	}

	public PeriodDao getPeriodDao() {
		return periodDao;
	}

	public void setPeriodDao(PeriodDao periodDao) {
		this.periodDao = periodDao;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
