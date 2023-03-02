package com.blogging.Application.Services.impl;

import java.util.List;
import java.util.Locale.Category;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogging.Application.Entities.Categories;
import com.blogging.Application.Exceptions.ResourceNotFoundException;
import com.blogging.Application.Payloads.CategoryDto;
import com.blogging.Application.Repositries.CategoryRepo;
import com.blogging.Application.Services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	
   @Autowired
	private CategoryRepo categoryRepo;
   
   @Autowired
   private ModelMapper modelMapper;


   
   // Create 
   
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {

		Categories cat = this.modelMapper.map(categoryDto, Categories.class);
		Categories addedCat =this.categoryRepo.save(cat);
		return this.modelMapper.map(addedCat, CategoryDto.class);
	}

	// Update
	
	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {

		Categories cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Categories", "Categories Id", categoryId));
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
	Categories updated=this.categoryRepo.save(cat);
		return this.modelMapper.map(updated, CategoryDto.class);
	}
	
	//Delete

	@Override
	public void deleteCategory(Integer categoryId) {

		Categories cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Categories","Categories id",categoryId));
		this.categoryRepo.delete(cat);
	}

	// GetById
	
	@Override
	public CategoryDto getCategory(Integer categoryId) {

		Categories cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Categories","Categories id",categoryId));
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	// GetAll
	
	@Override
	public List<CategoryDto> getAllCategory() {

	List<Categories>All = this.categoryRepo.findAll();
	List<CategoryDto> allList=All.stream().map((Cat)->this.modelMapper.map(Cat, CategoryDto.class)).collect(Collectors.toList());
	return allList;
	}

}
