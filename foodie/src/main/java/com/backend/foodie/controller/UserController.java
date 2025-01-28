package com.backend.foodie.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
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

import com.backend.foodie.entity.Users;
import com.backend.foodie.service.UserService;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService service;
	
	@GetMapping("/{username}")
	public ResponseEntity<Object> getByUsername(@PathVariable String username){
		try {
			Users user = service.getUserByName(username);
			return ResponseEntity.ok(user);
		} catch(EntityNotFoundException e) {
			Map<String, String> errorMap = new HashMap<>();
			errorMap.put("status", "404");
			errorMap.put("error", e.getMessage());
			return ResponseEntity.badRequest().body(errorMap);
		}
	}
	
	@PostMapping()
	public ResponseEntity<Object> addNewUser(@RequestBody Users newUser){
		try {
			Users user = service.addUser(newUser);
			return ResponseEntity.ok(user);
		} catch(EntityExistsException e) {
			Map<String, String> errorMap = new HashMap<>();
			errorMap.put("error", e.getMessage());
			return ResponseEntity.badRequest().body(errorMap);
		}
	}

	@PutMapping()
	public ResponseEntity<Object> updateUser(@RequestBody Users newUser){
		try {
			Users user = service.updateUser(newUser);
			return ResponseEntity.ok(user);
		} catch(EntityExistsException e) {
			Map<String, String> errorMap = new HashMap<>();
			errorMap.put("error", e.getMessage());
			return ResponseEntity.badRequest().body(errorMap);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable int id){
		Map<String, String> map = new HashMap<>();
        try {
            service.deleteUser(id);
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
