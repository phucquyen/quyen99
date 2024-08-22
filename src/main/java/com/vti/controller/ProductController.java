package com.vti.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.ProductDto;
import com.vti.entity.Product;
import com.vti.form.ProductFormForCreating;
import com.vti.form.ProductFormForUpdating;
import com.vti.service.IProductService;

@RestController
@RequestMapping(value = "api/v1/products")
@CrossOrigin("*")
public class ProductController {
	@Autowired
	private IProductService productService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public Page<ProductDto> getAllProducts(Pageable pageable, @RequestParam(required = false) String search) {
		Page<Product> productListDB = productService.getAllProducts(pageable, search);
//		List<ProductDto> productListDto = new ArrayList<>();
//
//		for (Product productDB : productListDB) {
//			ProductDto productDto = new ProductDto();
//			productDto.setId(productDB.getId());
//			productDto.setName(productDB.getName());
//			productDto.setPrice(productDB.getPrice());
//			productDto.setInfo(productDB.getInfo());
//			productDto.setDetail(productDB.getDetail());
//			productDto.setRatingStar(productDB.getRatingStar());
//			productDto.setImageName(productDB.getImageName());
//
//			productDto.setCategoryName(productDB.getCategory().getName());
//
//			productListDto.add(productDto);
//
//		}

		List<ProductDto> producPageDto = modelMapper.map(productListDB.getContent(), new TypeToken<List<ProductDto>>() {
		}.getType());

		return new PageImpl<>(producPageDto, pageable, productListDB.getTotalElements());
	}

	// tim san pham theo id
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getProductByI(@PathVariable(name = "id") short id) {
		try {
			Product productDB = productService.getProductById(id);

			ProductDto productDto = new ProductDto();
			productDto.setId(productDB.getId());
			productDto.setName(productDB.getName());
			productDto.setPrice(productDB.getPrice());
			productDto.setInfo(productDB.getInfo());
			productDto.setDetail(productDB.getDetail());
			productDto.setRatingStar(productDB.getRatingStar());
			productDto.setImageName(productDB.getImageName());

			productDto.setCategoryName(productDB.getCategory().getName());

			return new ResponseEntity<>(productDto, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);

		}
	}

//	Thêm mới sản phẩm
	@PostMapping()
	public ResponseEntity<?> createNewproduct(@RequestBody ProductFormForCreating productNewForm) {

//			Thêm mới Product
//			Sau khi thêm mới, trả về thông tin Product vừa thêm
		Product productNew = productService.createNewProduct(productNewForm);

//			Convert
		ProductDto productNewDto = new ProductDto();
		productNewDto.setId(productNew.getId());
		productNewDto.setName(productNew.getName());
		productNewDto.setPrice(productNew.getPrice());
		productNewDto.setInfo(productNew.getInfo());
		productNewDto.setDetail(productNew.getDetail());
		productNewDto.setRatingStar(productNew.getRatingStar());
		productNewDto.setImageName(productNew.getImageName());

		productNewDto.setCategoryName(productNew.getCategory().getName());

		return new ResponseEntity<>(productNewDto, HttpStatus.CREATED);

	}

//	Update sản phẩm
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable(name = "id") short id,
			@RequestBody ProductFormForUpdating productUpdateForm) {
		try {
//			Thực hiện Update Product
//			Sau khi Update, trả về thông tin Product vừa Update
			Product productUpdate = productService.updateProduct(id, productUpdateForm);

//			Convert
			ProductDto productUpdateDto = new ProductDto();
			productUpdateDto.setId(productUpdate.getId());
			productUpdateDto.setName(productUpdate.getName());
			productUpdateDto.setPrice(productUpdate.getPrice());
			productUpdateDto.setInfo(productUpdate.getInfo());
			productUpdateDto.setDetail(productUpdate.getDetail());
			productUpdateDto.setRatingStar(productUpdate.getRatingStar());
			productUpdateDto.setImageName(productUpdate.getImageName());

			productUpdateDto.setCategoryName(productUpdate.getCategory().getName());

			return new ResponseEntity<>(productUpdateDto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
		}

	}

	// xoa san pham theo id
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteProductById(@PathVariable(name = "id") short id) {
		try {
			Product productDelete = productService.getProductById(id);
			ProductDto productDeleteDto = new ProductDto();
			productDeleteDto.setId(productDelete.getId());
			productDeleteDto.setName(productDelete.getName());
			productDeleteDto.setPrice(productDelete.getPrice());
			productDeleteDto.setInfo(productDelete.getInfo());
			productDeleteDto.setDetail(productDelete.getDetail());
			productDeleteDto.setRatingStar(productDelete.getRatingStar());
			productDeleteDto.setImageName(productDelete.getImageName());

			productDeleteDto.setCategoryName(productDelete.getCategory().getName());

//			Xóa Product
			productService.deleteProductById(id);

			return new ResponseEntity<>(productDeleteDto, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
		}
	}
}
