package com.rishman.blog.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDTO {

    private Integer commentId;
    private String commentContent;
    private Integer userId;
}
