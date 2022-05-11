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

import com.crud.ecart.model.Brand;
import com.crud.ecart.service.BrandService;

@ExtendWith(MockitoExtension.class) // provides mock objects for you
class BrandControllerTest {

	@Mock // @Mock annotations is used to create the mock oject
	BrandService brandService;

	@InjectMocks // It create instance of class and helps to inject all neccesary mock object
	BrandController brandController;

	private Brand brand;

	@BeforeEach
	public void setup() {
		brand = new Brand();
		brand.setBrandId(101);
		brand.setBrandName("Samsung");
	}

	@Test
	public void addNewBrand() {
		when(brandService.addNewBrand(brand)).thenReturn(brand);
		Brand result = brandService.addNewBrand(brand);
		assertNotNull(result);
		assertEquals(brand, brandController.addNewBrand(brand));

	}

	@Test
	public void addNewListOfBrands() {
		List<Brand> brandLists = new ArrayList<Brand>();
		brandLists.add(brand);
		assertNotNull(brand);
		Brand brand1 = new Brand();
		brand1.setBrandId(102);
		brand1.setBrandName("Oppo");
		assertNotNull(brand1);
		brandLists.add(brand1);
		when(brandService.addNewListOfBrands(brandLists)).thenReturn(brandLists);
		assertEquals(brandLists, brandController.addNewListOfBrands(brandLists));
	}

	@Test
	public void updateBrand() {
		Optional<Brand> existingBrand = Optional.of(brand);
		when(brandService.findById(101)).thenReturn(existingBrand);
		Optional<Brand> result = brandService.findById(101);
		assertNotNull(result);
		brand.setBrandName("I Phone");
		when(brandService.updateBrand(brand)).thenReturn(brand);
		assertEquals(brand, brandController.updateBrand(brand));

	}

	@Test
	public void updateMultipleBrands() {
		List<Brand> brandLists = new ArrayList<Brand>();

		Optional<Brand> existingBrand = Optional.of(brand);
		when(brandService.findById(101)).thenReturn(existingBrand);
		Optional<Brand> result = brandService.findById(101);
		assertNotNull(result);
		brand.setBrandName("Accer");
		brandLists.add(brand);

		Brand brand1 = new Brand();
		brand1.setBrandId(102);
		brand1.setBrandName("Lenovo");
		Optional<Brand> existingBrand1 = Optional.of(brand1);
		when(brandService.findById(102)).thenReturn(existingBrand1);
		Optional<Brand> result1 = brandService.findById(102);
		assertNotNull(result1);
		brand1.setBrandName("Dell");
		brandLists.add(brand1);

		when(brandService.updateMultipleBrands(brandLists)).thenReturn(brandLists);
		assertEquals(brandLists, brandController.updateMultipleBrands(brandLists));
	}

	@Test
	public void findById() {
		Optional<Brand> existingBrand = Optional.of(brand);
		when(brandService.findById(101)).thenReturn(existingBrand);
		Optional<Brand> result = brandService.findById(101);
		assertNotNull(result);
		assertEquals(result, brandController.findById(101));
	}

	@Test
	public void fetchAllBrands() {
		List<Brand> brandLists = new ArrayList<Brand>();
		brandLists.add(brand);
		Brand brand1 = new Brand();
		brand1.setBrandId(102);
		brand1.setBrandName("Dell");
		brandLists.add(brand1);
		when(brandService.fetchAllBrand()).thenReturn(brandLists);
		assertEquals(2, brandController.fetchAllBrands().size());
	}

	@Test
	public void deleteById() {
		when(brandService.deleteById(101)).thenReturn("Deleted Brand Succesfully !");
		String result = brandService.deleteById(101);
		assertEquals(result, brandController.deleteById(101));

	}
}
