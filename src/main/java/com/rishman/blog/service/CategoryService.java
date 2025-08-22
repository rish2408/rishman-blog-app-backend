package com.rishman.blog.service;

import com.rishman.blog.payload.CategoryDTO;

import java.util.List;

public interface CategoryService {

    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO getCategoryById(Integer categoryId);
    CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId);
    void deleteCategoryById(Integer categoryId);
    List<CategoryDTO> getAllCategories();
}
