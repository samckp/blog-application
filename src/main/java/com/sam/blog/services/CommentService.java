package com.sam.blog.services;

import com.sam.blog.payloads.CommentsDto;

public interface CommentService {

        CommentsDto createComment(CommentsDto dto, Integer postId);
        void deleteComment(Integer commentId);
}
