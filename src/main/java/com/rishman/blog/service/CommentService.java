package com.rishman.blog.service;

import com.rishman.blog.payload.CommentDTO;

public interface CommentService {

    CommentDTO createComment(CommentDTO commentDTO, Integer userId, Integer postId);
    void deleteCommentById(Integer commentId);
}
