package com.sparta.testpost.dto;

import com.sparta.testpost.entity.Post;
import lombok.Getter;

@Getter
public class PostResponseDto {
    private Long id;
    private String password;
    private String username;
    private String title;
    private String contents;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.username = post.getUsername();
        this.contents = post.getContents();
        this.password = post.getPassoword();
        this.title = post.getTitle();
    }
}
