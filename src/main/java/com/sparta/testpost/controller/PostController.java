package com.sparta.testpost.controller;

import com.sparta.testpost.dto.PostRequestDto;
import com.sparta.testpost.dto.PostResponseDto;
import com.sparta.testpost.entity.Post;
import com.sparta.testpost.jwt.JwtUtil;
import com.sparta.testpost.service.PostService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {
    private final PostService postService;
    private final JwtUtil jwtUtil;

    // 게시글 전체 조회
    @GetMapping("/posts")
    public List<PostResponseDto> getPosts() {
        return postService.getPosts();
    }
    // 게시글 등록
    @PostMapping("/posts")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto , @RequestAttribute("post") Post post ) {
       return postService.createPost(requestDto,post.getUsername());
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
    } // 선택한 id 값에 해당하는 데이터를 Get하는 방식으로 Request Parameter -> Path Variable 형식

    // 선택한 게시글 수정
    @PutMapping("/posts/{id}")
    public PostResponseDto updatePost(
            @PathVariable Long id,
            @RequestBody PostRequestDto requestDto,
            @RequestAttribute("post") Post post
//            @RequestAttribute("username") String username
    ) {
        // post 객체는 AuthFilter에서 설정한 request attribute "post"로부터 전달 받습니다.

        // 게시글 수정 로직 수행
        return postService.updatePost(post,id,requestDto);


    }

    // 선택한 게시글 삭제
    @DeleteMapping("/posts/{id}")
    public Map<String, String> deletePost(@PathVariable Long id, @RequestBody Map<String,String> password) { // 해당하는 비밀번호 값만 불러오면 되니까
        // password = {"password":"77878"}
        return  null;
//        return postService.deletePost(id ,password.get("password"));
    }
}
