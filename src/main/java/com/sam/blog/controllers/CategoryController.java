package com.sam.blog.controllers;

import com.sam.blog.payloads.ApiResponse;
import com.sam.blog.payloads.CategoryDto;
import com.sam.blog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto catDto){

        CategoryDto cat = this.categoryService.createCategory(catDto);
        return new ResponseEntity<CategoryDto>(cat, HttpStatus.CREATED);
    }

    @PutMapping("/{catId}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto catDto,
                                                      @PathVariable("catId") Integer id){

        CategoryDto categoryDto = this.categoryService.updateCategory(catDto, id);

        return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);
    }

    @DeleteMapping("/{catId}")
    public ResponseEntity<?> delteCategory(@PathVariable("catId") Integer id) {

        this.categoryService.deleteCategory(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully", true),
                HttpStatus.OK);
    }

    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("catId") Integer id) {

        CategoryDto categoryDto = this.categoryService.getCategory(id);
        return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategory() {

        List<CategoryDto> categoryDto = this.categoryService.getCategory();
        return   ResponseEntity.ok(categoryDto);
    }

}
