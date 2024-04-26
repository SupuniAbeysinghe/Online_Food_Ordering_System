package com.FoodExpress.Controller;

import com.FoodExpress.Config.JwtProvider;
import com.FoodExpress.Service.CustomerUserDetailsService;
import com.FoodExpress.model.Cart;
import com.FoodExpress.model.Users;
import com.FoodExpress.repository.CartRepository;
import com.FoodExpress.repository.UserRepository;
import com.FoodExpress.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;
    @Autowired
    private CartRepository cartRepository;

    public ResponseEntity<AuthResponse>createUserHandler(@RequestBody Users users) throws Exception {
        Users isEmailExist = userRepository.findByEmail(users.getEmail());
        if(isEmailExist != null){
            throw new Exception("Email is already used with another account");

        }
        Users createdUser = new Users();
        createdUser.setEmail(users.getEmail());
        createdUser.setFullName(users.getFullName());
        createdUser.setRole(users.getRole());
        createdUser.setPassword(passwordEncoder.encode(users.getPassword()));

        Users savedUser = userRepository.save(createdUser);

        Cart cart = new Cart();
        cart.setCustomer(savedUser);
        cartRepository.save(cart);

        Authentication authentication = new UsernamePasswordAuthenticationToken(users.getEmail(),users.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Register success");
        authResponse.setRole(savedUser.getRole());




        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);


    }

}
