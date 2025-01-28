package com.backend.foodie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.foodie.entity.Users;
import com.backend.foodie.repo.UsersRepo;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
	@Autowired
	private UsersRepo repo;
	
	public Users getUserByName(String username) {
		if(!repo.existsByUsername(username)){
			throw new EntityNotFoundException(username+" not found");
		}
		return repo.findByUsername(username);
	}
	
	public Users addUser(Users toAdd) {
		if(repo.existsByUsername(toAdd.getUsername())) {
			throw new EntityExistsException(toAdd.getUsername()+" taken");
		}
		return repo.save(toAdd);
	}
	
	public Users updateUser(Users updatedUser) {
		if(!repo.existsById(updatedUser.getId())) {
			throw new EntityNotFoundException("Cannot update "+updatedUser.getUsername()+" does not exist");
		}
		return repo.save(updatedUser);
	}
	
	public int deleteUser(int toDelete) {
		if(!repo.existsById(toDelete)) {
			throw new EntityNotFoundException("Cannot delete "+toDelete+" does not exist");
		}
		Users user = repo.findById(toDelete).get();
		
		repo.delete(user);
		return 1;
	}
}
