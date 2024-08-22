package com.vti.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vti.entity.Product;

public interface IProductRepository extends JpaRepository<Product, Short> {
	Page<Product> findAll(Specification<Product> specification, Pageable pageable);
}
