package com.sam.blog.services;

import com.sam.blog.entities.Post;
import com.sam.blog.payloads.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    Post updatePost(PostDto postDto, Integer id);

    void deletePost(Integer id);

    List<PostDto> getAllPosts();

    PostDto getPostById(Integer id);

    List<PostDto> getPostByCategory(Integer id);

    List<PostDto> getPostByUser(Integer id);

    List<Post> searchPost(String keyword);
}
