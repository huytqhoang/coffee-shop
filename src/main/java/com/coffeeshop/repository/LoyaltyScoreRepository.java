package com.coffeeshop.repository;

import com.coffeeshop.model.LoyaltyScore;
import com.coffeeshop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoyaltyScoreRepository extends JpaRepository<LoyaltyScore, Long> {
    
}
