package com.coffeeshop.repository;

import com.coffeeshop.model.CoffeeMenu;
import com.coffeeshop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeMenuRepository extends JpaRepository<CoffeeMenu, Long> {

}
