package com.sam.blog.repositories;

import com.sam.blog.entities.Category;
import com.sam.blog.payloads.CategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
