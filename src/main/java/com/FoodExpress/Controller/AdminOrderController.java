package com.FoodExpress.Controller;

import com.FoodExpress.Service.OrderService;
import com.FoodExpress.Service.UserService;
import com.FoodExpress.model.Order;
import com.FoodExpress.model.Users;
import com.FoodExpress.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminOrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;


    @GetMapping("/order/restaurant/{id}")
    public ResponseEntity<List<Order>> getOrderHistory(
            @PathVariable Long id,
            @RequestParam (required = false) String orderStatus,
            @RequestHeader("Authorization") String jwt) throws Exception {
        Users users = userService.findUserByJwtToken(jwt);
        List<Order> orders = orderService.getRestaurantOrder(id,orderStatus);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping("/order/{id}/{orderStatus}")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable Long id,
            @PathVariable String orderStatus,
            @RequestHeader("Authorization") String jwt) throws Exception {
        Users users = userService.findUserByJwtToken(jwt);
        Order orders = orderService.updateOrder(id,orderStatus);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
