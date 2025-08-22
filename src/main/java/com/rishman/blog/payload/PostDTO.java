package com.rishman.blog.payload;

import com.rishman.blog.entity.Category;
import com.rishman.blog.entity.Comment;
import com.rishman.blog.entity.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class PostDTO {

    private Integer postId;

    @NotBlank(message = "Post title cannot be blank")
    @Size(min = 3, max = 200, message = "Post title must be between 5 and 200 characters")
    private String postTitle;

    @NotBlank(message = "Post content cannot be blank")
    @Size(min = 20, message = "Post content must be at least 20 characters")
    private String postContent;

    @Size(max = 255, message = "Image URL must not exceed 255 characters")
    private String postImage;

    private Date postAddedDate;
    private CategoryDTO category;
    private UserDTO user;

    private List<CommentDTO> comments = new ArrayList<>();

}
