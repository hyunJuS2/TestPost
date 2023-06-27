package com.sparta.testpost.controller;

import com.sparta.testpost.dto.PostRequestDto;
import com.sparta.testpost.dto.PostResponseDto;
import com.sparta.testpost.entity.Post;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PostController {
    private final Map<Long, Post> postList = new HashMap<>();

    @GetMapping("/posts")
    public List<PostResponseDto> getPosts() {
        List<PostResponseDto> responseDtoList = postList.values().stream().map(PostResponseDto::new).toList();
        return responseDtoList;
    }

    @PostMapping("/posts")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto) {
        //RequestDto -> Entity
        Post post = new Post(requestDto);
        // Post Max ID Check
        Long maxId = postList.size() > 0 ? Collections.max(postList.keySet()) + 1 : 1;
        // DB 저장
        postList.put(post.getId(), post);
        // Entity -> ResponseDto
        PostResponseDto postResponseDto = new PostResponseDto(post);
        return postResponseDto;
    }

    @PutMapping("/post/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        if (postList.containsKey(id)) {
            //해당 게시글 가져오기
            Post post = postList.get(id);
            //게시글 수정
            post.update(requestDto);
            return post.getId();
        } else {
            throw new IllegalArgumentException("선택하신 게시글은 존재하지 않습니다.");
        }
    }

    @DeleteMapping("/post/{id}")
    public Long deletePost(@PathVariable Long id) {
        if (postList.containsKey(id)) {
            //해당 게시글 삭제
            postList.remove(id);
            return id;
        } else throw new IllegalArgumentException("선택하신 게시글은 존재하지 않습니다.");

    }
}
