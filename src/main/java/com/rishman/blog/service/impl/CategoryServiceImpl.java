package com.rishman.blog.service.impl;

import com.rishman.blog.entity.Category;
import com.rishman.blog.exception.ResourceNotFoundException;
import com.rishman.blog.payload.CategoryDTO;
import com.rishman.blog.repository.CategoryDAO;
import com.rishman.blog.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {

        Category category = dtoToCategory(categoryDTO);
        categoryDAO.save(category);
        return categoryToDTO(category);
    }

    @Override
    public CategoryDTO getCategoryById(Integer categoryId) {

        Category category = categoryDAO.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","id",categoryId));
        return categoryToDTO(category);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId) {

        Category category = categoryDAO.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","id",categoryId));
        category.setCategoryTitle(categoryDTO.getCategoryTitle());
        category.setCategoryDescription(categoryDTO.getCategoryDescription());
        categoryDAO.save(category);

        return categoryToDTO(category);
    }

    @Override
    public void deleteCategoryById(Integer categoryId) {

        Category category = categoryDAO.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","id",categoryId));
        categoryDAO.delete(category);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {

        List<Category> categories = categoryDAO.findAll();
        return categories.stream().map(this::categoryToDTO).collect(Collectors.toList());

    }

    public Category dtoToCategory(CategoryDTO categoryDTO){
        return modelMapper.map(categoryDTO, Category.class);
    }

    public CategoryDTO categoryToDTO(Category category){
        return modelMapper.map(category, CategoryDTO.class);
    }
}
