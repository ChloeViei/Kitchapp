package com.chloeviei.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chloeviei.spring.model.Recipe;
import com.chloeviei.spring.repository.RecipeRepository;

@RestController
@RequestMapping("/api/v1")
public class RecipeController {
    
    @Autowired
    private RecipeRepository recipeRepository;

    // get all recipes
    @GetMapping("/recipes")
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }
}
