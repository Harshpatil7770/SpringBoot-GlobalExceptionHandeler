package com.crud.ecart.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.crud.ecart.model.Category;
import com.crud.ecart.service.CategoryService;

@ExtendWith(MockitoExtension.class) // It provied mock objects
class CategoryControllerTest {

	@Mock // @Mock used to mock the objects
	CategoryService categoryService;

	// @InjectMocks annotations used to create instance of class and and mock object
	@InjectMocks // for you
	CategoryController categoryController;

	private Category category1;
	private Category category2;

	@BeforeEach
	public void beforeEach() {
		category1 = new Category();
		category1.setCategoryId(101);
		category1.setCategoryName("SmartPhones");
		category2 = new Category();
		category2.setCategoryId(102);
		category2.setCategoryName("Laptops");
	}

	@Test
	public void addNewCategory() {
		when(categoryService.addNewCategoey(category1)).thenReturn(category1);
		Category result = categoryService.addNewCategoey(category1);
		assertNotNull(result);
		assertEquals(category1, categoryController.addNewCategory(category1));
	}

	@Test
	public void updateCategory() {
		Optional<Category> existingCat = Optional.of(category1);
		when(categoryService.findById(101)).thenReturn(existingCat);
		Optional<Category> result = categoryService.findById(101);
		assertNotNull(result);
		category1.setCategoryName("Medical");
		when(categoryService.updateCategory(category1)).thenReturn(category1);
		assertEquals(category1, categoryController.updateCategory(category1));
	}

	@Test
	public void findById() {
		Optional<Category> existingCat = Optional.of(category1);
		when(categoryService.findById(101)).thenReturn(existingCat);
		Optional<Category> result = categoryService.findById(101);
		assertNotNull(result);
		when(categoryService.findById(101)).thenReturn(result);
		assertEquals(result, categoryController.findById(101));
	}

	@Test
	public void addNewListCategories() {
		List<Category> catLists = new ArrayList<Category>();
		catLists.add(category1);
		catLists.add(category2);
		when(categoryService.addListOfCategories(catLists)).thenReturn(catLists);
		assertEquals(catLists, categoryController.addNewListCategories(catLists));
	}

	@Test
	public void updateListOfCategory() {
		List<Category> updatedLists = new ArrayList<Category>();
		Optional<Category> exitCategory1 = Optional.of(category1);
		when(categoryService.findById(101)).thenReturn(exitCategory1);
		Optional<Category> result = categoryService.findById(101);
		assertNotNull(result);
		category1.setCategoryName("Mens Wear");
		updatedLists.add(category1);

		Optional<Category> exitCategory2 = Optional.of(category2);
		when(categoryService.findById(102)).thenReturn(exitCategory2);
		Optional<Category> result1 = categoryService.findById(102);
		assertNotNull(result1);
		category2.setCategoryName("Ladies Wear");
		updatedLists.add(category2);
		when(categoryService.updateListOfCategories(updatedLists)).thenReturn(updatedLists);
		assertEquals(updatedLists, categoryController.updateListOfCategory(updatedLists));
	}

	@Test
	public void deleteCategory() {

		when(categoryService.deleteCategory(101)).thenReturn("Deleted Category Succesfully !");
		String result = categoryService.deleteCategory(101);
		assertEquals(result, categoryController.deleteCategory(101));
	}

	@Test
	public void fetchAll() {
		List<Category> catLists = new ArrayList<Category>();
		catLists.add(category1);
		catLists.add(category2);
		when(categoryService.fetchAll()).thenReturn(catLists);
		assertEquals(2, categoryController.fetchAll().size());

	}
}
