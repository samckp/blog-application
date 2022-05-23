package com.sam.blog.services;

import com.sam.blog.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto, Integer id);

    void deleteCategory(Integer id);

    CategoryDto getCategory(Integer id);

    List<CategoryDto> getCategory();
}
