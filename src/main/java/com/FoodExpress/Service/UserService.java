package com.FoodExpress.Service;

import com.FoodExpress.model.Users;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

public interface UserService {
    public Users findUserByJwtToken(String jwt) throws Exception;

    public Users findUserByEmail(String email) throws Exception;

}
