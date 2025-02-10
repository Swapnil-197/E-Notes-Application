package com.enote.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.enote.dto.CategoryDto;
import com.enote.dto.CategoryResponse;
import com.enote.entity.Category;

@Service
public interface CategoryService {

	public List<CategoryDto> getAllCategory();
	public Boolean saveCategory(CategoryDto categoryDto);
	public List<CategoryResponse> getActiveCategory();
 }
