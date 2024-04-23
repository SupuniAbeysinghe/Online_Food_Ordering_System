package com.FoodExpress.model;

import com.FoodExpress.dto.RestaurentDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long id;

    private String fullName;
    private String email;
    private String password;
    private USER_ROLE role;
    @JsonIgnore
    @OneToMany
    private List<Order> orders = new ArrayList<>();

    @ElementCollection
    private List<RestaurentDTO> favoutites = new ArrayList();

}
