package com.vti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.CategoryDto;
import com.vti.entity.Category;
import com.vti.service.ICategoryService;

@RestController
@RequestMapping(value = "api/v1/categorys")
@CrossOrigin("*")
public class CategoryController {
	@Autowired
	private ICategoryService categoryService;

	@GetMapping()
	public ResponseEntity<?> getAllCategorys() {
		List<Category> categoryListDB = categoryService.getAllCategorys();
		List<CategoryDto> categoryListDto = new ArrayList<>();

		for (Category categoryDB : categoryListDB) {
			CategoryDto categoryDto = new CategoryDto();
			categoryDto.setId(categoryDB.getId());
			categoryDto.setName(categoryDB.getName());

			categoryListDto.add(categoryDto);
		}

		return new ResponseEntity<>(categoryListDto, HttpStatus.OK);

	}
	// tim san pham theo id

}
