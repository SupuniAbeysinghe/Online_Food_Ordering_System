package com.FoodExpress.Controller;

import com.FoodExpress.Service.OrderService;
import com.FoodExpress.Service.UserService;
import com.FoodExpress.model.CartItem;
import com.FoodExpress.model.Order;
import com.FoodExpress.model.Users;
import com.FoodExpress.request.AddCartItemRequest;
import com.FoodExpress.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest req,
                                                  @RequestHeader("Authorization") String jwt) throws Exception {
        Users users = userService.findUserByJwtToken(jwt);
        Order order = orderService.createOrder(req,users);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/order/user")
    public ResponseEntity<List<Order>> getOrderHistory(
                                             @RequestHeader("Authorization") String jwt) throws Exception {
        Users users = userService.findUserByJwtToken(jwt);
        List<Order> orders = orderService.getUsersOrder(users.getId());
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

}
