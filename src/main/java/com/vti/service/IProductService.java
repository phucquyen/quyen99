package com.vti.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vti.entity.Product;
import com.vti.form.ProductFormForCreating;
import com.vti.form.ProductFormForUpdating;

public interface IProductService {

	Page<Product> getAllProducts(Pageable pageable);

	Product getProductById(short id);

	Product createNewProduct(ProductFormForCreating productNewForm);

	Product updateProduct(short id, ProductFormForUpdating productUpdateForm);

	void deleteProductById(short id);

	Page<Product> getAllProducts(Pageable pageable, String search);

}
