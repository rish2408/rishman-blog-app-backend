package com.rishman.blog.repository;

import com.rishman.blog.entity.Category;
import com.rishman.blog.entity.Post;
import com.rishman.blog.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostDAO extends JpaRepository<Post, Integer> {

    Page<Post> findByUser(User user, Pageable pageable);
    Page<Post> findByCategory(Category category, Pageable pageable);

    @Query("SELECT p FROM Post p WHERE p.postTitle LIKE %?1%")
    List<Post> findByTitle(String title);
}
