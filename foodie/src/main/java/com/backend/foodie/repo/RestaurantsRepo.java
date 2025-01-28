package com.backend.foodie.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.foodie.entity.Restaurants;

public interface RestaurantsRepo extends JpaRepository<Restaurants, Integer>{

}
