package com.FoodExpress.Service;

import com.FoodExpress.dto.RestaurantDto;
import com.FoodExpress.model.Restaurant;
import com.FoodExpress.model.Users;
import com.FoodExpress.response.CreateRestaurantRequest;
import org.springframework.data.annotation.CreatedBy;

import java.util.List;

public interface RestaurantService {
    public Restaurant createRestaurant(CreateRestaurantRequest req, Users users);

    public Restaurant updateRestaurant(Long restaurantId,CreateRestaurantRequest updatedRestaurant) throws Exception;
    public void deleteRestaurant (Long restaurantId)throws Exception;

    public List<Restaurant> getAllRestaurant();

    public List<Restaurant> searchRestaurant(String keyword);

    public Restaurant findRestaurantById(Long id) throws Exception;

    public Restaurant getRestaurantByUserId(Long userId)throws Exception;

    public RestaurantDto addToFavourites(Long restaurantId,Users users) throws Exception;

    public Restaurant updateRestaurantStatus(Long id)throws Exception;



}
