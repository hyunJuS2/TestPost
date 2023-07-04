package com.sparta.testpost.service;

import com.sparta.testpost.dto.DeleteResponseDto;
import com.sparta.testpost.dto.PostRequestDto;
import com.sparta.testpost.dto.PostResponseDto;
import com.sparta.testpost.entity.Post;
import com.sparta.testpost.entity.User;
import com.sparta.testpost.jwt.JwtUtil;
import com.sparta.testpost.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final JwtUtil jwtUtil;


    public PostResponseDto createPost(PostRequestDto requestDto, String username) {

        // RequestDto -> Entity
        Post post = new Post(requestDto, username);
        // DB 저장
        Post savePost = postRepository.save(post);
        // Entity -> ResponseDto
        PostResponseDto postResponseDto = new PostResponseDto(post);
        return postResponseDto;

    }
    //게시글 전체 조회 로직
    public List<PostResponseDto> getPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc().stream().map(PostResponseDto::new).toList();
    }

    // 선택한 게시글 조회 로직
//    public Optional<Post> getPost(Long id) {
//        return postRepository.findById(id);
//    }

    public PostResponseDto getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        PostResponseDto postResponseDto = new PostResponseDto(post);
        return postResponseDto;
    }


    @Transactional
    public PostResponseDto updatePost(User user, Long id, PostRequestDto requestDto)  {
        // 해당 ID 일치하는 게시글 조회
        Optional<Post> optionalPost = postRepository.findById(id);
        if(optionalPost.isPresent()) {
            Post postUp = optionalPost.get();
            // 게시글 작성자와 현재 사용자가 동일한지 확인
            if (postUp.getUsername().equals(user.getUsername())) {
                // 게시글 수정
                postUp.update(requestDto);
                // 객체에 담아서 return 하기
                PostResponseDto postResponseDto = new PostResponseDto(postUp);
                return postResponseDto;
            } else {
                throw new IllegalArgumentException("접근이 거부되었습니다.");
            }
        } else {
            throw new IllegalArgumentException("해당 게시글을 찾을 수 없습니다.");
        }
    }

    //게시글 삭제 로직
    @Transactional
    public DeleteResponseDto deletePost(Long id , User user ) {

        DeleteResponseDto deleteResponseDto = new DeleteResponseDto();

        // 해당 ID 일치하는 게시글 조회
        Optional<Post> optionalPost = postRepository.findById(id);
        if(optionalPost.isPresent()) {
            Post postDel = optionalPost.get();
            // 토큰에 저장된 사용자명과 일치하는 게시글 조회
            if(postDel.getUsername().equals(user.getUsername())) {
                // 게시글 삭제
                postRepository.delete(postDel);
                deleteResponseDto.setMsg("게시글 삭제 성공");
                deleteResponseDto.setStatusCode(200);


            } else {
                throw new IllegalArgumentException("접근이 거부되었습니다.");
            }
        } else {
            throw new IllegalArgumentException("해당 게시글을 찾을 수 없습니다.");
        }
        return deleteResponseDto;
    }



// 윤상님🌟
//    public Map<String,String> deletePosts(Long postId, String password) {
//        Post post = postRepository.findById(postId)
//                .orElseThrow(() -> new IllegalArgumentException("Post Not Found"));
//
//        if (!post.getPassword().equals(password)) {
//            throw new IllegalArgumentException("The entered password does not matched");
//        }
//        postRepository.delete(post);
//        return Collections.singletonMap("success","true");
//    }


}
