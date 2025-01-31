package com.backend.foodie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.foodie.entity.FoodOrders;
import com.backend.foodie.entity.Users;
import com.backend.foodie.repo.FoodOrdersRepo;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class FoodOrderService {
	@Autowired
	private FoodOrdersRepo repo;
	
	public List<FoodOrders> getAllFoodOrders() {
		return repo.findAll();
	}
	
	public List<FoodOrders> getByUser(Users user){
		return repo.findByUser(user);
	}
	
	public FoodOrders getById(int id) {
		if(!repo.existsById(id))
			throw new EntityNotFoundException("Cannot find "+id);
		return repo.findById(id).get();
	}
	
	public FoodOrders addFoodOrder(FoodOrders toAdd) {
		if(repo.existsById(toAdd.getId()))
			throw new EntityExistsException(toAdd.getId()+" Already exist");
		return repo.save(toAdd);
	}
	
	public FoodOrders updateFoodOrder(FoodOrders toUpdate) {
		if(!repo.existsById(toUpdate.getId()))
			throw new EntityNotFoundException("Cannot find "+toUpdate.getId());
		return repo.save(toUpdate);
	}
	
	public boolean deleteFoodOrder(int id) {
		if(!repo.existsById(id))
			throw new EntityNotFoundException("Cannot find "+id);
		
		FoodOrders toDelete = repo.findById(id).get();
		repo.delete(toDelete);
		return true;
	}
}
