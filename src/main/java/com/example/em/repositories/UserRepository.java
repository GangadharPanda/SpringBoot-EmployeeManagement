package com.example.em.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.em.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public Optional<User> findByEmail(String email);

	public User save(User user);
}
