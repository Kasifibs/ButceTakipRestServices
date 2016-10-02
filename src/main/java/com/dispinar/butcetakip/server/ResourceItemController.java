package com.dispinar.butcetakip.server;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@RequestMapping(value="deneme", method=RequestMethod.GET)
	public String deneme(){
		return "basarili";
	}
	
	@RequestMapping(value="kaydet", method=RequestMethod.POST)
	public void saveResourceItem(@RequestBody ResourceItem resourceItem){
		resourceItemService.saveResourceItem(resourceItem);
	}
}
