package com.dispinar.butcetakip.server.controller;

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

import com.dispinar.butcetakip.server.entity.ResourceItem;
import com.dispinar.butcetakip.server.service.ResourceItemService;

@RestController
@RequestMapping("/varlikKalemi")
public class ResourceItemController {

	@Autowired
	private ResourceItemService resourceItemService;
	
	@RequestMapping(value="/kaydet", method=RequestMethod.POST)
	public void saveResourceItem(@RequestBody ResourceItem resourceItem){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username = auth.getName();
		
		resourceItemService.saveResourceItem(resourceItem, username);
	}
	
	@RequestMapping(value="/liste", method=RequestMethod.GET)
	public List<ResourceItem> getAllResourceItems(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username = auth.getName();
	      
		return resourceItemService.getAllResourceItems(username);
	}
	
	@RequestMapping(value="/kalem/{id}", method=RequestMethod.GET)
	public ResourceItem getResourceItem(@PathVariable("id") Long id){
		return resourceItemService.getResourceItem(id);
	}
	
	@RequestMapping(value="/guncelle", method=RequestMethod.PUT)
	public ResourceItem updateResourceItem(@RequestBody ResourceItem resourceItem){
		return resourceItemService.updateResourceItem(resourceItem);
	}
	
	@RequestMapping(value="/sil/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteResourceItem(@PathVariable("id") Long id){
		resourceItemService.deleteResourceItem(id);
		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
	}
}
