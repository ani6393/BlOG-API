package com.blogging.Application.Services;

import java.util.List;

import com.blogging.Application.Payloads.CategoryDto;

public interface CategoryService 
{
// Create
	
	public CategoryDto createCategory(CategoryDto categoryDto);
	
// update
	
	public CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);

// delete

	public void  deleteCategory(Integer categoryId);

// get

	public CategoryDto getCategory(Integer categoryId);

// get All
	
	List<CategoryDto> getAllCategory();
}
