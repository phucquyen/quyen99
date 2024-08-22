package com.vti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vti.entity.Category;

public interface ICategoryRepository extends JpaRepository<Category, Short> {

	@Override
	List<Category> findAll();

}
