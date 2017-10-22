package com.dispinar.butcetakip.server.common.service;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import com.dispinar.butcetakip.server.common.controller.dto.period.PeriodExpensesInformationDTO;
import com.dispinar.butcetakip.server.common.controller.dto.period.PeriodIncomesInformationDTO;
import com.dispinar.butcetakip.server.common.controller.dto.period.PeriodResourcesInformationDTO;
import com.dispinar.butcetakip.server.common.dao.PeriodDao;
import com.dispinar.butcetakip.server.common.entity.Period;
import com.dispinar.butcetakip.server.common.entity.User;
import com.dispinar.butcetakip.server.common.query.PeriodQueryParamsWrapper;
import com.dispinar.butcetakip.server.common.query.QueryResultWrapper;
import com.dispinar.butcetakip.server.iteminstances.entity.Expense;
import com.dispinar.butcetakip.server.iteminstances.entity.Income;
import com.dispinar.butcetakip.server.iteminstances.entity.Resource;
import com.dispinar.butcetakip.server.iteminstances.service.ExpenseService;
import com.dispinar.butcetakip.server.iteminstances.service.IncomeService;
import com.dispinar.butcetakip.server.iteminstances.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;

@Transactional
public class PeriodServiceImpl implements PeriodService{

	private PeriodDao periodDao;

	private UserService userService;

	@Autowired
	private ResourceService resourceService;

	@Autowired
    private IncomeService incomeService;

	@Autowired
    private ExpenseService expenseService;
	
	public void savePeriod(Period period, String username) {
		User user = getUserService().getUserByUsername(username);
		period.setUser(user);
		
		periodDao.save(period);
	}

	public List<Period> getAllPeriods(String username) {
		User user = getUserService().getUserByUsername(username);
		
		return periodDao.findAll(user.getId());
	}

	public QueryResultWrapper<Period> performQueryAndPrepareResultWrapper(String username, PeriodQueryParamsWrapper queryParams){
        List<Period> periods = queryPeriods(username, queryParams);
        Long countOfPeriods = queryCountOfPeriods(username, queryParams);

        return new QueryResultWrapper<Period>(countOfPeriods, periods);
    }
	
	public List<Period> queryPeriods(String username, PeriodQueryParamsWrapper queryParams) {
		User user = getUserService().getUserByUsername(username);
		
		return periodDao.queryPeriods(user.getId(), queryParams);
	}

	public Long queryCountOfPeriods(String username, PeriodQueryParamsWrapper queryParams){
        User user = getUserService().getUserByUsername(username);

        return periodDao.queryCountOfPeriods(user.getId(), queryParams);
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

    public PeriodIncomesInformationDTO preparePeriodIncomesInformation(String username, Long periodId) {
        List<Income> periodIncomesList = incomeService.getAllIncomesByPeriodId(username, periodId);
        BigDecimal totalAmount = new BigDecimal("0");
        for (Income income : periodIncomesList) {
            totalAmount = totalAmount.add(income.getAmount());
        }

        return new PeriodIncomesInformationDTO(periodIncomesList, totalAmount);
    }

    public PeriodExpensesInformationDTO preparePeriodExpensesInformation(String username, Long periodId) {
        PeriodResourcesInformationDTO periodResourcesInformationDTO = this.preparePeriodResourcesInformation(username, periodId);
        PeriodIncomesInformationDTO periodIncomesInformationDTO = this.preparePeriodIncomesInformation(username, periodId);

        BigDecimal totalExpense = new BigDecimal(periodIncomesInformationDTO.getPeriodTotalIncomes()).subtract(new BigDecimal(periodResourcesInformationDTO.getPeriodResult()));

        List<Expense> periodExpensesList = expenseService.getAllExpensesByPeriod(username, periodId);
        BigDecimal totalKnownExpenses = new BigDecimal("0");
        for (Expense expense : periodExpensesList) {
            totalKnownExpenses = totalKnownExpenses.add(expense.getAmount());
        }

        return new PeriodExpensesInformationDTO(periodExpensesList, periodResourcesInformationDTO.getPeriodResult(), periodIncomesInformationDTO.getPeriodTotalIncomes(), totalExpense.toPlainString(), totalKnownExpenses.toPlainString());

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
