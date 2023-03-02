package com.blogging.Application.Payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto
{
	
private Integer categoryId;

@NotEmpty
@Size(min = 5,message = "write min 5 word in title")
private String categoryTitle;

@NotBlank
@Size(min=5,max = 50,message = "write min 5 word in Description, max write 50 letter")
private String categoryDescription;
}
