package com.backend.foodie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.foodie.entity.Restaurants;
import com.backend.foodie.repo.RestaurantsRepo;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class RestaurantService {
	@Autowired
	private RestaurantsRepo repo;
	
	public List<Restaurants> getAllRestaurants(){
		return repo.findAll();
	}
	
	public Restaurants getById(int id) {
		if(!repo.existsById(id))
			throw new EntityNotFoundException("Cannot find "+id);
		return repo.findById(id).get();
	}
	
	public Restaurants addNewRestaurant(Restaurants newRestaurant) {
		if(repo.existsById(newRestaurant.getId()))
			throw new EntityExistsException(newRestaurant.getName()+ " already exists");
		return repo.save(newRestaurant);
	}
	
	public Restaurants updateRestaurant(Restaurants toUpdate) {
		if(!repo.existsById(toUpdate.getId()))
			throw new EntityNotFoundException("Cannot find "+toUpdate.getId());
		return repo.save(toUpdate);
	}
	
	public boolean deleteRestaurant(int id) {
		if(!repo.existsById(id))
			throw new EntityNotFoundException("Cannot find "+id);
		
		Restaurants toDelete = repo.findById(id).get();
		repo.delete(toDelete);
		return true;
	}
}
