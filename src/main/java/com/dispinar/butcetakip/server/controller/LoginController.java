package com.dispinar.butcetakip.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oturum")
public class LoginController {

	@RequestMapping(value="/girisYap", method=RequestMethod.GET)
	public ResponseEntity<Boolean> login(){
		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
	}
	
	@RequestMapping(value="/kontrol", method=RequestMethod.GET)
	public ResponseEntity<Boolean> checkStatus(){
		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
	}
}
