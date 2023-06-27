package com.sparta.testpost.controller;

import com.sparta.testpost.dto.PostRequestDto;
import com.sparta.testpost.dto.PostResponseDto;
import com.sparta.testpost.dto.PostResponseDtowhole;
import com.sparta.testpost.entity.Post;
import com.sparta.testpost.service.PostService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 게시글 전체 조회
    @GetMapping("/posts")
    public List<PostResponseDto> getPosts() {
        return postService.getPosts();
    }
    // 게시글 등록
    @PostMapping("/posts")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto) {
        return postService.createPost(requestDto);
    }
    // 선택한 게시글 조회
//    @GetMapping("/posts/{id}")
//    public Optional<Post> getPost(@PathVariable Long id)  {
//        return postService.getPost(id);
//    }

    // 선택한 게시글 조회
    @GetMapping("/posts/{id}")
    public PostResponseDto getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    // 선택한 게시글 수정
    @PutMapping("/posts/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.updatePost(id, requestDto);
    }

    // 선택한 게시글 삭제
    @DeleteMapping("/posts/{id}")
    public Map<String, String> deletePost(@PathVariable Long id, @RequestBody Map<String,String> password) {
        // password = {"password":"77878"}
        return postService.deletePost(id ,password.get("password"));
    }
}
