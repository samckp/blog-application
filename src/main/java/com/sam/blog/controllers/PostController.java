package com.sam.blog.controllers;

import com.sam.blog.payloads.PostDto;
import com.sam.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/userid/{userid}/categoryid/{categoryid}/post")
    public ResponseEntity<PostDto> createPost(
                    @RequestBody PostDto postDto,
                    @PathVariable("userid") Integer userid,
                    @PathVariable("categoryid") Integer categoryid){

        PostDto newDto = this.postService.createPost(postDto, userid, categoryid);

        return new ResponseEntity<PostDto>(newDto, HttpStatus.CREATED);
    }
}
