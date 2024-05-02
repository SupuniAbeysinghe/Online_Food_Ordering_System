package com.FoodExpress.Controller;

import com.FoodExpress.Service.RestaurantService;
import com.FoodExpress.Service.UserService;
import com.FoodExpress.model.Restaurant;
import com.FoodExpress.model.Users;
import com.FoodExpress.response.CreateRestaurantRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(".api/admin/restaurants")
public class AdminRestaurantController {

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<Restaurant> createRestaurant(
            @RequestBody CreateRestaurantRequest req,
            @RequestHeader("Authorization") String jwt
            ) throws Exception {
        Users users = userService.findUserByJwtToken(jwt);

        Restaurant restaurant = restaurantService.createRestaurant(req,users);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);

    }

}
