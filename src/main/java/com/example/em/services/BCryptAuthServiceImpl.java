package com.example.em.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.em.entities.User;
import com.example.em.repositories.UserRepository;

@Service
public class BCryptAuthServiceImpl implements IAuthServiceWithoutToken {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Optional<User> login(String userName, String password) {
		Optional<User> savedUser = userRepository.findByEmail(userName);
		if (savedUser.isPresent() && bCryptPasswordEncoder.matches(password, savedUser.get().getPassword())) {
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
		return userRepository
				.save(new User(null, userName, bCryptPasswordEncoder.encode(password), (byte) 0));
	}

	@Override
	public void forgotPassword(String userName) {

	}

}
