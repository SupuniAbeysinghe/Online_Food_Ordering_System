package com.FoodExpress.repository;

import com.FoodExpress.model.IngredientsItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientItemRepository extends JpaRepository <IngredientsItem,Long>{

}
