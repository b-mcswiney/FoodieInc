package com.backend.foodie.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.foodie.entity.Users;

public interface UsersRepo extends JpaRepository<Users, Integer> {
	public Users findByUsername(String username);
}
