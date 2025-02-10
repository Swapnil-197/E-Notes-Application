package com.enote.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enote.dto.CategoryDto;
import com.enote.dto.CategoryResponse;
import com.enote.entity.Category;
import com.enote.service.CategoryService;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/Save-Category")
	public ResponseEntity<?> saveCategory(@RequestBody CategoryDto categorydto){
		Boolean saveCategory = categoryService.saveCategory(categorydto);
		if(saveCategory) {
			return new ResponseEntity<>("Saved Successfully", HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>("Not Saved", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/All-Category")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		List<CategoryDto> allCategory = categoryService.getAllCategory();
		if(CollectionUtils.isEmpty(allCategory)) {
			return ResponseEntity.noContent().build();
		}else {
			return new ResponseEntity<>(allCategory,HttpStatus.OK);
		}
		
	}
	
	@GetMapping("/Active-Category")
	public ResponseEntity<List<CategoryResponse>> getActiveCategory(){
		List<CategoryResponse> allActiveCategory = categoryService.getActiveCategory();
		if(CollectionUtils.isEmpty(allActiveCategory)) {
			return ResponseEntity.noContent().build();
		}else {
			return new ResponseEntity<>(allActiveCategory,HttpStatus.OK);
		}
		
	}
}
