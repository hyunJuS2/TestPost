package com.sparta.testpost.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDto { //받아오는 내용들

    private String title;
    private String contents;
}

