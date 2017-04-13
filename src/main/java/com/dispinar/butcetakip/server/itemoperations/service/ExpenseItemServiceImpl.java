package com.dispinar.butcetakip.server.itemoperations.service;

import java.util.List;

import com.dispinar.butcetakip.server.common.entity.User;
import com.dispinar.butcetakip.server.common.service.UserService;
import com.dispinar.butcetakip.server.itemoperations.dao.ExpenseItemDao;
import com.dispinar.butcetakip.server.itemoperations.entity.ExpenseItem;
import com.dispinar.butcetakip.server.itemoperations.query.ExpenseItemQueryParamsWrapper;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class ExpenseItemServiceImpl implements ExpenseItemService{

	private ExpenseItemDao expenseItemDao;
	private UserService userService;
	
	public void saveExpenseItem(ExpenseItem expenseItem, String username) {
		User user = userService.getUserByUsername(username);
		expenseItem.setUser(user);
		
		expenseItemDao.save(expenseItem);
	}

	public List<ExpenseItem> getAllExpenseItems(String username) {
		User user = userService.getUserByUsername(username);
		
		return expenseItemDao.findAll(user.getId());
	}
	
	public List<ExpenseItem> queryExpenseItems(String username, ExpenseItemQueryParamsWrapper queryParamsWrapper) {
		User user = getUserService().getUserByUsername(username);
		
		return expenseItemDao.queryExpenseItems(user.getId(), queryParamsWrapper);
	}

	public ExpenseItem getExpenseItem(Long id) {
		return expenseItemDao.findById(id);
	}

	public ExpenseItem updateExpenseItem(ExpenseItem expenseItem) {
		return expenseItemDao.update(expenseItem);
	}

	public void deleteExpenseItem(Long id) {
		ExpenseItem expenseItem = expenseItemDao.findById(id);
		expenseItemDao.delete(expenseItem);
	}

	
	//------------------Getter&Setter------------------------
	public ExpenseItemDao getExpenseItemDao() {
		return expenseItemDao;
	}

	public void setExpenseItemDao(ExpenseItemDao expenseItemDao) {
		this.expenseItemDao = expenseItemDao;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
