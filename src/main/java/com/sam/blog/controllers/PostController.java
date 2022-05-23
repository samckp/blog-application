package com.sam.blog.controllers;

import com.sam.blog.payloads.PostDto;
import com.sam.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/user/{userid}/category/{categoryId}/post")
    public ResponseEntity<PostDto> createPost(
                    @RequestBody PostDto postDto,
                    @PathVariable("userid") Integer userid,
                    @PathVariable("categoryId") Integer categoryId){

        PostDto newDto = this.postService.createPost(postDto, userid, categoryId);

        return new ResponseEntity<PostDto>(newDto, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(
            @PathVariable("userId") Integer userId){

        List<PostDto> postDtos = this.postService.getPostByUser(userId);

        return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostBycategory(
            @PathVariable("categoryId") Integer categoryId){

        List<PostDto> postDtos = this.postService.getPostByCategory(categoryId);

        return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
    }
}

