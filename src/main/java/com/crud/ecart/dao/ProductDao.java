package com.crud.ecart.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.crud.ecart.model.Product;
@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

	@Query(value="select * from products p inner join brands b on p.brand_brand_id=b.brand_id where brand_name=?",nativeQuery=true)
	List<Product> findByName(String brandName);

	@Query(value="select * from products p inner join categories c on p.category_category_id=c.category_id where c.category_name=?",nativeQuery=true)
	List<Product> findByCatgoryName(String categoryName);

}
