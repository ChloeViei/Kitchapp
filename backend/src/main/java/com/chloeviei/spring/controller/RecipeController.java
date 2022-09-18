package com.chloeviei.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chloeviei.spring.exception.ResourceNotFoundException;
import com.chloeviei.spring.model.Recipe;
import com.chloeviei.spring.repository.RecipeRepository;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    
    @Autowired
    private RecipeRepository recipeRepository;

    // get all recipes
    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    // create recipe
	@PostMapping
	public Recipe createEmployee(@RequestBody Recipe recipe) {
		return recipeRepository.save(recipe);
	}

    // get recipe by id
	@GetMapping("/{id}")
	public ResponseEntity<Recipe> getEmployeeById(@PathVariable Long id) {
		Recipe recipe = recipeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
		return ResponseEntity.ok(recipe);
	}

    // update recipe 
	@PutMapping("/{id}")
	public ResponseEntity<Recipe> updateEmployee(@PathVariable Long id, @RequestBody Recipe recipeDetails){
		Recipe recipe = recipeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
		
		recipe.setTitle(recipeDetails.getTitle());
		recipe.setDescription(recipeDetails.getDescription());
		recipe.setInstruction(recipeDetails.getInstruction());
        recipe.setDifficulty(recipeDetails.getDifficulty());
		
		Recipe updatedRecipe = recipeRepository.save(recipe);
		return ResponseEntity.ok(updatedRecipe);
	}


    // delete recipe
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		Recipe recipe = recipeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Recipe not exist with id :" + id));
		
		recipeRepository.delete(recipe);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
