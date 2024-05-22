package com.FoodExpress.Service;

import com.FoodExpress.model.Order;
import com.FoodExpress.model.Users;
import com.FoodExpress.request.OrderRequest;

import java.util.List;

public interface OrderService {
    public Order createOrder(OrderRequest order, Users users) throws Exception;
    public Order updateOrder(Long orderId,String orderStatus)throws Exception;
    public  void cancelOrder(Long orderId)throws Exception;
    public List<Order> getUsersOrder(Long userId)throws Exception;
    public List<Order> getRestaurantOrder(Long restaurantId,String orderStatus)throws Exception;

}
