package com.crud.ecart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.ecart.dao.BrandDao;
import com.crud.ecart.dao.CategoryDao;
import com.crud.ecart.dao.ProductDao;
import com.crud.ecart.globalexceptionhandeler.ElementNotFoundException;
import com.crud.ecart.globalexceptionhandeler.InputUserException;
import com.crud.ecart.globalexceptionhandeler.NegativeValueExpection;
import com.crud.ecart.model.Brand;
import com.crud.ecart.model.Category;
import com.crud.ecart.model.Product;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;

	@Autowired
	BrandDao brandDao;

	@Autowired
	CategoryDao categoryDao;

	// Not Working Validation (BrandID, CategoryID)
	@Override
	public Product addNewProduct(Product product) {

		// if along with else if used when we want to mutiple condition based on
		// previous one.
		if (product.getProductName().length() == 0 || product.getProductName().isEmpty()
				|| product.getProductName().isBlank()) {
			throw new InputUserException();
		} else if (product.getDescription().length() == 0 || product.getDescription().isEmpty()
				|| product.getProductName().isBlank()) {
			throw new InputUserException();
		} else if (product.getPrice() <= 0) {
			throw new NegativeValueExpection();
		}

		// Optional<Product> existingBrand =
		// productDao.findById(product.getBrand().getBrandId());
//		if (!existingBrand.isPresent()) {
//			throw new ElementNotFoundException();
//		}
//
//		Optional<Product> existingCategory = productDao.findById(product.getCategory().getCategoryId());
//		if (!existingCategory.isPresent()) {
//			throw new ElementNotFoundException();
//		}

		if (product.getBrand().getBrandId() <= 0 || product.getCategory().getCategoryId() <= 0) {
			throw new NegativeValueExpection();
		}

		Product addProduct = productDao.save(product);
		String methodName = "addNewProduct()";
		log.info(methodName + " called");
		return addProduct;
	}

	@Override
	public List<Product> addNewListOfProduct(List<Product> product) {

		// muiltiple if statement used when want to check multiple condition no matter
		// previous statement is true or false.
		List<Product> newProductList = null;
		for (Product newProduct : product) {
			if (newProduct.getProductName().isEmpty() || newProduct.getProductName().length() == 0
					|| newProduct.getProductName().isBlank()) {
				throw new InputUserException();
			}
			if (newProduct.getDescription().isEmpty() || newProduct.getDescription().length() == 0
					|| newProduct.getDescription().isBlank()) {
				throw new InputUserException();
			}
			if (newProduct.getPrice() <= 0) {
				throw new NegativeValueExpection();
			}

			if (newProduct.getBrand().getBrandId() < 0 || newProduct.getCategory().getCategoryId() < 0) {
				throw new NegativeValueExpection();
			}
//			if (newProduct.getBrand().getBrandName().isEmpty() || newProduct.getBrand().getBrandName().length() == 0
//					|| newProduct.getBrand().getBrandName().isBlank()) {
//				throw new InputUserException();
//			}
//			if (newProduct.getCategory().getCategoryName().isEmpty()
//					|| newProduct.getCategory().getCategoryName().length() == 0
//					|| newProduct.getCategory().getCategoryName().isBlank()) {
//				throw new InputUserException();
//			}
		}

		newProductList = productDao.saveAll(product);
		return newProductList;
	}

	@Override
	public Product updateProduct(Product product) {

		Optional<Product> updateProduct = productDao.findById(product.getProductId());
		// Optional<Product> existingBrand =
		// productDao.findById(product.getBrand().getBrandId());

		// Optional<Brand> existingBrand=brandDao.findById(brand.getBrandId());
		// Optional<Category> existingCategory
		// =categoryDao.findById(category.getCategoryId());
		// Optional<Product> existingCategory =
		// productDao.findById(product.getCategory().getCategoryId());
		if (product.getProductName().isEmpty() || product.getProductName().length() == 0
				|| product.getProductName().isBlank()) {
			throw new InputUserException();
		} else if (product.getDescription().isEmpty() || product.getDescription().length() == 0
				|| product.getDescription().isBlank()) {
			throw new InputUserException();
		} else if (product.getPrice() <= 0) {
			throw new NegativeValueExpection();
		} else if (!updateProduct.isPresent()) {
			throw new ElementNotFoundException();
		}
//		} else if (!existingBrand.isPresent()) {
//			throw new ElementNotFoundException();
//		} else if (!existingCategory.isPresent()) {
//			throw new ElementNotFoundException();
//		}
		else if (product.getBrand().getBrandId() <= 0 || product.getCategory().getCategoryId() <= 0) {
			throw new NegativeValueExpection();
		}

		Product existinProduct = productDao.findById(product.getProductId()).orElse(null);

		existinProduct.setProductId(product.getProductId());
		existinProduct.setProductName(product.getProductName());
		existinProduct.setPrice(product.getPrice());
		existinProduct.setDescription(product.getDescription());
		existinProduct.setBrand(product.getBrand());
		existinProduct.setCategory(product.getCategory());

		return productDao.save(existinProduct);

	}

	@Override
	public List<Product> updateListOfProducts(List<Product> product) {

		List<Product> listsProduct = new ArrayList<Product>();

		for (Product eachProduct : product) {
			Optional<Product> existingProduct = productDao.findById(eachProduct.getProductId());
			if (!existingProduct.isPresent()) {
				throw new ElementNotFoundException();
			}
			if (eachProduct.getProductName().isEmpty() || eachProduct.getProductName().isBlank()
					|| eachProduct.getProductName().length() == 0) {
				throw new InputUserException();
			}
			if (eachProduct.getDescription().isEmpty() || eachProduct.getDescription().isBlank()
					|| eachProduct.getProductName().length() == 0) {
				throw new InputUserException();
			}
			if (eachProduct.getPrice() < 0) {
				throw new NegativeValueExpection();
			}
			if (eachProduct.getBrand().getBrandId() <= 0 || eachProduct.getCategory().getCategoryId() <= 0) {
				throw new NegativeValueExpection();
			}
		}

		for (Product eachProduct : product) {
			Product updateProduct = productDao.findById(eachProduct.getProductId()).orElse(null);
			updateProduct.setProductId(eachProduct.getProductId());
			updateProduct.setProductName(eachProduct.getProductName());
			updateProduct.setPrice(eachProduct.getPrice());
			updateProduct.setBrand(eachProduct.getBrand());
			updateProduct.setCategory(eachProduct.getCategory());
			productDao.save(eachProduct);
			listsProduct.add(updateProduct);
		}

		return listsProduct;
	}

	@Override
	public Optional<Product> fetchById(int productId) {
		Optional<Product> existingProduct = productDao.findById(productId);
		if (!existingProduct.isPresent()) {
			throw new ElementNotFoundException();
		}
		String methodName = "fetchById()";
		log.info(methodName + " called");
		return existingProduct;
	}

	@Override
	public List<Product> fetchAllProducts() {
		List<Product> productList = productDao.findAll();
		if (productList.isEmpty()) {
			throw new ElementNotFoundException();
		}
		String methodName = "fetchAllProducts()";
		log.info(methodName + " called");
		return productList;
	}

	@Override
	public String deleteProduct(int productId) {
		Optional<Product> existingProduct = productDao.findById(productId);
		if (!existingProduct.isPresent()) {
			throw new ElementNotFoundException();
		}

		productDao.deleteById(productId);
		String methodName = "deleteProduct()";
		log.info(methodName + " called");
		return "deleted Product !";
	}

//	@Override
//	public List<Product> findByBrandName(String brandName) {
	@Override
	public List<Product> findByBrand(String brandName) {

//		Optional<Product> existingProduct = productDao.findByBrand(brandName);
//		if (!existingProduct.isPresent()) {
//			throw new ElementNotFoundException();
//		}
		List<Product> existBrand = productDao.findByName(brandName);

		String methodName = "findByBrandName()";
		log.info(methodName + " called");
		return existBrand;
	}

	@Override
	public List<Product> findByCategoryName(String categoryName) {
		List<Product> existCategory = productDao.findByCatgoryName(categoryName);
		String methodName = "findByCategoryName()";
		log.info(methodName + " called");
		return existCategory;
	}
}
