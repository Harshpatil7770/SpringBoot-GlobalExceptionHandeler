package com.crud.ecart.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.ecart.model.Brand;
import com.crud.ecart.service.BrandService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/brand")
@Slf4j
public class BrandController {

	@Autowired
	BrandService brandService;

	@PostMapping("/save")
	public Brand addNewBrand(@RequestBody Brand brand) {

		Brand newBrand = brandService.addNewBrand(brand);
		String methodName = "addNewBrand()";
		log.info(methodName + " called");
		return newBrand;

	}

	@PostMapping("/saveAll")
	public List<Brand> addNewListOfBrands(@RequestBody List<Brand> brand) {
		String methodName = "addNewListOfBrands()";
		log.info(methodName + " called");
		return brandService.addNewListOfBrands(brand);

	}

	@PutMapping("/update")
	public Brand updateBrand(@RequestBody Brand brand) {
		String methodName = "updateBrand()";
		log.info(methodName + " called");
		return brandService.updateBrand(brand);
	}

	@PutMapping("/updateAll")
	public List<Brand> updateMultipleBrands(@RequestBody List<Brand> brand) {
		String methodName = "updateMultipleBrands()";
		log.info(methodName + " called");
		List<Brand> updatedbrandsList = brandService.updateMultipleBrands(brand);
		return updatedbrandsList;
	}

	@GetMapping("/{brandId}")
	public Optional<Brand> findBtId(@PathVariable int brandId) {
		String methodName = "findBtId()";
		log.info(methodName + " called");

		Optional<Brand> existingBrand = brandService.findById(brandId);
		return existingBrand;
	}

	@GetMapping("/fetchAll")
	public List<Brand> fetchAllBrands() {
		List<Brand> brandList = brandService.fetchAllBrand();
		String methodName = "fetchAllBrands()";
		log.info(methodName + " called");
		return brandList;

	}

	@DeleteMapping("/delete/{brandId}")
	public String deleteById(@PathVariable int brandId) {
		String method = "deleteById()";
		log.info(method + " called");
		brandService.deleteById(brandId);
		return " Deleted Brand Succesfully !";

	}
}
