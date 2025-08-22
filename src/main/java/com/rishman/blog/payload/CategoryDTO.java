package com.rishman.blog.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class CategoryDTO {

    private Integer categoryId;

    @NotBlank(message = "Category title cannot be blank")
    @Size(min = 3, max = 100, message = "Category title must be between 3 and 100 characters")
    private String categoryTitle;

    @NotBlank(message = "Category description cannot be blank")
    @Size(max = 500, message = "Category description must not exceed 500 characters")
    private String categoryDescription;
}
