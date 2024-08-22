package com.vti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vti.entity.Category;
import com.vti.repository.ICategoryRepository;

@Service
public class CategoryService implements ICategoryService {
	@Autowired
	private ICategoryRepository categoryRepository;

	@Override
	public List<Category> getAllCategorys() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll();
	}

}
