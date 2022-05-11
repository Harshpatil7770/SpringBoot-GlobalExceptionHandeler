package com.crud.ecart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.ecart.dao.CategoryDao;
import com.crud.ecart.globalexceptionhandeler.ElementNotFoundException;
import com.crud.ecart.globalexceptionhandeler.InputUserException;
import com.crud.ecart.model.Category;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDao categoryDao;

	@Override
	public Category addNewCategoey(Category category) {

		if (category.getCategoryName().isEmpty() || category.getCategoryName().length() == 0
				|| category.getCategoryName().isBlank()) {
			throw new InputUserException();
		}

		Category newCategory = categoryDao.save(category);
		return newCategory;

	}

	@Override
	public Category updateCategory(Category category) {

		Optional<Category> existCategory = categoryDao.findById(category.getCategoryId());
		if (!existCategory.isPresent()) {
			throw new ElementNotFoundException();
		}

		if (category.getCategoryName().isEmpty() || category.getCategoryName().length() == 0
				|| category.getCategoryName().isBlank()) {
			throw new InputUserException();
		}

		Category existingCategory = categoryDao.findById(category.getCategoryId()).orElse(null);
		existingCategory.setCategoryId(category.getCategoryId());
		existingCategory.setCategoryName(category.getCategoryName());
		return categoryDao.save(category);
	}

	@Override
	public Optional<Category> findById(int categoryId) {

		Optional<Category> category = categoryDao.findById(categoryId);
		if (!category.isPresent()) {
			throw new ElementNotFoundException();
		}
		return category;
	}

	@Override
	public List<Category> addListOfCategories(List<Category> category) {

		for (Category catNames : category) {
			if (catNames.getCategoryName().isEmpty()) {
				if (catNames.getCategoryName().length() == 0) {

					throw new NoSuchElementException();

				}
			}
		}

		List<Category> categoryList = categoryDao.saveAll(category);
		String methodName = "addListOfCategories()";
		log.info(methodName + " called");
		return categoryList;
	}

	@Override
	public List<Category> updateListOfCategories(List<Category> category) {
		List<Category> updatedLists = new ArrayList<Category>();
		for (Category existCat : category) {
			Optional<Category> eachCat = categoryDao.findById(existCat.getCategoryId());
			if (!eachCat.isPresent()) {
				throw new ElementNotFoundException();
			}

		}

		for (Category existCat : category) {
			Category updatedCategory = categoryDao.findById(existCat.getCategoryId()).orElse(null);
			updatedCategory.setCategoryId(existCat.getCategoryId());
			if (existCat.getCategoryName().isEmpty() || existCat.getCategoryName().isBlank()
					|| existCat.getCategoryName().length() == 0) {
				throw new InputUserException();
			}
			updatedCategory.setCategoryName(existCat.getCategoryName());
			categoryDao.save(updatedCategory);
			updatedLists.add(existCat);
		}
		return updatedLists;
	}

	@Override
	public String deleteCategory(int categoryId) {
		Optional<Category> existingCatId = categoryDao.findById(categoryId);
		if (!existingCatId.isPresent()) {
			throw new ElementNotFoundException();
		}
		String method = "deleteCategory()";
		log.info(method + " called");
		categoryDao.deleteById(categoryId);
		return "Deleted Category";

	}

	@Override
	public List<Category> fetchAll() {
		List<Category> categoryList = categoryDao.findAll();
		if (categoryList.isEmpty()) {
			throw new ElementNotFoundException();
		}
		String methodName = "fetchAll()";
		log.info(methodName + " called");
		return categoryList;
	}
}
