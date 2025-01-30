package com.backend.foodie.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.foodie.entity.MenuItems;
import com.backend.foodie.entity.Restaurants;

public interface MenuItemsRepo extends JpaRepository<MenuItems, Integer>{
	public List<MenuItems> findByRestaurant(Restaurants restaurant);
}
