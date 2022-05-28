package com.sam.blog.controllers;

import com.sam.blog.config.AppConstant;
import com.sam.blog.payloads.ApiResponse;
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

    @GetMapping("/posts")
    public  ResponseEntity<List<PostDto>> getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstant.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstant.SORT_DIRECTION, required = false) String sortDir
            ){

        List<PostDto> postDtos = this.postService.getAllPosts(pageNumber, pageSize, sortBy, sortDir);

        return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
    }


    @GetMapping("/post/{postId}")
    public  ResponseEntity<PostDto> getPostById(@PathVariable("postId") Integer id){
        PostDto postDtos = this.postService.getPostById(id);
        return new ResponseEntity<PostDto>(postDtos, HttpStatus.OK);
    }

    @PutMapping("/post/{postId}")
    public ResponseEntity<PostDto>  updatePostById(@RequestBody PostDto postDto, @PathVariable("postId") Integer id){

        PostDto dto = this.postService.updatePost(postDto, id);
        return new ResponseEntity<PostDto>(dto, HttpStatus.OK);
    }
    @DeleteMapping("/post/{postId}")
    public ApiResponse deletePostById(@PathVariable("postId") Integer id){

        this.postService.deletePost(id);
        return new ApiResponse("Post is successfully deleted !!", true);
    }

    @GetMapping("/post/search/{keyword}")
    public ResponseEntity<List<PostDto>> searchByPostTitle(
        @PathVariable("keyword") String keyword){

        List<PostDto> dtos = this.postService.searchPost(keyword);
        return new ResponseEntity<List<PostDto>>(dtos, HttpStatus.OK);
    }
}

