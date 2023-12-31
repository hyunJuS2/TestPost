package com.sparta.testpost.controller;

import com.sparta.testpost.dto.ResultResponseDto;
import com.sparta.testpost.dto.PostRequestDto;
import com.sparta.testpost.dto.PostResponseDto;
import com.sparta.testpost.entity.User;
import com.sparta.testpost.jwt.JwtUtil;
import com.sparta.testpost.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostService postService;
    private final JwtUtil jwtUtil;

    // 게시글 전체 조회
    @GetMapping("/cm/posts")
    public List<PostResponseDto> getPosts() {
        return postService.getPosts();
    }


    // 게시글 등록
    @PostMapping("/posts")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto , @RequestAttribute("user") User user ) {
       return postService.createPost(requestDto,user.getUsername());
    }
    // 선택한 게시글 조회
//    @GetMapping("/posts/{id}")
//    public Optional<Post> getPost(@PathVariable Long id)  {
//        return postService.getPost(id);
//    }

    // 선택한 게시글 조회
    @GetMapping("/cm/post/{id}")
    public PostResponseDto getPost(@PathVariable Long id) {
        return postService.getPost(id);
    } // 선택한 id 값에 해당하는 데이터를 Get하는 방식으로 Request Parameter -> Path Variable 형식

    // 선택한 게시글 수정
    @PutMapping("/posts/{id}")
    public PostResponseDto updatePost(
            @PathVariable Long id,
            @RequestBody PostRequestDto requestDto,
            @RequestAttribute("user") User user
    ) {
        // post 객체는 AuthFilter에서 설정한 request attribute "post"로부터 전달 받습니다.

        // 게시글 수정 로직 수행
      return postService.updatePost(user,id,requestDto);
    }

    // 선택한 게시글 삭제
    @DeleteMapping("/posts/{id}")
    public ResultResponseDto deletePost(@PathVariable Long id, @RequestAttribute("user") User user) { // 해당하는 비밀번호 값만 불러오면 되니까
        return postService.deletePost(id,user);

    }
}
