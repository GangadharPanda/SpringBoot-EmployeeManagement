package com.example.em.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.em.services.TxnTestServiceImpl;

@RestController
public class TxnTestController {
	
	@Autowired
	private TxnTestServiceImpl impl;
	
	@GetMapping("/hello")
	public String home() {
		return impl.saveMultiple();
	}

}
