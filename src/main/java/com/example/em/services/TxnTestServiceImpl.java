package com.example.em.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.em.entities.User;
import com.example.em.entities.UserTokens;
import com.example.em.repositories.UserRepository;
import com.example.em.repositories.UserTokensRepository;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Service
public class TxnTestServiceImpl {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserTokensRepository userTokensRepository;

	@Transactional(value = TxType.MANDATORY)
	public String saveMultiple() {
		User user = new User(null, "userName", "bCryptPasswordEncoder.encode(password)", (byte) 0);
		userRepository.save(user);

		UserTokens tokens = new UserTokens(null, 100l, null, "userName", LocalDate.now(),
				LocalDate.now().plus(1, ChronoUnit.DAYS));
		userTokensRepository.save(tokens);
		return "Test";

	}

}
