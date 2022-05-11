package com.crud.ecart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.ecart.dao.BrandDao;
import com.crud.ecart.globalexceptionhandeler.ElementNotFoundException;
import com.crud.ecart.globalexceptionhandeler.InputUserException;
import com.crud.ecart.model.Brand;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BrandServiceImpl implements BrandService {

	@Autowired
	BrandDao brandDao;

	@Override
	public Brand addNewBrand(Brand brand) {
		if (brand.getBrandName().isEmpty() || brand.getBrandName().length() == 0 || brand.getBrandName().isBlank()) {
			throw new InputUserException();
		}

		Brand brandAdded = brandDao.save(brand);
		String methodName = "addNewBrand()";
		log.info(methodName + " called");
		return brandAdded;
	}

	@Override
	public List<Brand> addNewListOfBrands(List<Brand> brand) {
		List<Brand> brandsLists = brandDao.saveAll(brand);
		for (Brand newBrand : brand) {
			if (newBrand.getBrandName().isBlank()) {
				if (newBrand.getBrandName().length() == 0 || newBrand.getBrandName().isEmpty()) {
					throw new InputUserException();
				}
			}
		}
		String methodName = "addNewListOfBrands()";
		log.info(methodName + " called");
		return brandsLists;

	}

	@Override
	public Brand updateBrand(Brand brand) {

		Optional<Brand> existingBrand = brandDao.findById(brand.getBrandId());
		if (!existingBrand.isPresent()) {
			throw new ElementNotFoundException();
		}
		Brand existingBrands = brandDao.findById(brand.getBrandId()).orElse(null);
		existingBrands.setBrandId(brand.getBrandId());
		if (brand.getBrandName().isEmpty() || brand.getBrandName().length() == 0 || brand.getBrandName().isEmpty()) {
			throw new InputUserException();
		}
		existingBrands.setBrandName(brand.getBrandName());
		String methodName = "updateBrand()";
		log.info(methodName + " called");
		return brandDao.save(brand);
	}

	@Override
	public List<Brand> updateMultipleBrands(List<Brand> brand) {
		List<Brand> updateBrandLists = new ArrayList<Brand>();
		for (Brand eachExistingBrand : brand) {
			Optional<Brand> eachbrand = brandDao.findById(eachExistingBrand.getBrandId());
			if (!eachbrand.isPresent()) {
				throw new ElementNotFoundException();
			}
		}

		for (Brand eachExistingBrand : brand) {
			Brand updateBrand = brandDao.findById(eachExistingBrand.getBrandId()).orElse(null);
			updateBrand.setBrandId(eachExistingBrand.getBrandId());
			if (eachExistingBrand.getBrandName().isEmpty() || eachExistingBrand.getBrandName().length() == 0
					|| eachExistingBrand.getBrandName().isBlank()) {
				throw new InputUserException();
			}
			updateBrand.setBrandName(eachExistingBrand.getBrandName());
			brandDao.save(eachExistingBrand);
			updateBrandLists.add(updateBrand);

		}
		return updateBrandLists;
	}

	@Override
	public Optional<Brand> findById(int brandId) {

		Optional<Brand> existingBrand = brandDao.findById(brandId);
		if (!existingBrand.isPresent()) {
			throw new ElementNotFoundException();
		}
		String methodName = "findById()";
		log.info(methodName + " called");
		return existingBrand;
	}

	@Override
	public List<Brand> fetchAllBrand() {
		List<Brand> existingList = brandDao.findAll();
		if (existingList.isEmpty()) {
			throw new ElementNotFoundException();
		}
		String methodName = "fetchAllBrand()";
		log.info(methodName + " called");
		return existingList;
	}

	@Override
	public String deleteById(int brandId) {

		Optional<Brand> existingBrandId = brandDao.findById(brandId);
		if (!existingBrandId.isPresent()) {
			throw new ElementNotFoundException();
		}
		brandDao.deleteById(brandId);
		String method = "deleteById()";
		log.info(method + " called");
		return "Deleted Succesfully";
	}

}
