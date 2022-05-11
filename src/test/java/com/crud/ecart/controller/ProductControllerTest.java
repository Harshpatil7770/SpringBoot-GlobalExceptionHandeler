package com.crud.ecart.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

import com.crud.ecart.model.Brand;
import com.crud.ecart.model.Category;
import com.crud.ecart.model.Product;
import com.crud.ecart.service.ProductService;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

	@Mock
	ProductService productService;

	@InjectMocks
	ProductController productController;

	private Product product;
	private Product product1;
	private Brand brand;
	private Brand brand1;
	private Category category;
	private Category category1;

	@BeforeEach
	public void setup() {
		product = new Product();
		product.setProductId(101);
		product.setProductName("I Phone XR");
		product.setPrice(38000);
		product.setDescription("Looks Good");
		brand = new Brand();
		brand.setBrandId(1);
		brand.setBrandName("I Phones");
		category = new Category();
		category.setCategoryId(1);
		category.setCategoryName("SmartPhones");
		product.setBrand(brand);
		product.setCategory(category);

		product1 = new Product();
		product1.setProductId(101);
		product1.setProductName("I Phone 12");
		product1.setPrice(52000);
		product1.setDescription("Looks Good");
		brand1 = new Brand();
		brand1.setBrandId(1);
		brand1.setBrandName("I Phones");
		category1 = new Category();
		category1.setCategoryId(1);
		category1.setCategoryName("SmartPhones");
		product1.setBrand(brand1);
		product1.setCategory(category1);
	}

	@Test
	public void addNewProduct() {

		when(productService.addNewProduct(product)).thenReturn(product);
		assertEquals(product, productController.addNewProduct(product));
	}

	@Test
	public void addNewListOfProduct() {
		List<Product> prodLists = new ArrayList<Product>();
		prodLists.add(product);
		prodLists.add(product1);
		when(productService.addNewListOfProduct(prodLists)).thenReturn(prodLists);
		assertEquals(2, productController.addNewListOfProduct(prodLists).size());
	}

	@Test
	public void updateProduct() {

		Optional<Product> prod = Optional.of(product);
		when(productService.fetchById(101)).thenReturn(prod);
		Optional<Product> result = productService.fetchById(101);
		assertNotNull(result);
		product.setProductName("I phone 6");
		when(productService.updateProduct(product)).thenReturn(product);
		assertEquals(product, productController.updateProduct(product));
	}

	@Test
	public void updateListOfProduct() {

		List<Product> prodLists = new ArrayList<Product>();

		Optional<Product> existingProduct = Optional.of(product);
		when(productService.fetchById(101)).thenReturn(existingProduct);
		Optional<Product> result = productService.fetchById(101);
		assertNotNull(result);
		product.setProductName("I phone 6");
		prodLists.add(product);

		Optional<Product> existingProduct1 = Optional.of(product1);
		when(productService.fetchById(101)).thenReturn(existingProduct1);
		Optional<Product> result1 = productService.fetchById(101);
		assertNotNull(result);
		product.setProductName("I phone 5s");
		prodLists.add(product);

		when(productService.updateListOfProducts(prodLists)).thenReturn(prodLists);
		assertEquals(prodLists, productController.updateListOfProduct(prodLists));
	}

	@Test
	public void findById() {
		Optional<Product> existingProduct = Optional.of(product);
		when(productService.fetchById(101)).thenReturn(existingProduct);
		Optional<Product> result = productService.fetchById(101);
		assertNotNull(result);
		assertEquals(result, productController.findById(101));
	}

//	@Test
//	public void fetchAllProducts() {
//		List<Product> prodLists = new ArrayList<Product>();
//		prodLists.add(product);
//		prodLists.add(product1);
//		when(productService.fetchAllProducts()).thenReturn(prodLists);
//		assertEquals(prodLists, productController.fetchAllProducts());
//	}

	@Test
	public void deleteProduct() {
		when(productService.deleteProduct(101)).thenReturn(" deleted Product Succesfully !");
		String result = productService.deleteProduct(101);
		assertEquals(result, productController.deleteProduct(101));
	}

	@Test
	public void fetchProductByBrandName() {
		List<Product> prodLists = new ArrayList<Product>();
		when(productService.findByBrand("I Phones")).thenReturn(prodLists);
		List<Product> result = productService.findByBrand("I Phones");
		assertNotNull(result);
		assertEquals(prodLists, productController.fetchProductByBrandName("I Phones"));
	}

	@Test
	public void fetchProductByCategoryName() {
		List<Product> prodLists = new ArrayList<Product>();
		when(productService.findByCategoryName("SmartPhones")).thenReturn(prodLists);
		List<Product> result = productService.findByCategoryName("SmartPhones");
		assertNotNull(result);
		assertEquals(prodLists, productController.fetchProductByCategoryName("SmartPhones"));
	}
}
