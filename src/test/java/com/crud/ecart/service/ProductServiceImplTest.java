package com.crud.ecart.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
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

import com.crud.ecart.dao.ProductDao;
import com.crud.ecart.globalexceptionhandeler.ElementNotFoundException;
import com.crud.ecart.globalexceptionhandeler.InputUserException;
import com.crud.ecart.globalexceptionhandeler.NegativeValueExpection;
import com.crud.ecart.model.Brand;
import com.crud.ecart.model.Category;
import com.crud.ecart.model.Product;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

	@Mock
	ProductDao productDao;

	@InjectMocks
	ProductServiceImpl productServiceImpl;

	private Product product;
	private Brand brand;
	private Category category;

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

	}

	@Test
	public void addNewProduct() {
		when(productDao.save(product)).thenReturn(product);
		Product result = productDao.save(product);
		assertNotNull(result);
		assertEquals(product, productServiceImpl.addNewProduct(product));
	}

	@Test
	public void addNewProductThrowsException_IF_ProductNameIsNull() {
		doThrow(InputUserException.class).when(productDao).save(null);
		assertThrows(InputUserException.class, () -> {
			productDao.save(null);
		});
	}

	@Test
	public void addNewProductThrowsException_IF_ProductNameIsBlank() {
		Product product1 = new Product();
		product1.setProductId(101);
		product1.setProductName(" ");
		doThrow(InputUserException.class).when(productDao).save(product1);
		assertThrows(InputUserException.class, () -> {
			productDao.save(product1);
		});
	}

	@Test
	public void addNewProductThrowsException_IF_ProductDescriptionIsNull() {
		doThrow(InputUserException.class).when(productDao).save(null);
		assertThrows(InputUserException.class, () -> {
			productDao.save(null);
		});
	}

	@Test
	public void addNewProductThrowsException_IF_ProductDescriptionIsBlank() {
		Product product1 = new Product();
		product1.setProductId(102);
		product1.setProductName("Oppo F1f");
		product1.setDescription(" ");
		doThrow(InputUserException.class).when(productDao).save(product1);
		assertThrows(InputUserException.class, () -> {
			productDao.save(product1);
		});
	}

	@Test
	public void addNewProductThrowsException_IF_BrandIsNegative() {
		Brand brand1 = new Brand();
		brand1.setBrandId(-1);
		Product prod = new Product();
		prod.setBrand(brand1);

		doThrow(NegativeValueExpection.class).when(productDao).save(prod);
		assertThrows(NegativeValueExpection.class, () -> {
			productDao.save(prod);
		});
	}

	@Test
	public void addNewProductThrowsException_IF_CategoryIsNegative() {
		Category category1 = new Category();
		category1.setCategoryId(-1);
		Product prod = new Product();
		prod.setCategory(category1);
		doThrow(NegativeValueExpection.class).when(productDao).save(prod);
		assertThrows(NegativeValueExpection.class, () -> {
			productDao.save(prod);
		});
	}

	@Test
	public void addNewListOfProduct() {
		List<Product> prodLists = new ArrayList<Product>();
		prodLists.add(product);
		Product product1 = new Product();
		product1.setProductId(101);
		product1.setProductName("I Phone XR");
		product1.setPrice(38000);
		product1.setDescription("Looks Good");
		Brand brand1 = new Brand();
		brand1.setBrandId(1);
		brand1.setBrandName("I Phones");
		Category category1 = new Category();
		category1.setCategoryId(1);
		category1.setCategoryName("SmartPhones");
		product1.setBrand(brand1);
		product1.setCategory(category1);
		prodLists.add(product1);

		when(productDao.saveAll(prodLists)).thenReturn(prodLists);
		assertEquals(prodLists, productServiceImpl.addNewListOfProduct(prodLists));
	}

	@Test
	public void addNewListOfProductThrowsException_IF_ProductNameIsNull() {
		Product product1 = new Product();
		product1.setProductId(101);
		product1.setProductName(null);
		doThrow(InputUserException.class).when(productDao).save(product1);
		assertThrows(InputUserException.class, () -> {
			productDao.save(product1);
		});
	}

	@Test
	public void addNewListOfProductThrowsException_IF_ProductNameIsBlank() {
		Product product1 = new Product();
		product1.setProductId(101);
		product1.setProductName(" ");
		doThrow(InputUserException.class).when(productDao).save(product1);
		assertThrows(InputUserException.class, () -> {
			productDao.save(product1);
		});
	}

	@Test
	public void addNewListOfProductThrowsException_IF_ProductDescriptionIsEmpty() {
		Product product1 = new Product();
		product1.setProductId(101);
		product1.setProductName("Oppo F1 f");
		product1.setDescription(null);
		doThrow(InputUserException.class).when(productDao).save(product1);
		assertThrows(InputUserException.class, () -> {
			productDao.save(product1);
		});
	}

	@Test
	public void addNewListOfProductThrowsException_IF_ProductDescriptionIsBlank() {
		Product product1 = new Product();
		product1.setProductId(101);
		product1.setProductName("Oppo F1 f");
		product1.setDescription("    ");
		doThrow(InputUserException.class).when(productDao).save(product1);
		assertThrows(InputUserException.class, () -> {
			productDao.save(product1);
		});
	}

	@Test
	public void addNewListOfProductThrowsException_IF_BrandIsNegative() {
		Brand brand1 = new Brand();
		brand1.setBrandId(-1);
		Product prod = new Product();
		prod.setBrand(brand1);

		doThrow(NegativeValueExpection.class).when(productDao).save(prod);
		assertThrows(NegativeValueExpection.class, () -> {
			productDao.save(prod);
		});
	}

	@Test
	public void addNewListOfProductThrowsException_IF_CategoryIsNegative() {
		Category category1 = new Category();
		category1.setCategoryId(-1);
		Product prod = new Product();
		prod.setCategory(category1);
		doThrow(NegativeValueExpection.class).when(productDao).save(prod);
		assertThrows(NegativeValueExpection.class, () -> {
			productDao.save(prod);
		});
	}

	@Test
	public void updateProduct() {
		Optional<Product> exitCat = Optional.of(product);
		when(productDao.findById(101)).thenReturn(exitCat);
		Optional<Product> result = productDao.findById(101);
		assertNotNull(result);
		product.setProductName("Oppo Reno");
		product.setDescription("Curved Edges");
		product.setPrice(38000);
		when(productDao.save(product)).thenReturn(product);
		assertEquals(product, productServiceImpl.updateProduct(product));
	}

	@Test
	public void updateProductThrowsExpection_IF_ProductIsNotFound() {
		doThrow(ElementNotFoundException.class).when(productDao).findById(null);
		assertThrows(ElementNotFoundException.class, () -> {
			productDao.findById(null);
		});
	}

	@Test
	public void updateProductThrowsExpection_IF_ProductNameIsBlank() {
		Product product1 = new Product();
		product1.setProductId(101);
		product1.setProductName(" ");
		doThrow(InputUserException.class).when(productDao).save(product1);
		assertThrows(InputUserException.class, () -> {
			productDao.save(product1);
		});
	}

	@Test
	public void updateProductThrowsExpection_IF_ProductNameIsNull() {
		Product product1 = new Product();
		product1.setProductId(101);
		product1.setProductName(null);
		doThrow(InputUserException.class).when(productDao).save(product1);
		assertThrows(InputUserException.class, () -> {
			productDao.save(product1);
		});
	}

	@Test
	public void updateProductThrowsExpection_IF_BrandIsNegative() {
		Brand brand1 = new Brand();
		brand1.setBrandId(-1);
		Product prod = new Product();
		prod.setBrand(brand1);

		doThrow(NegativeValueExpection.class).when(productDao).save(prod);
		assertThrows(NegativeValueExpection.class, () -> {
			productDao.save(prod);
		});
	}

	@Test
	public void updateProductThrowsExpection_IF_CategoryIsNegative() {
		Category category1 = new Category();
		category1.setCategoryId(-1);
		Product prod = new Product();
		prod.setCategory(category1);
		doThrow(NegativeValueExpection.class).when(productDao).save(prod);
		assertThrows(NegativeValueExpection.class, () -> {
			productDao.save(prod);
		});
	}

	@Test
	public void updateListOfProducts() {
		List<Product> updateProductList = new ArrayList<Product>();
		Optional<Product> existingProduct = Optional.of(product);
		when(productDao.findById(101)).thenReturn(existingProduct);
		Optional<Product> result = productDao.findById(101);
		assertNotNull(result);
		product.setProductName("Lenovo Ideapad");
		updateProductList.add(product);

		Product product1 = new Product();
		product1.setProductId(102);
		product1.setProductName("Dell Latitude");
		Optional<Product> existingProduct1 = Optional.of(product1);
		when(productDao.findById(102)).thenReturn(existingProduct1);
		Optional<Product> result1 = productDao.findById(102);
		assertNotNull(result1);
		product.setProductName("Lenovo ThinkPad");
		updateProductList.add(product);

		assertThat(productServiceImpl.updateListOfProducts(updateProductList)).isEqualTo(updateProductList);

	}

	@Test
	public void updateListOfProductsThrowsException_IF_ProductNameIsNull() {
		Product product1 = new Product();
		product1.setProductId(101);
		product1.setProductName(null);
		doThrow(InputUserException.class).when(productDao).save(product1);
		assertThrows(InputUserException.class, () -> {
			productDao.save(product1);
		});

	}

	@Test
	public void updateListOfProductsThrowsException_IF_ProductNameIsBlank() {
		Product product1 = new Product();
		product1.setProductId(101);
		product1.setProductName("  ");
		doThrow(InputUserException.class).when(productDao).save(product1);
		assertThrows(InputUserException.class, () -> {
			productDao.save(product1);
		});

	}

	@Test
	public void updateListOfProductsThrowsException_IF_ProductDescriptionIsNull() {
		Product product1 = new Product();
		product1.setProductId(101);
		product1.setProductName("Lenovo Idepad");
		product1.setDescription(null);
		doThrow(InputUserException.class).when(productDao).save(product1);
		assertThrows(InputUserException.class, () -> {
			productDao.save(product1);
		});

	}

	@Test
	public void updateListOfProductsThrowsException_IF_ProductDescriptionIsBlank() {
		Product product1 = new Product();
		product1.setProductId(101);
		product1.setProductName("Lenovo Ideapad");
		product1.setProductName("  ");
		doThrow(InputUserException.class).when(productDao).save(product1);
		assertThrows(InputUserException.class, () -> {
			productDao.save(product1);
		});

	}

	@Test
	public void updateListOfProductsThrowsException_IF_BrandIsNegative() {
		Brand brand1 = new Brand();
		brand1.setBrandId(-1);
		Product prod = new Product();
		prod.setBrand(brand1);

		doThrow(NegativeValueExpection.class).when(productDao).save(prod);
		assertThrows(NegativeValueExpection.class, () -> {
			productDao.save(prod);
		});
	}

	@Test
	public void updateListOfProductsThrowsException_IF_CategoryIsNegative() {
		Category category1 = new Category();
		category1.setCategoryId(-1);
		Product prod = new Product();
		prod.setCategory(category1);
		doThrow(NegativeValueExpection.class).when(productDao).save(prod);
		assertThrows(NegativeValueExpection.class, () -> {
			productDao.save(prod);
		});
	}

	@Test
	public void fetchById() {
		Optional<Product> existingProd = Optional.of(product);
		when(productDao.findById(101)).thenReturn(existingProd);
		Optional<Product> result = productDao.findById(101);
		assertNotNull(result);
		assertEquals(result, productServiceImpl.fetchById(101));
	}

	@Test
	public void fetchByIdThrowsExpection_IF_ProductNotFound() {
		doThrow(ElementNotFoundException.class).when(productDao).findById(null);
		assertThrows(ElementNotFoundException.class, () -> {
			productDao.findById(null);
		});
	}

	@Test
	public void fetchAllProducts() {
		List<Product> prodLists = new ArrayList<Product>();
		prodLists.add(product);
		Product product1 = new Product();
		product1 = new Product();
		product1.setProductId(101);
		product1.setProductName("I Phone XR");
		product1.setPrice(38000);
		product1.setDescription("Looks Good");
		Brand brand1 = new Brand();
		brand1.setBrandId(1);
		brand1.setBrandName("I Phones");
		Category category1 = new Category();
		category1.setCategoryId(1);
		category1.setCategoryName("SmartPhones");
		product1.setBrand(brand1);
		product1.setCategory(category1);
		prodLists.add(product1);
		when(productDao.findAll()).thenReturn(prodLists);
		assertEquals(2, productServiceImpl.fetchAllProducts().size());
		assertEquals(prodLists, productServiceImpl.fetchAllProducts());
	}

	@Test
	public void deleteProduct() {
		Optional<Product> existingProd = Optional.of(product);
		when(productDao.findById(101)).thenReturn(existingProd);
		Optional<Product> result = productDao.findById(101);
		assertNotNull(result);
		productDao.deleteById(101);
		assertThat(productDao.existsById(101)).isFalse();
	}

	@Test
	public void deleteProductThrowsExpection_IF_ProductNotFound() {
		doThrow(ElementNotFoundException.class).when(productDao).deleteById(null);
		assertThrows(ElementNotFoundException.class, () -> {
			productDao.deleteById(null);
		});
	}

	@Test
	public void findByBrand() {
		List<Product> prodLists = new ArrayList<Product>();
		prodLists.add(product);
		Product product1 = new Product();
		product1 = new Product();
		product1.setProductId(101);
		product1.setProductName("I Phone XR");
		product1.setPrice(38000);
		product1.setDescription("Looks Good");
		Brand brand1 = new Brand();
		brand1.setBrandId(1);
		brand1.setBrandName("I Phones");
		Category category1 = new Category();
		category1.setCategoryId(1);
		category1.setCategoryName("SmartPhones");
		product1.setBrand(brand1);
		product1.setCategory(category1);
		prodLists.add(product1);

		when(productDao.findByName("I Phones")).thenReturn(prodLists);
		assertEquals(prodLists, productServiceImpl.findByBrand("I Phones"));
	}

	@Test
	public void findByCategoryName() {
		List<Product> prodLists = new ArrayList<Product>();
		prodLists.add(product);

		Product product1 = new Product();
		product1 = new Product();
		product1.setProductId(101);
		product1.setProductName("I Phone XR");
		product1.setPrice(38000);
		product1.setDescription("Looks Good");
		Brand brand1 = new Brand();
		brand1.setBrandId(1);
		brand1.setBrandName("I Phones");
		Category category1 = new Category();
		category1.setCategoryId(1);
		category1.setCategoryName("SmartPhones");
		product1.setBrand(brand1);
		product1.setCategory(category1);
		prodLists.add(product1);

		when(productDao.findByCatgoryName("SmartPhones")).thenReturn(prodLists);
		assertEquals(prodLists, productServiceImpl.findByCategoryName("SmartPhones"));
	}
}
