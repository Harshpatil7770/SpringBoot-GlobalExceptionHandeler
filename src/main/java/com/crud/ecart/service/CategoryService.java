package com.crud.ecart.service;

import java.util.List;
import java.util.Optional;

import com.crud.ecart.model.Category;

public interface CategoryService {

	Category addNewCategoey(Category category);
	
	Category updateCategory(Category category);
	
	Optional<Category> findById(int categoryId);
	
	List<Category> addListOfCategories(List<Category> category);
	
	List<Category> updateListOfCategories(List<Category> category);
	
	String deleteCategory(int categoryId);
	
	List<Category> fetchAll();
}
