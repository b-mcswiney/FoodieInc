package com.backend.foodie.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="restaurants")
public class Restaurants {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@Column(length=150)
	String address;
	@Column(length=150)
	String name;
	int rating;
	
	@OneToMany(mappedBy = "restaurant")
	private Set<FoodOrders> orders;
	
	public Restaurants() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Restaurants(String name, String address, int rating) {
		super();
		this.name = name;
		this.address = address;
		this.rating = rating;
	}
	
	public Restaurants(int id, String name, String address, int rating) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.rating = rating;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public int getRating() {
		return rating;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	@Override
	public String toString() {
		return "Restaurants [id=" + id + ", name="+name+", address=" + address + ", rating=" + rating +"]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
