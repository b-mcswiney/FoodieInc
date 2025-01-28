package com.backend.foodie.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.foodie.entity.Restaurants;
import com.backend.foodie.service.RestaurantService;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/restaurants")
public class RestaurantsController {
	@Autowired
	private RestaurantService service;
	
	@GetMapping
	private ResponseEntity<Object> getAllRestaurants(){
		List<Restaurants> restaurants = service.getAllRestaurants();
		return ResponseEntity.ok(restaurants);
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<Object> getRestaurantById(@PathVariable int id){
		try {
			Restaurants restaurant = service.getById(id);
			return ResponseEntity.ok(restaurant);
		} catch(EntityNotFoundException e) {
			Map<String, String> errorMap = new HashMap<>();
			errorMap.put("error", e.getMessage());
			return ResponseEntity.badRequest().body(errorMap);
		}
	}
	
	@PostMapping
	public ResponseEntity<Object> addNewRestaurant(@RequestBody Restaurants toAdd){
		try {
			Restaurants added = service.addNewRestaurant(toAdd);
			return ResponseEntity.ok(added);
		} catch(EntityExistsException e) {
			Map<String, String> errorMap = new HashMap<>();
			errorMap.put("error", e.getMessage());
			return ResponseEntity.badRequest().body(errorMap);
		}
	}
	
	@PutMapping
	public ResponseEntity<Object> updateRestaurant(@RequestBody Restaurants toUpdate){
		try {
			Restaurants updated = service.updateRestaurant(toUpdate);
			return ResponseEntity.ok(updated);
		} catch(EntityNotFoundException e) {
			Map<String, String> errorMap = new HashMap<>();
			errorMap.put("error", e.getMessage());
			return ResponseEntity.badRequest().body(errorMap);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable int id){
		Map<String, String> map = new HashMap<>();
        try {
            service.deleteRestaurant(id);
            map.put("message", "Delete successful");
            return ResponseEntity.ok(map);
        }catch (EntityNotFoundException  e){
        	map.put("status", "404");
            map.put("error", e.getMessage());
        }catch (Exception e){
            if(e.getCause() != null && e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
//                SQLIntegrityConstraintViolationException sql_violation_exception = (SQLIntegrityConstraintViolationException) e.getCause().getCause() ;
                map.put("error", "SQLIntegrityConstraintViolationException has accured. Foreign key cpnstraint" );
            } else {
                System.out.println(e.getMessage());
            }
        }
        
        return ResponseEntity.badRequest().body(map);
	}
}
