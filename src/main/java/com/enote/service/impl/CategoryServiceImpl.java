package com.enote.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;

import com.enote.dto.CategoryDto;
import com.enote.dto.CategoryResponse;
import com.enote.entity.Category;
import com.enote.repository.CategoryRepository;
import com.enote.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	private static final Object CategoryDto = null;

	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public Boolean saveCategory(CategoryDto categoryDto) {
		
//		Category category = new Category();
//		category.setName(categoryDto.getName());
//		category.setDescription(categoryDto.getDescription());
//		category.setIsActive(categoryDto.getIsActive());
		
		// Above 4 lines code is done by below one line only by adding model mapper dependancy in pom.xml file
		Category category = mapper.map(categoryDto,Category.class);
				
		category.setIsDeleted(false);
		category.setCreatedBy(1);
		category.setCreatedDate(new Date());
		Category saveCategory = categoryRepo.save(category);
		if(ObjectUtils.isEmpty(saveCategory)) {
			return false;
		}
		return true;
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories = categoryRepo.findByIsDeletedFalse();
		// Implementation of Java 8 stream concept
		List<CategoryDto> categoryListDto = categories.stream().map(cat -> mapper.map(cat , CategoryDto.class)).toList();
		return categoryListDto;
	}

	
//	1️. categories.stream()
//	categories is assumed to be a collection (likely a List<Category>).
//	Calling .stream() converts the List<Category> into a Stream<Category>, allowing functional-style operations like map().

	//	2️. .map(cat -> mapper.map(cat , CategoryDto.class))
//	.map() is used to transform each element in the stream.
//	cat -> mapper.map(cat, CategoryDto.class) is a lambda expression, where:
//	cat is a single Category object from the categories list.
//	mapper.map(cat, CategoryDto.class) converts cat (a Category object) into a CategoryDto object.
//	mapper is likely an Object Mapper (such as ModelMapper or MapStruct) that maps one object type to another.

	//	3️. .toList()
//	This collects the transformed elements back into a List<CategoryDto>.
//	It converts the Stream<CategoryDto> into a List<CategoryDto>, ensuring the result remains in a list format.
	
//	Converts a List<Category> into a Stream<Category>.
//	Uses .map() to transform each Category into a CategoryDto.
//	Collects the transformed items back into a List<CategoryDto>.
	
	@Override
	public List<CategoryResponse> getActiveCategory() {
		List<Category> categories = categoryRepo.findByIsActiveTrueAndIsDeletedFalse();
		List<CategoryResponse> isActiveList = categories.stream().map(cat -> mapper.map(cat , CategoryResponse.class))
				.collect(Collectors.toList());
		return isActiveList;
	}
	
	@Override
	public CategoryDto getCategoryById(Integer id) {
		Optional<Category> category = categoryRepo.findByIdAndIsDeletedFalse(id);
		if(category.isPresent()) {
			Category categoryObj = category.get();
			return mapper.map(categoryObj, CategoryDto.class);
		}
		return null;
	}

	@Override
	public Boolean deleteCategoryById(Integer id) {
		Optional<Category> findById = categoryRepo.findById(id);
		if(findById.isPresent()) {
			Category category = findById.get();
			category.setIsDeleted(true);
			categoryRepo.save(category);
			return true;
		}
		return false;
	}

}
