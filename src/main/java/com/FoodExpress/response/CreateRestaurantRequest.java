package com.FoodExpress.response;

import com.FoodExpress.model.Address;
import com.FoodExpress.model.ContactInformation;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreateRestaurantRequest {
    private Long id;
    private String name;
    private String description;
    private String cuisineType;
    private Address address;
    private ContactInformation contactInformation;
    private String opninghours;
    private List<String> images;


}
