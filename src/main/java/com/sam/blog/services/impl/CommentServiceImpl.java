package com.sam.blog.services.impl;

import com.sam.blog.entities.Comments;
import com.sam.blog.entities.Post;
import com.sam.blog.exceptions.ResourceNotFoundException;
import com.sam.blog.payloads.CommentsDto;
import com.sam.blog.repositories.CommentsRepo;
import com.sam.blog.repositories.PostRepository;
import com.sam.blog.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentsRepo commentsRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentsDto createComment(CommentsDto dto, Integer postId) {

        Post post = this.postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post", "post_id", postId));

        Comments comments = this.modelMapper.map(dto, Comments.class);
        comments.setPost(post);

        Comments cmnt = this.commentsRepo.save(comments);

        return this.modelMapper.map(cmnt, CommentsDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {

        Comments cmnt = this.commentsRepo.findById(commentId)
                .orElseThrow(()->new ResourceNotFoundException("Comments", "commentId", commentId ));

        this.commentsRepo.delete(cmnt);
    }
}
