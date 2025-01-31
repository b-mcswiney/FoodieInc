package com.backend.foodie.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.foodie.entity.FoodOrders;
import com.backend.foodie.entity.Users;
import com.backend.foodie.service.FoodOrderService;
import com.backend.foodie.service.UserService;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/foodorders")
public class FoodOrderController {
	@Autowired
	private FoodOrderService service;
	@Autowired
	private UserService userService;
	
	@GetMapping
	private ResponseEntity<Object> getAllOrders(){
		List<FoodOrders> orders = service.getAllFoodOrders();
		return ResponseEntity.ok(orders);
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<Object> getOrderById(@PathVariable int id){
		try {
			FoodOrders order = service.getById(id);
			return ResponseEntity.ok(order);
		} catch(EntityNotFoundException e) {
			Map<String, String> errorMap = new HashMap<>();
			errorMap.put("error", e.getMessage());
			return ResponseEntity.badRequest().body(errorMap);
		}
	}
	
	@GetMapping("/user/{name}")
	private ResponseEntity<Object> getByUser(@PathVariable String name){
		Users user = userService.getUserByName(name);
		List<FoodOrders> orders = service.getByUser(user);
		return ResponseEntity.ok(orders);
	}
	
	@PostMapping
	public ResponseEntity<Object> addNewOrder(@RequestBody FoodOrders toAdd){
		try {
			FoodOrders added = service.addFoodOrder(toAdd);
			return ResponseEntity.ok(added);
		} catch(EntityExistsException e) {
			Map<String, String> errorMap = new HashMap<>();
			errorMap.put("error", e.getMessage());
			return ResponseEntity.badRequest().body(errorMap);
		}
	}
	
	@PutMapping
	public ResponseEntity<Object> updateFoodOrder(@RequestBody FoodOrders toUpdate){
		try {
			FoodOrders updated = service.updateFoodOrder(toUpdate);
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
            service.deleteFoodOrder(id);
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
