package com.sam.blog.repositories;

import com.sam.blog.entities.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepo extends JpaRepository<Comments, Integer> {

}
