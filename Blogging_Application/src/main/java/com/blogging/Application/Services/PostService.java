package com.blogging.Application.Services;

import java.util.List;

import org.modelmapper.internal.bytebuddy.asm.Advice.This;

import com.blogging.Application.Entities.Categories;
import com.blogging.Application.Entities.Post;
import com.blogging.Application.Payloads.PostDto;
import com.blogging.Application.Payloads.PostResponse;

public interface PostService 
{
	// Create
	
	PostDto createPost(PostDto postDto,Integer userId,Integer categoriesId);
	
	// update
	
PostDto updatePost(PostDto postDto,Integer postId);

// Delete

void deletePost(Integer postId);

////Get All Posts
PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);

////Get All Posts
//List<PostDto>getAllPost();


// Get Single Post

PostDto getPostById(Integer postId);

// Get All Posts By Categories

List<PostDto> getPostByCategories(Integer categoriesId);

// Get All Posts By User

List<PostDto>getPostsByUser(Integer userId);


// Search Posts By User

List<PostDto>searchByTitle(String keyword);

}