package com.backend.foodie.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class FoodOrders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	Users user;
	
	@ManyToOne
	@JoinColumn(name="restaurant_id")
	Restaurants restaurant;
	
	LocalDateTime order_time;
	
	public FoodOrders() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FoodOrders(Users user, Restaurants restaurant, LocalDateTime order_time) {
		super();
		this.user = user;
		this.restaurant = restaurant;
		this.order_time = order_time;
	}

	public FoodOrders(int id, Users user, Restaurants restaurant, LocalDateTime order_time) {
		super();
		this.id = id;
		this.user = user;
		this.restaurant = restaurant;
		this.order_time = order_time;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Restaurants getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurants restaurant) {
		this.restaurant = restaurant;
	}

	public LocalDateTime getOrder_time() {
		return order_time;
	}

	public void setOrder_time(LocalDateTime order_time) {
		this.order_time = order_time;
	}

	@Override
	public String toString() {
		return "FoodOrders [id=" + id + ", user=" + user + ", restaurant=" + restaurant + ", order_time=" + order_time
				+ "]";
	}
}
