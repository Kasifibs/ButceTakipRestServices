package com.dispinar.butcetakip.server.iteminstances.controller;

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

import com.dispinar.butcetakip.server.iteminstances.entity.Resource;
import com.dispinar.butcetakip.server.iteminstances.service.ResourceService;

@RestController
@RequestMapping("/varlik")
public class ResourceController {
	
	@Autowired
	private ResourceService resourceService;
	
	@RequestMapping(value="/kaydet", method=RequestMethod.POST)
	public void saveResource(@RequestBody Resource resource){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username = auth.getName();
		
		resourceService.saveResource(resource, username);
	}
	
	@RequestMapping(value="/liste", method=RequestMethod.GET)
	public List<Resource> getAllResources(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username = auth.getName();
	      
		return resourceService.getAllResources(username);
	}
	
	@RequestMapping(value="/varlik/{id}", method=RequestMethod.GET)
	public Resource getResource(@PathVariable("id") Long id){
		return resourceService.getResource(id);
	}
	
	@RequestMapping(value="/guncelle", method=RequestMethod.PUT)
	public Resource updateResource(@RequestBody Resource resource){
		return resourceService.updateResource(resource);
	}
	
	@RequestMapping(value="/sil/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteResource(@PathVariable("id") Long id){
		resourceService.deleteResource(id);
		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
	}

}
