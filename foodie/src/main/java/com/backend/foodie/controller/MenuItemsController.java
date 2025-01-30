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

import com.backend.foodie.entity.MenuItems;
import com.backend.foodie.service.MenuItemsService;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/menu")
public class MenuItemsController {
	@Autowired
	private MenuItemsService service;
	
	@GetMapping
	private ResponseEntity<Object> getAllMenuItems(){
		List<MenuItems> MenuItems = service.getAllMenuItems();
		return ResponseEntity.ok(MenuItems);
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<Object> getMenuItemById(@PathVariable int id){
		try {
			MenuItems MenuItem = service.getById(id);
			return ResponseEntity.ok(MenuItem);
		} catch(EntityNotFoundException e) {
			Map<String, String> errorMap = new HashMap<>();
			errorMap.put("error", e.getMessage());
			return ResponseEntity.badRequest().body(errorMap);
		}
	}
	
	@PostMapping
	public ResponseEntity<Object> addNewMenuItem(@RequestBody MenuItems toAdd){
		try {
			MenuItems added = service.addNewMenuItem(toAdd);
			return ResponseEntity.ok(added);
		} catch(EntityExistsException e) {
			Map<String, String> errorMap = new HashMap<>();
			errorMap.put("error", e.getMessage());
			return ResponseEntity.badRequest().body(errorMap);
		}
	}
	
	@PutMapping
	public ResponseEntity<Object> updateMenuItem(@RequestBody MenuItems toUpdate){
		try {
			MenuItems updated = service.updateMenuItem(toUpdate);
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
            service.deleteMenuItem(id);
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
