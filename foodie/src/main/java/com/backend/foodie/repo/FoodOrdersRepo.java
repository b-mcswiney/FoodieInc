package com.backend.foodie.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.foodie.entity.FoodOrders;

public interface FoodOrdersRepo extends JpaRepository<FoodOrders, Integer>{

}
