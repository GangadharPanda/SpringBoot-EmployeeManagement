package com.example.em.services;

import java.util.Optional;

import com.example.em.entities.User;

public interface IAuthServiceWithoutToken {
	
	public Optional<User> login(String email, String password) ;
	
	public User signUp(String email, String password) ;
	
	public void forgotPassword(String email) ;
}
