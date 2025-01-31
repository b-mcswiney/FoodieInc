package com.backend.foodie.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.foodie.entity.FoodOrders;
import com.backend.foodie.entity.Users;

public interface FoodOrdersRepo extends JpaRepository<FoodOrders, Integer>{
	public List<FoodOrders> findByUser(Users user);
}
