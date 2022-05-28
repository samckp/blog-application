package com.sam.blog.services.impl;

import com.sam.blog.entities.Category;
import com.sam.blog.entities.Post;
import com.sam.blog.entities.User;
import com.sam.blog.exceptions.ResourceNotFoundException;
import com.sam.blog.payloads.PostDto;
import com.sam.blog.repositories.CategoryRepository;
import com.sam.blog.repositories.PostRepository;
import com.sam.blog.repositories.UserRepository;
import com.sam.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

        User user = this.userRepository.findById(userId).orElseThrow(
                ()-> new ResourceNotFoundException("User","id",userId)
        );

        Category newCat = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

        Post post = this.modelMapper.map(postDto, Post.class);
        post.setAddedDate(new Date());
        post.setImageName("default.png");

        post.setUser(user);
        post.setCategory(newCat);

        Post newpost = this.postRepository.save(post);

        return this.modelMapper.map(newpost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer id) {

        Post post = this.postRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Post", "post_Id", id));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

        Post updatedPost = this.postRepository.save(post);
        return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer id) {

        Post post = this.postRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Post", "post_Id", id));
        this.postRepository.delete(post);
    }

    @Override
    public List<PostDto> getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        Sort sort = (sortDir.equalsIgnoreCase("asc")
                    ? Sort.by(sortBy).ascending()
                    : Sort.by(sortBy).descending());

        Pageable p = PageRequest.of(pageNumber, pageSize, sort);

        Page<Post> posts = this.postRepository.findAll(p);
        List<Post> allPost = posts.getContent();

        List<PostDto> postDto = allPost.stream().map((post) ->
                    this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDto;
    }

    @Override
    public PostDto getPostById(Integer id) {

        Post post = this.postRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Post", "post_Id", id));
        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getPostByCategory(Integer id) {

        Category newCat = this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", id));

        List<Post> posts = this.postRepository.findByCategory(newCat);

        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(
                post, PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> getPostByUser(Integer id) {

        User user = this.userRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("User", "id", id));

        List<Post> posts = this.postRepository.findByUser(user);
        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(
                post, PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> searchPost(String keyword) {

        List<Post> posts = this.postRepository.searchByTitle("%"+keyword+"%");
        List<PostDto> dtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
        return dtos;
    }
}
