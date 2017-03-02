package com.dispinar.butcetakip.server.itemoperations.service;

import java.util.List;

import javax.transaction.Transactional;

import com.dispinar.butcetakip.server.common.entity.User;
import com.dispinar.butcetakip.server.common.service.UserService;
import com.dispinar.butcetakip.server.itemoperations.dao.IncomeItemDao;
import com.dispinar.butcetakip.server.itemoperations.entity.IncomeItem;
import com.dispinar.butcetakip.server.itemoperations.query.IncomeItemQueryParamsWrapper;

@Transactional 
public class IncomeItemServiceImpl implements IncomeItemService{

	private IncomeItemDao incomeItemDao;
	private UserService userService;
	
	public void saveIncomeItem(IncomeItem incomeItem, String username) {
		User user = userService.getUserByUsername(username);
		incomeItem.setUser(user);
		
		incomeItemDao.save(incomeItem);
	}

	public List<IncomeItem> getAllIncomeItems(String username) {
		User user = userService.getUserByUsername(username);
		
		return incomeItemDao.findAll(user.getId());
	}
	
	public List<IncomeItem> queryIncomeItems(String username, IncomeItemQueryParamsWrapper queryParamsWrapper) {
		User user = getUserService().getUserByUsername(username);
		
		return incomeItemDao.queryIncomeItems(user.getId(), queryParamsWrapper);
	}

	public IncomeItem getIncomeItem(Long id) {
		return incomeItemDao.findById(id);
	}

	public IncomeItem updateIncomeItem(IncomeItem incomeItem) {
		return incomeItemDao.update(incomeItem);
	}

	public void deleteIncomeItem(Long id) {
		IncomeItem attechedIncomeItem = incomeItemDao.findById(id);
		incomeItemDao.delete(attechedIncomeItem);
	}
	
	//---------------------Getter&Setters------------------------------
		public IncomeItemDao getIncomeItemDao() {
			return incomeItemDao;
		}

		public void setIncomeItemDao(IncomeItemDao incomeItemDao) {
			this.incomeItemDao = incomeItemDao;
		}

		public UserService getUserService() {
			return userService;
		}

		public void setUserService(UserService userService) {
			this.userService = userService;
		}

}
