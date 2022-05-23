package com.sam.blog.services.impl;

import com.sam.blog.entities.Category;
import com.sam.blog.exceptions.ResourceNotFoundException;
import com.sam.blog.payloads.CategoryDto;
import com.sam.blog.repositories.CategoryRepository;
import com.sam.blog.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        Category newCat = this.modelMapper.map(categoryDto, Category.class);
        Category newCategory = this.categoryRepository.save(newCat);
        return this.modelMapper.map(newCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {

        Category newCat = this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", id));

        newCat.setCategoryTitle(categoryDto.getCategoryTitle());
        newCat.setCategoryDesc(categoryDto.getCategoryDesc());
        Category updateCategory = this.categoryRepository.save(newCat);

        return this.modelMapper.map(updateCategory, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer id) {

        Category cat = this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", id));

        this.categoryRepository.delete(cat);
    }

    @Override
    public CategoryDto getCategory(Integer id) {

        Category cat = this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", id));

        return this.modelMapper.map(cat, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getCategory() {

        List<Category> cats = this.categoryRepository.findAll();

        List<CategoryDto> result = cats.stream().map((cat) -> this.modelMapper.map(cat, CategoryDto.class))
                .collect(Collectors.toList());
        return result;
    }
}
