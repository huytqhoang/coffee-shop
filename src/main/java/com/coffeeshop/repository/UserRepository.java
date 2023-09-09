package com.coffeeshop.repository;

import com.coffeeshop.model.Shop;
import com.coffeeshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
