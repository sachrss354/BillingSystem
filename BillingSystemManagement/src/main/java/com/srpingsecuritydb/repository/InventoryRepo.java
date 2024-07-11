package com.srpingsecuritydb.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srpingsecuritydb.entity.Inventory;
@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Integer> {

	Inventory findByName(String name);

}
