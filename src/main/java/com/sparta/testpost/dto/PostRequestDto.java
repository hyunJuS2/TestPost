package com.sparta.testpost.dto;

import lombok.Getter;

@Getter
public class PostRequestDto {

    private String password;
    private String username;
    private String title;
    private String contents;
}
