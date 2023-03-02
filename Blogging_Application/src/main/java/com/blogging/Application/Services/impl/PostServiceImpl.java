package com.blogging.Application.Services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.blogging.Application.Entities.Categories;
import com.blogging.Application.Entities.Post;
import com.blogging.Application.Entities.User;
import com.blogging.Application.Exceptions.ResourceNotFoundException;
import com.blogging.Application.Payloads.PostDto;
import com.blogging.Application.Payloads.PostResponse;
import com.blogging.Application.Repositries.CategoryRepo;
import com.blogging.Application.Repositries.PostRepo;
import com.blogging.Application.Repositries.UserRepo;
import com.blogging.Application.Services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	
	// Create Post
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoriesId) 
	
	{	
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User id", userId));
        Categories categories=this.categoryRepo.findById(categoriesId).orElseThrow(()->new ResourceNotFoundException("Categories","categories id",categoriesId));
		
        Post post=this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategories(categories);
		
		Post newPost = this.postRepo.save(post);
		
		return this.modelMapper.map(newPost,PostDto.class);
	}

	// Update Post
	
	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
	    Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "post id", postId));
	    post.setTitle(postDto.getTitle());
	    post.setContent(postDto.getContent());
	    post.setImageName(postDto.getImageName());
	    Post updatePost = this.postRepo.save(post);
	    return this.modelMapper.map(updatePost,PostDto.class);
	}

	// Delete Post
	
	@Override
	 public void deletePost(Integer postId) {
    Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "post id", postId));
		
		 this.postRepo.delete(post);
	}
	 
	// Get All Post
	
	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir)
{
		// use if else condition
		
//		Sort sort=null;
//		if(sortDir.equalsIgnoreCase("asc"))
//		{
//			sort= Sort.by(sortBy).ascending();
//		}
//		else 
//		{
//	      	sort=Sort.by(sortBy).descending();
//		}
//		
		// termery Operator
		
	Sort sort=(sortDir.equalsIgnoreCase("Asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		
		PageRequest pageable=PageRequest.of(pageNumber,pageSize,sort);
		Page<Post>pagePost=this.postRepo.findAll(pageable);
	List<Post>allPosts=pagePost.getContent();
	
		List<PostDto> collectAllPost =allPosts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(collectAllPost);
		postResponse.setPageNumber(pagePost.getNumber());
	    postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElement(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLasttPage(pagePost.isLast());
		return postResponse;
	}

//	
//	public List<PostDto> getAllPost()
//	{
//		List<Post> findAll = this.postRepo.findAll();
//		List<PostDto> allPost = findAll.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
//		return allPost;
//	}
//	
//	// Get Post By postId
//	
//	@Override
//	public PostDto getPostById(Integer postId) {
//
//		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "post id", postId));
//		
//		return this.modelMapper.map(post, PostDto.class);
//	}
	
	// Get Post BY Categories Id

	@Override
	public List<PostDto> getPostByCategories(Integer categoriesId)
	{
		Categories cat=this.categoryRepo.findById(categoriesId).orElseThrow(()->new ResourceNotFoundException("Categories", "categories id", categoriesId));
		List<Post> posts = this.postRepo.findByCategories(cat);
		List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}
	
	// Get Post BY user Id 
	
	@Override
	public List<PostDto> getPostsByUser(Integer userId) {

	User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "user id", userId));
		
		List<Post> posts = this.postRepo.findByUser(user);
		
		List<PostDto> collectPosts = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return collectPosts;
	}

	// Search Post
	
	@Override
	public List<PostDto> searchByTitle(String keyword) {

		List<Post> posts = this.postRepo.findByTitleContaining(keyword);
		List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
