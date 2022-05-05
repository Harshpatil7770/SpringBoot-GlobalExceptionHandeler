package com.crud.ecart.service;

import java.util.List;
import java.util.Optional;

import com.crud.ecart.model.Product;

public interface ProductService {

	Product addNewProduct(Product product);

	List<Product> addNewListOfProduct(List<Product> product);

	Product updateProduct(Product product);

	List<Product> updateListOfProducts(List<Product> product);

	Optional<Product> fetchById(int productId);

	List<Product> fetchAllProducts();

	String deleteProduct(int productId);

	List<Product> findByBrandName(String brandName);

	List<Product> findByCategoryName(String categoryName);
}
