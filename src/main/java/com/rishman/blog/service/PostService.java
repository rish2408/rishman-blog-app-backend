package com.rishman.blog.service;

import com.rishman.blog.payload.PostDTO;
import com.rishman.blog.payload.PostResponse;

import java.util.List;

public interface PostService {

    PostDTO createPost(PostDTO postDTO,Integer userId, Integer categoryId);
    PostDTO getPostById(Integer postId);
    PostDTO updatePost(PostDTO postDTO, Integer postId);
    void deletePostById(Integer postId);
    PostResponse getAllPosts(Integer pageNo, Integer pageSize, String sortBy, String order);
    PostResponse getPostsByCategory(Integer categoryId, Integer pageNo, Integer pageSize);
    PostResponse getPostsByUser(Integer userId, Integer pageNo, Integer pageSize);
    List<PostDTO> getPostsByKeyword(String keyword);
}
