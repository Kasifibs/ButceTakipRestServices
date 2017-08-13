package com.dispinar.butcetakip.server.common.service;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import com.dispinar.butcetakip.server.common.controller.dto.period.PeriodResourcesInformationDTO;
import com.dispinar.butcetakip.server.common.dao.PeriodDao;
import com.dispinar.butcetakip.server.common.entity.Period;
import com.dispinar.butcetakip.server.common.entity.User;
import com.dispinar.butcetakip.server.common.query.PeriodQueryParamsWrapper;
import com.dispinar.butcetakip.server.iteminstances.entity.Resource;
import com.dispinar.butcetakip.server.iteminstances.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;

@Transactional
public class PeriodServiceImpl implements PeriodService{

	private PeriodDao periodDao;

	private UserService userService;

	@Autowired
	private ResourceService resourceService;
	
	public void savePeriod(Period period, String username) {
		User user = getUserService().getUserByUsername(username);
		period.setUser(user);
		
		periodDao.save(period);
	}

	public List<Period> getAllPeriods(String username) {
		User user = getUserService().getUserByUsername(username);
		
		return periodDao.findAll(user.getId());
	}
	
	public List<Period> queryPeriods(String username, PeriodQueryParamsWrapper queryParams) {
		User user = getUserService().getUserByUsername(username);
		
		return periodDao.queryPeriods(user.getId(), queryParams);
	}

	public Period getPeriod(Long id) {
		return periodDao.findById(id);
	}

	public Period updatePeriod(Period detachedPeriod) {
		return periodDao.update(detachedPeriod);
	}

	public void deletePeriod(Long id) {
		Period period = getPeriodDao().findById(id);
		periodDao.delete(period);
	}

    public PeriodResourcesInformationDTO preparePeriodResourcesInformation(String username, Long periodId) {
        List<Resource> periodResourcesList = resourceService.getAllResourcesByPeriod(username, periodId);
        BigDecimal beginAmount = this.getPeriod(periodId).getBeginAmount();
        BigDecimal endAmount = new BigDecimal("0");
        for (Resource resource : periodResourcesList) {
            endAmount = endAmount.add(resource.getAmount());
        }

        return new PeriodResourcesInformationDTO(periodResourcesList, beginAmount, endAmount);
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
