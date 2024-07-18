package com.example.em.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.em.entities.UserTokens;

@Repository
public interface UserTokensRepository extends JpaRepository<UserTokens, Long> {

	public Optional<UserTokens> findByTokenAndEmail(String token, String email);

	public UserTokens save(UserTokens user);
}
