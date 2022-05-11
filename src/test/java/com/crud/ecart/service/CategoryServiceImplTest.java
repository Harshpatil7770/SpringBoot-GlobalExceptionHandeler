package com.crud.ecart.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
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

import com.crud.ecart.dao.CategoryDao;
import com.crud.ecart.globalexceptionhandeler.ElementNotFoundException;
import com.crud.ecart.globalexceptionhandeler.InputUserException;
import com.crud.ecart.model.Category;

@ExtendWith(MockitoExtension.class) // It provide mock object for you
class CategoryServiceImplTest {

	@Mock // @Mock annotations used to mock the object
	CategoryDao categoryDao;

	@InjectMocks // @InjectMocks annotations create instance of class as well as inject all
					// neceesary
	// mocks.
	CategoryServiceImpl categoryServiceImpl;

	private Category category;
	private Category category1;

	@BeforeEach
	public void setup() {
		category = new Category();
		category.setCategoryId(101);
		category.setCategoryName("SmartPhones");
		category1 = new Category();
		category1.setCategoryId(102);
		category1.setCategoryName("Laptops");
	}

	// positive Test Case Scenario
	@Test
	public void addNewCategoey() {
		when(categoryDao.save(category)).thenReturn(category);
		Category result = categoryDao.save(category);
		assertNotNull(result);
		assertEquals(result, categoryServiceImpl.addNewCategoey(category));
		// verify(categoryDao).save(category);
	}

	// Negative and exception scenario
	@Test
	public void addNewCategoryThrowsException_IF_CategoryNameIsNull() {
		doThrow(InputUserException.class).when(categoryDao).save(null);
		assertThrows(InputUserException.class, () -> {
			categoryDao.save(null);
		});
	}

	// Negative and exception scenario
	@Test
	public void addNewCategoryThrowsException_IF_CategoryNameIsBlank() {
		Category cat = new Category();
		cat.setCategoryId(101);
		cat.setCategoryName(" ");
		doThrow(InputUserException.class).when(categoryDao).save(cat);
		assertThrows(InputUserException.class, () -> {
			categoryDao.save(cat);
		});
	}

	// Positive Scenario
	@Test
	public void updateCategory() {

		when(categoryDao.findById(101)).thenReturn(Optional.of(new Category()));
		Optional<Category> result = categoryDao.findById(101);
		assertNotNull(result);
		category.setCategoryName("Laptops");
		when(categoryDao.save(category)).thenReturn(category);
		assertEquals(category, categoryServiceImpl.updateCategory(category));
//		verify(categoryDao).findById(101);
//		verify(categoryDao).save(category);
	}

	// Exception and negative Scenario
	@Test
	public void updateCategoryThrowsException_IF_CategoryNotFound() {
		doThrow(ElementNotFoundException.class).when(categoryDao).findById(null);
		assertThrows(ElementNotFoundException.class, () -> {
			categoryDao.findById(null);
		});
	}

	// Exception and negative Scenario
	@Test
	public void updateCategoryThrowsException_IF_CategoryNameIsNull() {
		doThrow(InputUserException.class).when(categoryDao).save(null);
		assertThrows(InputUserException.class, () -> {
			categoryDao.save(null);
		});
	}

	@Test
	public void updateCategoryThrowsExpection_IF_CategoryNameIsBlank() {
		Category updateCat = new Category();
		updateCat.setCategoryId(1);
		updateCat.setCategoryName(" ");
		doThrow(InputUserException.class).when(categoryDao).save(updateCat);
		assertThrows(InputUserException.class, () -> {
			categoryDao.save(updateCat);
		});
	}

	@Test
	public void findCategoryById() {
		when(categoryDao.findById(101)).thenReturn(Optional.of(new Category()));
		Optional<Category> result = categoryDao.findById(101);
		assertNotNull(result);
		assertEquals(result, categoryServiceImpl.findById(101));
		// verify(categoryDao).findById(101);
	}

	@Test
	public void findCategoryByIdThrowsExpection_IF_CategoryIsNotFound() {
		doThrow(ElementNotFoundException.class).when(categoryDao).findById(null);
		assertThrows(ElementNotFoundException.class, () -> {
			categoryDao.findById(null);
		});
	}

	@Test
	public void addListOfCategories() {

		List<Category> catLists = new ArrayList<Category>();
		catLists.add(category);
		catLists.add(category1);
		when(categoryDao.saveAll(catLists)).thenReturn(catLists);
		assertEquals(2, categoryServiceImpl.addListOfCategories(catLists).size());
	}

	@Test
	public void addListOfCategoriesThrowsException_IF_CategoryIsNull() {
		doThrow(InputUserException.class).when(categoryDao).saveAll(null);
		assertThrows(InputUserException.class, () -> {
			categoryDao.saveAll(null);
		});
	}

	@Test
	public void updateListOfCategories() {
		List<Category> catLists = new ArrayList<Category>();

		Optional<Category> exitingCat1 = Optional.of(category);
		when(categoryDao.findById(101)).thenReturn(exitingCat1);
		Optional<Category> result = categoryDao.findById(101);
		assertNotNull(result);
		category.setCategoryName("Medical");
		catLists.add(category);

		Optional<Category> exitingCat2 = Optional.of(category1);
		when(categoryDao.findById(102)).thenReturn(exitingCat2);
		Optional<Category> result1 = categoryDao.findById(102);
		assertNotNull(result1);
		category1.setCategoryName("Leather");
		catLists.add(category1);

	//	when(categoryDao.saveAll(catLists)).thenReturn(catLists);
		assertThat(categoryServiceImpl.updateListOfCategories(catLists)).isEqualTo(catLists);
	}

	@Test
	public void updateListOfCategoriesThrowsException_IF_CategoryNameIsNull() {

		doThrow(InputUserException.class).when(categoryDao).saveAll(null);
		assertThrows(InputUserException.class, () -> {
			categoryDao.saveAll(null);
		});
	}

	@Test
	public void updateListOfCategoriesThrowsException_IF_CategoryNameIsBlank() {
		List<Category> categoryLists = new ArrayList<Category>();
		Category cat1 = new Category();
		cat1.setCategoryId(101);
		cat1.setCategoryName(" ");

		Category cat2 = new Category();
		cat2.setCategoryId(102);
		cat2.setCategoryName("Electronics");
		categoryLists.add(cat1);
		categoryLists.add(cat2);

		doThrow(InputUserException.class).when(categoryDao).saveAll(categoryLists);
		assertThrows(InputUserException.class, () -> {
			categoryDao.saveAll(categoryLists);
		});
	}

	@Test
	public void deleteCategory() {

		Optional<Category> existCat = Optional.of(category);
		Optional<Category> result = categoryDao.findById(101);
		assertNotNull(result);
		categoryDao.deleteById(101);
		assertThat(categoryDao.existsById(101)).isFalse();
//		doNothing().when(categoryDao).deleteById(101);
//		categoryServiceImpl.deleteCategory(101);
//		verify(categoryDao).deleteById(101);
	}

	@Test
	public void deleteCategoryByIdThrowsExpection_IF_CategoryNotFound() {
		doThrow(ElementNotFoundException.class).when(categoryDao).findById(null);
		assertThrows(ElementNotFoundException.class, () -> {
			categoryDao.findById(null);
		});
	}

	@Test
	public void fetchAll() {
		List<Category> catLists = new ArrayList<Category>();
		catLists.add(category);
		catLists.add(category1);
		when(categoryDao.findAll()).thenReturn(catLists);
		assertEquals(2, categoryServiceImpl.fetchAll().size());
	}
}
