package com.FoodExpress.Service;

import com.FoodExpress.Config.JwtProvider;
import com.FoodExpress.model.Users;
import com.FoodExpress.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;
    @Override
    public Users findUserByJwtToken(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        Users users = findUserByEmail(email);

        return users;
    }

    @Override
    public Users findUserByEmail(String email) throws Exception {
        Users users = userRepository.findByEmail(email);

        if (users == null){
            throw new Exception("user not found");
        }
        return users;
    }
}
