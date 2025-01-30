package com.backend.foodie.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.foodie.entity.MenuItems;

public interface MenuItemsRepo extends JpaRepository<MenuItems, Integer>{

}
