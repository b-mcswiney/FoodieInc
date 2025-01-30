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
		Restaurants r2 = new Restaurants("Not McDonalds","1800 not Street",7, "Definitely not McDonalds");
		Restaurants r3 = new Restaurants("Jane's Spuds","Jane's house",10, "Jane is serving tasty potatoes from her window! WOW!");
		MenuItems m1 = new MenuItems("Single Burger", 10.99);
		MenuItems m2 = new MenuItems("Double Burger", 13.99);
		MenuItems m3 = new MenuItems("McSandwich", 4.99);
		MenuItems m4 = new MenuItems("McWater", 1.99);
		MenuItems m5 = new MenuItems("McIceCream", 3.99);
		MenuItems m6 = new MenuItems("Just a Whole potato", 1.99);
		MenuItems m7 = new MenuItems("Just a Whole potato + butter", 2.99);
		m1.setRestaurant(r1);
		m2.setRestaurant(r1);
		m3.setRestaurant(r2);
		m4.setRestaurant(r2);
		m5.setRestaurant(r2);
		m6.setRestaurant(r3);
		m7.setRestaurant(r3);
		
		usersRepo.save(u1);
		restaurantsRepo.save(r1);
		restaurantsRepo.save(r2);
		restaurantsRepo.save(r3);
		menuItemsRepo.save(m1);
		menuItemsRepo.save(m2);
		menuItemsRepo.save(m3);
		menuItemsRepo.save(m4);
		menuItemsRepo.save(m5);
		menuItemsRepo.save(m6);
		menuItemsRepo.save(m7);
	}
	
}
