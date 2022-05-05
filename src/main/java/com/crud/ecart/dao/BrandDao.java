package com.crud.ecart.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.crud.ecart.model.Brand;
import com.crud.ecart.model.Product;
@Repository
public interface BrandDao extends JpaRepository<Brand,Integer>{

	@Query(value="select * from brands where brand_name=?",nativeQuery=true)
	Optional<Brand> findByName(String brandName);

	@Query(value="select * from brands where brand_name=?",nativeQuery=true)
	List<Product> findByBrandName(String brandName);

}
