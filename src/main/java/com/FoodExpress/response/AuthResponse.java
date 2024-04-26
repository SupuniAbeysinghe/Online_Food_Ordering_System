package com.FoodExpress.response;

import com.FoodExpress.model.USER_ROLE;
import com.FoodExpress.model.Users;
import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String message;
    private USER_ROLE role;
}
