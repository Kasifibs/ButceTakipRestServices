package com.dispinar.butcetakip.server;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/varlikKalemi")
public class ResourceItemController {

	@RequestMapping(value="deneme", method=RequestMethod.GET)
	public String deneme(){
		return "basarili";
	}
}
