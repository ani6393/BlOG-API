package com.blogging.Application.Repositries;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogging.Application.Entities.User;

public interface UserRepo extends JpaRepository<User, Integer>
{

}
