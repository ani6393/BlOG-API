package com.blogging.Application.Entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@Getter
@Setter
public class Categories 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
public Integer categoryId;
		@Column(name = "Title",length = 100,nullable = false)
public String categoryTitle;
		@Column(name = "Description")
public String categoryDescription;
		
		@OneToMany(mappedBy = "categories", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
		private List<Post>posts=new ArrayList<>();

}
