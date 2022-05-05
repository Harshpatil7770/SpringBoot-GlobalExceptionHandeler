package com.crud.ecart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.ecart.dao.CategoryDao;
import com.crud.ecart.globalexceptionhandeler.ElementNotFoundException;
import com.crud.ecart.model.Category;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDao categoryDao;

	@Override
	public Category addNewCategoey(Category category) {

		if (category.getCategoryName().isEmpty() || category.getCategoryName().length() == 0) {
			throw new NoSuchElementException();
		}

		Category newCategory = categoryDao.save(category);
		return newCategory;

	}

	@Override
	public Category updateCategory(Category category) {
		if (category.getCategoryName().isEmpty() || category.getCategoryName().length() == 0) {
			throw new NoSuchElementException();
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
		List<Category> catsLists = null;
		for (Category catNames : category) {
			if (catNames.getCategoryName().isBlank()) {
				if (catNames.getCategoryName().length() == 0) {

					throw new NoSuchElementException();

				}
			}
		}

		catsLists = new ArrayList<Category>();
		for (Category eachcatNames : category) {

			Optional<Category> exisitingCats = categoryDao.findById(eachcatNames.getCategoryId());
			if (exisitingCats.isPresent()) {
				Category newCategory = exisitingCats.get();
				newCategory.setCategoryName(eachcatNames.getCategoryName());
				categoryDao.save(newCategory);
				catsLists.add(newCategory);
			}
		}
		String methodName = "updateListOfCategories()";
		log.info(methodName + " called");
		return catsLists;

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
