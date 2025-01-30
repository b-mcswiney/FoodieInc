package com.backend.foodie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.foodie.entity.MenuItems;
import com.backend.foodie.entity.Restaurants;
import com.backend.foodie.repo.MenuItemsRepo;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class MenuItemsService {
	@Autowired
	private MenuItemsRepo repo;
	
	public List<MenuItems> getAllMenuItems(){
		return repo.findAll();
	}
	
	public MenuItems getById(int id) {
		if(!repo.existsById(id))
			throw new EntityNotFoundException("Cannot find "+id);
		return repo.findById(id).get();
	}
	
	public List<MenuItems> getByRestaurant(Restaurants restaurant){
		return repo.findByRestaurant(restaurant);
	}
	
	public MenuItems addNewMenuItem(MenuItems newMenuItem) {
		if(repo.existsById(newMenuItem.getId()))
			throw new EntityExistsException(newMenuItem.getName()+ " already exists");
		return repo.save(newMenuItem);
	}
	
	public MenuItems updateMenuItem(MenuItems toUpdate) {
		if(!repo.existsById(toUpdate.getId()))
			throw new EntityNotFoundException("Cannot find "+toUpdate.getId());
		return repo.save(toUpdate);
	}
	
	public boolean deleteMenuItem(int id) {
		if(!repo.existsById(id))
			throw new EntityNotFoundException("Cannot find "+id);
		
		MenuItems toDelete = repo.findById(id).get();
		repo.delete(toDelete);
		return true;
	}
}
