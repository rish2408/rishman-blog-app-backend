package com.rishman.blog.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class PostResponse {

    private List<PostDTO> posts;
    private int pageNo;
    private int pageSize;
    private int totalPages;
    private long totalItems;
    private boolean lastPage;
}
