package com.example.em.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.em.entities.User;
import com.example.em.entities.UserTokens;
import com.example.em.repositories.UserRepository;
import com.example.em.repositories.UserTokensRepository;

@Service
public class AuthServiceWithTokenImpl implements IAuthServiceWithToken {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserTokensRepository userTokensRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Optional<User> login(String userName, String password) {
		Optional<User> savedUser = userRepository.findByEmail(userName);
		if (savedUser.isPresent() && bCryptPasswordEncoder.matches(password, savedUser.get().getPassword())) {
			// The actual token can be different , right now I am using the encoded password
			// itself
			UserTokens tokens = new UserTokens(null, savedUser.get().getId(), savedUser.get().getPassword(), userName,
					LocalDate.now(), LocalDate.now().plus(1, ChronoUnit.DAYS));
			userTokensRepository.save(tokens);
			return savedUser;
		}
		throw new RuntimeException("Incorrect Credentials");
	}

	@Override
	public User signUp(String userName, String password) {
		Optional<User> savedUser = userRepository.findByEmail(userName);
		if (savedUser.isPresent()) {
			return savedUser.get();
		}
		return userRepository.save(new User(null, userName, bCryptPasswordEncoder.encode(password), (byte) 0));
	}

	@Override
	public void forgotPassword(String userName) {

	}

	@Override
	public boolean isValidToken(String token, String email) {
		Optional<UserTokens> userToken = userTokensRepository.findByTokenAndEmail(token, email);
		return userToken.isPresent() && userToken.get().getExpiredBy().isAfter(LocalDate.now());
	}

}
