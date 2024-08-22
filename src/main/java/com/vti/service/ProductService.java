package com.vti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.vti.entity.Category;
import com.vti.entity.Product;
import com.vti.form.ProductFormForCreating;
import com.vti.form.ProductFormForUpdating;
import com.vti.repository.ICategoryRepository;
import com.vti.repository.IProductRepository;

import specification.ProductSpecification;

@Service
public class ProductService implements IProductService {

	@Autowired
	private IProductRepository productRepository;

	@Autowired
	private ICategoryRepository categoryRepository;

	@Override
	public Page<Product> getAllProducts(Pageable pageable) {
		// TODO Auto-generated method stub
		return productRepository.findAll(pageable);
	}

	@Override
	public Product getProductById(short id) {
		// TODO Auto-generated method stub
		return productRepository.getById(id);
	}

	@Override
	public Product createNewProduct(ProductFormForCreating productNewForm) {

		// Tìm manufacturer theo id
		Category category = categoryRepository.getById(productNewForm.getCategoryId());

		Product product = new Product();
		product.setName(productNewForm.getName());
		product.setPrice(productNewForm.getPrice());
		product.setInfo(productNewForm.getInfo());
		product.setDetail(productNewForm.getDetail());
		product.setRatingStar(productNewForm.getRatingStar());
		product.setImageName(productNewForm.getImageName());

		product.setCategory(category);

		Product productNew = productRepository.save(product);
		return productNew;

	}

	@Override
	public Product updateProduct(short id, ProductFormForUpdating productUpdateForm) {
		Product product = productRepository.getById(id);
		// Tìm manufacturer theo id

		// Tìm manufacturer theo id
		Category category = categoryRepository.getById(productUpdateForm.getCategoryId());

		product.setName(productUpdateForm.getName());
		product.setPrice(productUpdateForm.getPrice());
		product.setInfo(productUpdateForm.getInfo());
		product.setDetail(productUpdateForm.getDetail());
		product.setRatingStar(productUpdateForm.getRatingStar());
		product.setImageName(productUpdateForm.getImageName());
		product.setCategory(category);

		Product productUpdate = productRepository.save(product);
		return productUpdate;
	}

	@Override
	public void deleteProductById(short id) {
		productRepository.deleteById(id);
	}

	@Override
	public Page<Product> getAllProducts(Pageable pageable, String search) {
		Specification<Product> where = null;

		if (!StringUtils.isEmpty(search)) {
			ProductSpecification nameSpecification = new ProductSpecification("name", "LIKE", search);
			ProductSpecification priceSpecification = new ProductSpecification("price", "LIKE", search);
			ProductSpecification infoSpecification = new ProductSpecification("info", "LIKE", search);
			ProductSpecification categorySpecification = new ProductSpecification("category", "LIKE", search);

			where = Specification.where(nameSpecification).or(priceSpecification).or(infoSpecification)
					.or(categorySpecification);
		}
//		Example<Product> example = Example.of(new Product(), ExampleMatcher.matchingAny().withMatcher("name",
//				ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase()));
		return productRepository.findAll(where, pageable);
	}

}
