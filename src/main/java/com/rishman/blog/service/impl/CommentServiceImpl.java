package com.rishman.blog.service.impl;

import com.rishman.blog.entity.Comment;
import com.rishman.blog.entity.Post;
import com.rishman.blog.entity.User;
import com.rishman.blog.exception.ResourceNotFoundException;
import com.rishman.blog.payload.CommentDTO;
import com.rishman.blog.repository.CommentDAO;
import com.rishman.blog.repository.PostDAO;
import com.rishman.blog.repository.UserDAO;
import com.rishman.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private PostDAO postDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDTO createComment(CommentDTO commentDTO, Integer userId, Integer postId) {

        Post post = postDAO.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        User user = userDAO.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        Comment comment = dtoToComment(commentDTO);
        comment.setPost(post);
        comment.setUser(user);
        commentDAO.save(comment);

        return commentToDTO(comment);
    }

    @Override
    public void deleteCommentById(Integer commentId) {

        Comment comment = commentDAO.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
        commentDAO.delete(comment);
    }

    public Comment dtoToComment(CommentDTO commentDTO){
        return modelMapper.map(commentDTO, Comment.class);
    }

    public CommentDTO commentToDTO(Comment comment){
        return modelMapper.map(comment, CommentDTO.class);
    }
}
