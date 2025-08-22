package com.rishman.blog.controller;

import com.rishman.blog.config.AppConstants;
import com.rishman.blog.entity.Post;
import com.rishman.blog.payload.PostDTO;
import com.rishman.blog.payload.PostResponse;
import com.rishman.blog.service.FileService;
import com.rishman.blog.service.PostService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO, @PathVariable Integer userId, @PathVariable Integer categoryId){
        PostDTO createPostDTO = postService.createPost(postDTO, userId, categoryId);
        return new ResponseEntity<PostDTO>(createPostDTO, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<PostResponse> getPostsByUser(@PathVariable Integer userId,
                                                       @RequestParam(defaultValue = AppConstants.PAGE_NUMBER) Integer pageNo,
                                                       @RequestParam(defaultValue = AppConstants.PAGE_SIZE) Integer pageSize){

        PostResponse postsByUser = postService.getPostsByUser(userId, pageNo, pageSize);
        return new ResponseEntity<PostResponse>(postsByUser, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<PostResponse> getPostsByCategory(@PathVariable Integer categoryId,
                                                           @RequestParam(defaultValue = AppConstants.PAGE_NUMBER) Integer pageNo,
                                                           @RequestParam(defaultValue = AppConstants.PAGE_SIZE) Integer pageSize){

        PostResponse postsByCategory = postService.getPostsByCategory(categoryId, pageNo, pageSize);
        return new ResponseEntity<PostResponse>(postsByCategory, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(defaultValue = AppConstants.PAGE_NUMBER) Integer pageNo,
            @RequestParam(defaultValue = AppConstants.PAGE_SIZE) Integer pageSize,
            @RequestParam(defaultValue = AppConstants.SORT_BY) String sortBy,
            @RequestParam(defaultValue = AppConstants.ORDER) String order){

        PostResponse allPosts = postService.getAllPosts(pageNo, pageSize, sortBy, order);
        return new ResponseEntity<PostResponse>(allPosts, HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId){
        PostDTO getPostByIdDTO = postService.getPostById(postId);
        return new ResponseEntity<PostDTO>(getPostByIdDTO, HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> updatePost(@Valid @RequestBody PostDTO postDTO, @PathVariable Integer postId){
        PostDTO updatePostDTO = postService.updatePost(postDTO, postId);
        return new ResponseEntity<PostDTO>(updatePostDTO, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<Void> deletePostById(@PathVariable Integer postId){
        postService.deletePostById(postId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/posts/search/{keyword}")
    public ResponseEntity<List<PostDTO>> getPostsByKeyword(@PathVariable String keyword){
        List<PostDTO> postsByKeyword = postService.getPostsByKeyword(keyword);
        return new ResponseEntity<List<PostDTO>>(postsByKeyword, HttpStatus.OK);
    }

    @PostMapping("post/image/upload/{postId}")
    public ResponseEntity<PostDTO> uploadPostImage(@PathVariable Integer postId,
                                                   @RequestParam("file") MultipartFile file) throws IOException {

        PostDTO postById = postService.getPostById(postId);
        String fileName = fileService.uploadImage(path, file);
        postById.setPostImage(fileName);
        PostDTO updatedpostDTO = postService.updatePost(postById, postId);

        return new ResponseEntity<PostDTO>(updatedpostDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadPostImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {

        InputStream resource = fileService.getImage(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }
}
