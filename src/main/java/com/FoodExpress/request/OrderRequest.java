package com.FoodExpress.request;

import com.FoodExpress.model.Address;
import lombok.Data;

@Data
public class OrderRequest {
    private  Long restaurantId;
    private Address deliveryAddress;
}
