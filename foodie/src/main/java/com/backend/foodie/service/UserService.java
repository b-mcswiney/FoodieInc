package com.backend.foodie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.foodie.entity.Users;
import com.backend.foodie.repo.UsersRepo;

@Service
public class UserService {
	@Autowired
	private UsersRepo repo;
	
	public Users getUserByName(String username) {
		return repo.findByUsername(username);
	}
	
	public Users updateUser(Users updatedUser) {
		if(!repo.existsById(updatedUser.getId())) {
			return null;
		}
		return repo.save(updatedUser);
	}
	
	public int deleteUser(Users toDelete) {
		if(!repo.existsById(toDelete.getId())) {
			return 0;
		}
		repo.delete(toDelete);
		return 1;
	}
}
