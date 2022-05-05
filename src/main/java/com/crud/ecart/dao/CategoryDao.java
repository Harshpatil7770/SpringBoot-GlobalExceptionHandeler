package com.crud.ecart.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.crud.ecart.model.Category;
@Repository
public interface CategoryDao extends JpaRepository<Category,Integer> {

	@Query(value="delete from categories where category_id=?",nativeQuery=true)
	boolean deleteByCategoryId(int categoryId);

}
