package com.dispinar.butcetakip.server.itemoperations.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dispinar.butcetakip.server.itemoperations.entity.ExpenseItem;
import com.dispinar.butcetakip.server.itemoperations.service.ExpenseItemService;

@RestController
@RequestMapping("/harcamaKalemi")
public class ExpenseItemController {
	
	@Autowired
	private ExpenseItemService expenseItemService;
	
	@RequestMapping(value="/kaydet", method=RequestMethod.POST)
	public void saveExpenseItem(@RequestBody ExpenseItem expenseItem){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username = auth.getName();
		
		expenseItemService.saveExpenseItem(expenseItem, username);
	}
	
	@RequestMapping(value="/liste", method=RequestMethod.GET)
	public List<ExpenseItem> getAllExpenseItems(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username = auth.getName();
	      
		return expenseItemService.getAllExpenseItems(username);
	}
	
	@RequestMapping(value="/kalem/{id}", method=RequestMethod.GET)
	public ExpenseItem getExpenseItem(@PathVariable("id") Long id){
		return expenseItemService.getExpenseItem(id);
	}
	
	@RequestMapping(value="/guncelle", method=RequestMethod.PUT)
	public ExpenseItem updateExpenseItem(@RequestBody ExpenseItem expenseItem){
		return expenseItemService.updateExpenseItem(expenseItem);
	}
	
	@RequestMapping(value="/sil/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteExpenseItem(@PathVariable("id") Long id){
		expenseItemService.deleteExpenseItem(id);
		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
	}
}
