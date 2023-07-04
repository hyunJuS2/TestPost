package com.sparta.testpost.entity;

import com.sparta.testpost.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor //기본생성자

@Entity
@Table(name = "post")
public class Post extends Timestamped{
    // 아이디(식별자), 작성자, 제목, 내용, 날짜, 비밀번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "contents", length = 500) //null 값 허용
    private String contents;

    @Column(name = "title") //null 값 허용
    private String title;

    public Post(PostRequestDto requestDto) { // 처음 글을 등록할 때
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

    public Post(String username, PostRequestDto requestDto) {
        this.username = username;
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
    }

    public void update(PostRequestDto requestDto) { // 수정할 내용들
        //제목, 작성 내용을 수정
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

}
