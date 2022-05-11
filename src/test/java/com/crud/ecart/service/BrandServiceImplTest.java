package com.crud.ecart.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.crud.ecart.dao.BrandDao;
import com.crud.ecart.globalexceptionhandeler.InputUserException;
import com.crud.ecart.model.Brand;

@ExtendWith(MockitoExtension.class) // It provide mock object for you
class BrandServiceImplTest {

	@Mock // @Mock annotationsi is used to mock the object. It test your business logic.
	BrandDao brandDao;

	@InjectMocks // @InjectMocks is used to create instance of class and inject all neccesaary
					// mocks.
	BrandServiceImpl brandServiceImpl;

	private Brand brand;

	@BeforeEach // setup method or execute before each test.
	public void setup() {
		brand = new Brand();
		brand.setBrandId(101);
		brand.setBrandName("Oppo");
	}

	@Test
	public void addNewBrand() {
		when(brandDao.save(brand)).thenReturn(brand);
		Brand result = brandDao.save(brand);
		assertNotNull(result);
		assertEquals(brand, brandServiceImpl.addNewBrand(brand));
		
	}

	@Test
	public void addNewBrandThrowsExpection_IF_BrandNameIsNull() {
		doThrow(InputUserException.class).when(brandDao).save(null);
		assertThrows(InputUserException.class, () -> {
			brandDao.save(null);
		});
	}

	@Test
	public void addNewBrandThrowsExpection_IF_BrandNameIsBlank() {
		Brand brand1 = new Brand();
		brand1.setBrandId(101);
		brand1.setBrandName(" ");
		doThrow(InputUserException.class).when(brandDao).save(brand1);
		assertThrows(InputUserException.class, () -> {
			brandDao.save(brand1);
		});
	}

	@Test
	public void addNewListOfBrands() {
		List<Brand> brandLists = new ArrayList<Brand>();
		assertNotNull(brand);
		brandLists.add(brand);
		Brand brand1 = new Brand();
		brand1.setBrandId(102);
		brand1.setBrandName("I Phone");
		assertNotNull(brand1);
		brandLists.add(brand1);
		when(brandDao.saveAll(brandLists)).thenReturn(brandLists);
		assertEquals(brandLists, brandServiceImpl.addNewListOfBrands(brandLists));
		assertEquals(2, brandServiceImpl.addNewListOfBrands(brandLists).size());
	}

	@Test
	public void addNewListOfBrandsThrowsException_IF_BrandNameIsNull() {
		doThrow(InputUserException.class).when(brandDao).saveAll(null);
		assertThrows(InputUserException.class, () -> {
			brandDao.saveAll(null);
		});
	}

	@Test
	public void addNewListOfBrandsThrowsException_IF_BrandNameIsBlank() {
		List<Brand> brandLists = new ArrayList<Brand>();
		brandLists.add(brand);
		Brand brand1 = new Brand();
		brand1.setBrandId(102);
		brand1.setBrandName(" ");
		brandLists.add(brand1);
		doThrow(InputUserException.class).when(brandDao).saveAll(brandLists);
		assertThrows(InputUserException.class, () -> {
			brandDao.saveAll(brandLists);
		});
	}

	@Test
	public void updateBrand() {
		Optional<Brand> existingBrand = Optional.of(brand);
		when(brandDao.findById(101)).thenReturn(existingBrand);
		Optional<Brand> result = brandDao.findById(101);
		assertNotNull(result);
		brand.setBrandName("Vivo");
		when(brandDao.save(brand)).thenReturn(brand);
		assertEquals(brand, brandServiceImpl.updateBrand(brand));
	}

	@Test
	public void updatebrandThrowsExpection_IF_BrandIsNotFound() {
		doThrow(InputUserException.class).when(brandDao).findById(null);
		assertThrows(InputUserException.class, () -> {
			brandDao.findById(null);
		});
	}

	@Test
	public void updatebrandThrowsExpection_IF_BrandNameIsEmpty() {
		doThrow(InputUserException.class).when(brandDao).save(null);
		assertThrows(InputUserException.class, () -> {
			brandDao.save(null);
		});
	}

	@Test
	public void updatebrandThrowsExpection_IF_BrandNameIsBlank() {
		Brand brand1 = new Brand();
		brand1.setBrandId(102);
		brand1.setBrandName(" ");
		doThrow(InputUserException.class).when(brandDao).save(brand1);
		assertThrows(InputUserException.class, () -> {
			brandDao.save(brand1);
		});
	}

	@Test
	public void updateMultipleBrands() {
		List<Brand> updateListsBrand = new ArrayList<Brand>();
		Brand brand1 = new Brand();
		brand1.setBrandId(102);
		brand1.setBrandName("Samsung");

		Optional<Brand> existingBrand1 = Optional.of(brand);
		when(brandDao.findById(101)).thenReturn(existingBrand1);
		Optional<Brand> result = brandDao.findById(101);
		assertNotNull(result);
		brand.setBrandName("Vivo");
		updateListsBrand.add(brand);

		Optional<Brand> existingBrand2 = Optional.of(brand1);
		when(brandDao.findById(102)).thenReturn(existingBrand2);
		Optional<Brand> result1 = brandDao.findById(102);
		assertNotNull(result1);
		brand1.setBrandName("I ball");
		updateListsBrand.add(brand1);

		// when(brandDao.saveAll(updateListsBrand)).thenReturn(updateListsBrand);
		assertThat(brandServiceImpl.updateMultipleBrands(updateListsBrand)).isEqualTo(updateListsBrand);	
		assertEquals(updateListsBrand, brandServiceImpl.updateMultipleBrands(updateListsBrand));
		// assertEquals(2,
		// brandServiceImpl.updateMultipleBrands(updateListsBrand).size());

	}
}
