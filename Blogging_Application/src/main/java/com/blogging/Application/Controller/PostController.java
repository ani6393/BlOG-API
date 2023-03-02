package com.blogging.Application.Controller;
import java.io.IOException;
import java.util.List;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blogging.Application.BlogConstant.AppConstant;
import com.blogging.Application.Payloads.ApiResponse;
import com.blogging.Application.Payloads.FileResponse;
import com.blogging.Application.Payloads.PostDto;
import com.blogging.Application.Payloads.PostResponse;
import com.blogging.Application.Services.FileService;
import com.blogging.Application.Services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController 
{
	
	@Autowired
    private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	
	// Create

	@PostMapping("/users/{userId}/categories/{categoriesId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,@PathVariable Integer userId,@PathVariable Integer categoriesId)
	{	
		PostDto createPost = this.postService.createPost(postDto, userId, categoriesId);
	return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}
	
//	// Get All Posts
//	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse>getAllPost(
			@RequestParam(value = "pageNumber",defaultValue = AppConstant.PAGE_NUMBER,required = false)Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue =AppConstant.PAGE_SIZE ,required = false)Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = AppConstant.SORT_BY,required = false)String sortBy,
			@RequestParam(value = "sortDir",defaultValue = AppConstant.SORT_DIR,required = false)String sortDir)
	{
		PostResponse postResponse = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
				
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	}
	
//	@GetMapping("/posts")
//	public ResponseEntity<List<PostDto>>getAllPost(){
//		List<PostDto> allPost = this.postService.getAllPost();
//		return new ResponseEntity<List<PostDto>>(allPost,HttpStatus.OK);
//	}
	
	
	// Get Post By User
	
	@GetMapping("/users/{userId}/posts")
	public ResponseEntity<List<PostDto>>getPOstByUser(@PathVariable Integer userId)
	{
		List<PostDto> allPosts = this.postService.getPostsByUser(userId);
	return new ResponseEntity<List<PostDto>>(allPosts,HttpStatus.OK);
	}
	
	
	// Get Post details Post Id
	
		@GetMapping("/posts/{postId}")
		public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId)
		{
		PostDto post = this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(post,HttpStatus.OK);
		}
	
	// Get Post By Category
	
		@GetMapping("/categories/{categoriesId}/posts")
		public ResponseEntity<List<PostDto>>getPostByCategories(@PathVariable Integer categoriesId)
		{
			List<PostDto> allPosts =this.postService.getPostByCategories(categoriesId);
		return new ResponseEntity<List<PostDto>>(allPosts,HttpStatus.OK);
		}
		
		
		// Delete Post

		@DeleteMapping("/posts/{postId}")
		public ResponseEntity<ApiResponse>deletePostById(@PathVariable Integer postId)
		{
			 this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post is Deleted sucessfully !!",true),HttpStatus.OK );
		}
		
		// Update Post
		
		
		@PutMapping("/posts/{postId}")
		public ResponseEntity<PostDto>updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId)
		{
			 PostDto updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK );
		}
	
	// Searching
		@GetMapping("/posts/search/{keywords}")
		public ResponseEntity<List<PostDto>>searchByTitle(@PathVariable("keywords")String keywords)
				{
			List<PostDto> result = this.postService.searchByTitle(keywords);
			return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
			
			}
	
		// Post image Upload
		@PostMapping("/post/image/upload/{postId}")
		public ResponseEntity<PostDto>uploadPostImage(@RequestParam("image")MultipartFile image,
				@PathVariable Integer postId) throws IOException
		{
			PostDto postDto = this.postService.getPostById(postId);
			String fileName = this.fileService.uploadImage(path,image);
			postDto.setImageName(fileName);
			PostDto updatePost = this.postService.updatePost(postDto, postId);
			return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
			
//	@PostMapping("image")
//		public ResponseEntity<FileResponse>fileUpload(@RequestParam("image")MultipartFile image) throws IOException
//	{
//		String fileName=this.fileService.uploadImage(path, image);
//	return new ResponseEntity<>(new FileResponse( fileName,"Ok"),HttpStatus.OK);
			
		}
}
