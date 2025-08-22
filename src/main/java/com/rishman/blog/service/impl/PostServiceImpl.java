package com.rishman.blog.service.impl;

import com.rishman.blog.entity.Category;
import com.rishman.blog.entity.Post;
import com.rishman.blog.entity.User;
import com.rishman.blog.exception.ResourceNotFoundException;
import com.rishman.blog.payload.PostDTO;
import com.rishman.blog.payload.PostResponse;
import com.rishman.blog.repository.CategoryDAO;
import com.rishman.blog.repository.PostDAO;
import com.rishman.blog.repository.UserDAO;
import com.rishman.blog.service.PostService;
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
    private PostDAO postDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    public PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId) {

        User user = userDAO.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        Category category = categoryDAO.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","id",categoryId));

        Post post = dtoToPost(postDTO);
        post.setPostImage("default.png");
        post.setPostAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        postDAO.save(post);
        return postToDTO(post);
    }

    @Override
    public PostDTO getPostById(Integer postId) {

        Post post = postDAO.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        return postToDTO(post);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Integer postId) {

        Post post = postDAO.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        post.setPostTitle(postDTO.getPostTitle());
        post.setPostContent(postDTO.getPostContent());
        post.setPostImage(postDTO.getPostImage());
        post.setPostAddedDate(new Date());
        postDAO.save(post);
        return postToDTO(post);
    }

    @Override
    public void deletePostById(Integer postId) {

        Post post = postDAO.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        postDAO.delete(post);
    }

    @Override
    public PostResponse getAllPosts(Integer pageNo, Integer pageSize, String sortBy, String order) {

        Sort sort = order.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable  = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> postsPage = postDAO.findAll(pageable);
        List<Post> posts = postsPage.getContent();

        List<PostDTO> postDTOs = posts.stream().map(this::postToDTO).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setPosts(postDTOs);
        postResponse.setPageNo(postsPage.getNumber());
        postResponse.setPageSize(postsPage.getSize());
        postResponse.setTotalPages(postsPage.getTotalPages());
        postResponse.setTotalItems(postsPage.getTotalElements());
        postResponse.setLastPage(postsPage.isLast());

        return postResponse;
    }

    @Override
    public PostResponse getPostsByCategory(Integer categoryId, Integer pageNo, Integer pageSize) {

        Pageable pageable  = PageRequest.of(pageNo, pageSize);
        Page<Post> postsPage = postDAO.findByCategory(categoryDAO.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId)), pageable);
        List<Post> posts = postsPage.getContent();

        List<PostDTO> postDTOs = posts.stream().map(this::postToDTO).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setPosts(postDTOs);
        postResponse.setPageNo(postsPage.getNumber());
        postResponse.setPageSize(postsPage.getSize());
        postResponse.setTotalPages(postsPage.getTotalPages());
        postResponse.setTotalItems(postsPage.getTotalElements());
        postResponse.setLastPage(postsPage.isLast());

        return postResponse;
    }

    @Override
    public PostResponse getPostsByUser(Integer userId, Integer pageNo, Integer pageSize) {

        Pageable pageable  = PageRequest.of(pageNo, pageSize);
        Page<Post> postsPage = postDAO.findByUser(userDAO.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId)), pageable);
        List<Post> posts = postsPage.getContent();

        List<PostDTO> postDTOs = posts.stream().map(this::postToDTO).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setPosts(postDTOs);
        postResponse.setPageNo(postsPage.getNumber());
        postResponse.setPageSize(postsPage.getSize());
        postResponse.setTotalPages(postsPage.getTotalPages());
        postResponse.setTotalItems(postsPage.getTotalElements());
        postResponse.setLastPage(postsPage.isLast());

        return postResponse;
    }

    @Override
    public List<PostDTO> getPostsByKeyword(String keyword) {

        return postDAO.findByTitle(keyword).stream().map(this::postToDTO).collect(Collectors.toList());
    }

    public Post dtoToPost(PostDTO postDTO){
        return modelMapper.map(postDTO, Post.class);
    }

    public PostDTO postToDTO(Post post){
        return modelMapper.map(post, PostDTO.class);
    }
}
