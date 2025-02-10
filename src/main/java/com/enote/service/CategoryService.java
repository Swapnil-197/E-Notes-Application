package com.enote.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.enote.entity.Category;

@Service
public interface CategoryService {

	public Boolean saveCategory(Category category);
	public List<Category> getAllCategory();
 }
