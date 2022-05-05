package com.crud.ecart.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.ecart.model.Category;
import com.crud.ecart.service.CategoryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/category")
@Slf4j
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@PostMapping("/save")
	public Category addNewCategory(@RequestBody Category category) {
		return categoryService.addNewCategoey(category);
	}

	@PutMapping("/update")
	public Category updateCategory(@RequestBody Category category) {
		return categoryService.updateCategory(category);
	}

	@GetMapping("/{productId}")
	public Optional<Category> findById(@PathVariable int productId) {
		return categoryService.findById(productId);
	}

	@PostMapping("/saveAll")
	public List<Category> addNewListCategories(@RequestBody List<Category> category) {
		String method = "addNewListCategories()";
		log.info(method + " called");
		return categoryService.addListOfCategories(category);
	}

	@PutMapping("/updateAll")
	public List<Category> updateListOfCategory(@RequestBody List<Category> category) {
		List<Category> updatedCategory = categoryService.updateListOfCategories(category);
		String methodName = "updateListOfCategory()";
		log.info(methodName + " called");
		return updatedCategory;

	}

	@DeleteMapping("/{categoryId}")
	public String deleteCategory(@PathVariable int categoryId) {
		String methodName = "deleteCategory()";
		log.info(methodName + " called");
		String result = categoryService.deleteCategory(categoryId);
		return "Deleted Category Succesfully !";

	}
	
	@GetMapping("/findAll")
	public List<Category> fetchAll(){
		String method="fetchAll ()";
		log.info(method+" called");
		return categoryService.fetchAll();
	}
	
	
}
