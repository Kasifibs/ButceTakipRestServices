package com.dispinar.butcetakip.server.service;

import java.util.List;

import javax.transaction.Transactional;

import com.dispinar.butcetakip.server.dao.IncomeItemDao;
import com.dispinar.butcetakip.server.entity.IncomeItem;
import com.dispinar.butcetakip.server.entity.User;

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
