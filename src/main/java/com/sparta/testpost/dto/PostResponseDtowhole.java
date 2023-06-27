package com.sparta.testpost.dto;

import com.sparta.testpost.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDtowhole { //출력 시 나타나야 할 것들만 -> 작성자, 제목, 작성내용, 작성날짜 를 조회 & 작성날짜를 기준으로 내림차순

    private String username;
    private String title;
    private String contents;
    private LocalDateTime createdAt;


    public PostResponseDtowhole(Post post) {
        this.username = post.getUsername();
        this.contents = post.getContents();
        this.title = post.getTitle();
        this.createdAt = post.getCreatedAt();

    }
}
