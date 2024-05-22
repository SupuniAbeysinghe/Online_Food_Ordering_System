package com.FoodExpress.repository;

import com.FoodExpress.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long>{

    public List<Order> findByCustomerId(Long userId);
    public List<Order>findByRestaurantId(Long restaurantId);

}
