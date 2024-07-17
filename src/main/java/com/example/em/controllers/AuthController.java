package com.example.em.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.em.dtos.LoginInfoDTO;
import com.example.em.entities.User;
import com.example.em.services.IAuthServiceWithoutToken;

@RestController
@RequestMapping("auth")
public class AuthController {
	
	@Autowired
	
	@Qualifier("BCryptAuthServiceImpl")
	private IAuthServiceWithoutToken authServiceWithoutToken;
	
	@PostMapping("/login")
	public Optional<User> login(@RequestBody LoginInfoDTO loginInfo) {
		return authServiceWithoutToken.login(loginInfo.getEmail(), loginInfo.getPassword()) ;
	}
	
	@PostMapping("/signUp")
	public User signUp(@RequestBody LoginInfoDTO loginInfo) {
     return authServiceWithoutToken.signUp(loginInfo.getEmail(), loginInfo.getPassword()) ;
	}
	
	@PostMapping("/forgotPassword")
	public void forgotPassword(@RequestBody LoginInfoDTO loginInfo) {
		// TODO Auto-generated method stub
	}
	
}
