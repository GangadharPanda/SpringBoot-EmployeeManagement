package com.example.em.services;

public interface IAuthServiceWithToken extends IAuthServiceWithoutToken {

	boolean isValidToken(String token, String email);
}
