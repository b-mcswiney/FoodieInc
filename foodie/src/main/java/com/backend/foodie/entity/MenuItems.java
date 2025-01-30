package com.backend.foodie.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class MenuItems {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@Column(length=150)
	String name;
	
	double price;
	
	@ManyToOne
	@JoinColumn(name="restaurant_id")
	Restaurants restaurant;
	
	public MenuItems() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MenuItems(String name, double price) {
		super();
		this.name = name;
		this.price = price;
	}

	public MenuItems(int id, String name, double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "MenuItems [id=" + id + ", name=" + name + ", price=" + price + "]";
	}

	public Restaurants getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurants restaurant) {
		this.restaurant = restaurant;
	}
}
