package com.FoodExpress.Service;

import com.FoodExpress.dto.RestaurantDto;
import com.FoodExpress.model.Address;
import com.FoodExpress.model.Restaurant;
import com.FoodExpress.model.Users;
import com.FoodExpress.repository.AddressRepository;
import com.FoodExpress.repository.RestaurantRepository;
import com.FoodExpress.response.CreateRestaurantRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RestaurantServiceImp implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserService userService;

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest req, Users users) {

        Address address = addressRepository.save(req.getAddress());
        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(address);
        restaurant.setContactInformation(req.getContactInformation());
        restaurant.setCuisineType(req.getCuisineType());
        restaurant.setDescription(req.getDescription());
        restaurant.setImages(req.getImages());
        restaurant.setName(req.getName());
        restaurant.setOpeningHours(req.getOpninghours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(users);



        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception {
        return null;
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {

    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        return null;
    }

    @Override
    public List<Restaurant> searchRestaurant() {
        return null;
    }

    @Override
    public Restaurant findRestaurantById(Long id) throws Exception {
        return null;
    }

    @Override
    public Restaurant getRestaurantByUserId(Long userId) throws Exception {
        return null;
    }

    @Override
    public RestaurantDto addToFavourites(Long restaurantId, Users users) throws Exception {
        return null;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long id) throws Exception {
        return null;
    }
}
