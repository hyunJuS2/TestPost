package com.sparta.testpost.service;

import com.sparta.testpost.dto.PostRequestDto;
import com.sparta.testpost.dto.PostResponseDto;
import com.sparta.testpost.dto.PostResponseDtowhole;
import com.sparta.testpost.entity.Post;
import com.sparta.testpost.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostResponseDto createPost(PostRequestDto requestDto) {
        // RequestDto -> Entity
        Post post = new Post(requestDto);
        // DB 저장
        Post savePost = postRepository.save(post);
        // Entity -> ResponseDto
        PostResponseDto postResponseDto = new PostResponseDto(post);
        return postResponseDto;

    }
    //게시글 전체 조회 로직
    public List<PostResponseDtowhole> getPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc().stream().map(PostResponseDtowhole::new).toList();
    }

    // 선택한 게시글 조회 로직
    public Optional<Post> getPost(Long id) {
        return postRepository.findById(id);
    }


    // 게시글 수정 로직
    @Transactional
    public Long updatePost(Long id,PostRequestDto requestDto)  {
        //해당 게시글이 DB에 존재하는지 확인
        Post post = findPost(id, requestDto.getPassword());
        // 게시글 수정
        post.update(requestDto);
        return id;
    }

    //게시글 삭제 로직
    @Transactional
    public Long deletePost(Long id , PostRequestDto requestDto) {
        //해당 게시글이 DB에 존재하는지 확인
        Post post;
        post = findPost(id,requestDto.getPassword());
        // 게시글 삭제
        postRepository.delete(post);
        return id;
    }
    //게시글의 존재여부 확인
    private Post findPost(Long id,String password){
        Post post = postRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("선택하신 게시글은 존재하지 않습니다."));
        String postPassword = post.getPassword(); // 게시글에 등록된 비밀번호를 가져온다.
        if(!postPassword.equals(password)){
            throw  new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return post;
    }


//    private Post findPost(Long id, String password){
//        Optional<Post> post =  postRepository.findById(id);
//        if(post.isPresent()){
//            return postRepository.findByPassoword(password).orElseThrow(()->
//                    new IllegalArgumentException("Password가 일치하지 않습니다."));
//        } else throw new IllegalArgumentException("선택하신 게시글은 존재하지 않습니다.");
//
//    }
}
