package com.FoodExpress.Controller;

import com.FoodExpress.Service.CategoryService;
import com.FoodExpress.Service.UserService;
import com.FoodExpress.model.Category;
import com.FoodExpress.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;

    @PostMapping("/admin/category")
    public ResponseEntity<Category> createCategory(@RequestBody Category category,
                                                   @RequestHeader("Authorization") String jwt) throws Exception {
        Users users = userService.findUserByJwtToken(jwt);

        Category createdCategory = categoryService.createCategory(category.getName(), users.getId());

        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @GetMapping("/category/restaurant")
    public ResponseEntity<List<Category>> getRestaurantCategory(
                                                                @RequestHeader("Authorization") String jwt) throws Exception {
        Users users = userService.findUserByJwtToken(jwt);

        List<Category> categories = categoryService.findCategoryByRestaurantId(users.getId());

        return new ResponseEntity<>(categories, HttpStatus.CREATED);
    }
}
