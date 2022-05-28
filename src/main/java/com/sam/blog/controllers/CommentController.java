package com.sam.blog.controllers;

import com.sam.blog.payloads.ApiResponse;
import com.sam.blog.payloads.CommentsDto;
import com.sam.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentsDto> createComment(@RequestBody CommentsDto dto,
                                                     @PathVariable("postId") Integer postId){

        CommentsDto commentsDto = commentService.createComment(dto, postId);

        return new ResponseEntity<CommentsDto>(commentsDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable("commentId") Integer comntId){

        commentService.deleteComment(comntId);

        return new ResponseEntity<ApiResponse>(
                new ApiResponse("Comment deleted successfully",
                        true), HttpStatus.OK);
    }

}
