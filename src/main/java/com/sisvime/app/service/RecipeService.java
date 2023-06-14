package com.sisvime.app.service;

import com.sisvime.app.entity.Recipe;
import com.sisvime.app.repository.IRecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Optional;

public class RecipeService {
    @Autowired
    private IRecipeRepository recipeRepository;

    public Recipe create(Recipe Recipe) {
        return recipeRepository.save(Recipe);
    }

    public Optional<Recipe> getById(Long id) {
        return recipeRepository.findById(id);
    }

    public ArrayList<Recipe> getAll() {
        return (ArrayList<Recipe>) recipeRepository.findAll();
    }
}
