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

import com.dispinar.butcetakip.server.itemoperations.entity.IncomeItem;
import com.dispinar.butcetakip.server.itemoperations.service.IncomeItemService;

@RestController
@RequestMapping("/gelirKalemi")
public class IncomeItemController {
	
	@Autowired
	private IncomeItemService incomeItemService;
	
	@RequestMapping(value="/kaydet", method=RequestMethod.POST)
	public void saveIncomeItem(@RequestBody IncomeItem incomeItem){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username = auth.getName();
		
		incomeItemService.saveIncomeItem(incomeItem, username);
	}
	
	@RequestMapping(value="/liste", method=RequestMethod.GET)
	public List<IncomeItem> getAllIncomeItems(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username = auth.getName();
	      
		return incomeItemService.getAllIncomeItems(username);
	}

	@RequestMapping(value="/kalem/{id}", method=RequestMethod.GET)
	public IncomeItem getIncomeItem(@PathVariable("id") Long id){
		return incomeItemService.getIncomeItem(id);
	}
	
	@RequestMapping(value="/guncelle", method=RequestMethod.PUT)
	public IncomeItem updateResourceItem(@RequestBody IncomeItem incomeItem){
		return incomeItemService.updateIncomeItem(incomeItem);
	}
	
	@RequestMapping(value="/sil/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteIncomeItem(@PathVariable("id") Long id){
		incomeItemService.deleteIncomeItem(id);
		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
	}
}
