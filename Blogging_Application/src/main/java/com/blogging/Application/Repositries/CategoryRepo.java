package com.blogging.Application.Repositries;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogging.Application.Entities.Categories;

public interface CategoryRepo extends JpaRepository<Categories, Integer>
{

}
