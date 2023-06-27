package com.sparta.testpost.entity;

import com.sparta.testpost.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor //기본생성자

public class Post {
    // 아이디(식별자), 작성자, 제목, 내용, 날짜, 비밀번호
    private Long id;
    private String passoword;
    private String username;
    private String contents;
    private String title;

    public Post(PostRequestDto requestDto) {
        this.passoword = requestDto.getPassword();
        this.username = requestDto.getUsername();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

    public void update(PostRequestDto requestDto) {
        //제목, 작성자명, 작성 내용을 수정
        this.username = requestDto.getUsername();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }
}
