package com.crud.ecart.service;

import java.util.List;
import java.util.Optional;

import com.crud.ecart.model.Brand;

public interface BrandService {

	Brand addNewBrand(Brand brand);
	
	List<Brand> addNewListOfBrands(List<Brand> brand);
	
	Brand updateBrand(Brand brand);
	
	List<Brand> updateMultipleBrands(List<Brand> brand);
	
	Optional<Brand> findById(int brandId);
	
	List<Brand> fetchAllBrand();
	
	String deleteById(int brandId);
}
