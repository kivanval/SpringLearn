package com.example.springlearn.data;

import com.example.springlearn.domain.Ingredient;

import java.util.Collection;
import java.util.Optional;

public interface IngredientRepository {

    Collection<Ingredient> findAll();

    Optional<Ingredient> findById(String id);

    Ingredient save(Ingredient ingredient);
    
}
