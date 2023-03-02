package com.blogging.Application.Repositries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogging.Application.Entities.Categories;
import com.blogging.Application.Entities.Post;
import com.blogging.Application.Entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>
{
List<Post> findByUser(User user);
List<Post> findByCategories(Categories categories);

// for Searching data
List<Post> findByTitleContaining(String title);

}
