package com.backend.foodie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.backend.foodie.entity.MenuItems;
import com.backend.foodie.entity.Restaurants;
import com.backend.foodie.entity.Users;
import com.backend.foodie.repo.MenuItemsRepo;
import com.backend.foodie.repo.RestaurantsRepo;
import com.backend.foodie.repo.UsersRepo;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class FoodieApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodieApplication.class, args);
	}
	
	@Autowired
	MenuItemsRepo menuItemsRepo;
	@Autowired
	RestaurantsRepo restaurantsRepo;
	@Autowired
	UsersRepo usersRepo;

	@PostConstruct
	public void initialize() {
		Users u1 = new Users("Jim", "password", "Jim@example.com", "Jim's house");
		Restaurants r1 = new Restaurants("John's burgers","John's van",10, "John's tasty burger van");
		MenuItems m1 = new MenuItems("Single Burger", 10.99);
		m1.setRestaurant(r1);
		
		usersRepo.save(u1);
		restaurantsRepo.save(r1);
		menuItemsRepo.save(m1);
	}
	
}
