package com.sparta.testpost.dto;

import com.sparta.testpost.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
    private Long id;
    private String username;
    private String title;
    private String contents;
    private LocalDateTime createdAt;

    public PostResponseDto(Post post) { // -> 보내줘야 하는 내용들
        this.id = post.getId();
        this.username = post.getUsername();
        this.contents = post.getContents();
        this.title = post.getTitle();
        this.createdAt = post.getCreatedAt();

    }
}
