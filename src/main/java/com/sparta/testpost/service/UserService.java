package com.sparta.testpost.service;

import com.sparta.testpost.dto.LoginRequestDto;
import com.sparta.testpost.dto.SignupRequestDto;
import com.sparta.testpost.entity.Post;
import com.sparta.testpost.jwt.JwtUtil;
import com.sparta.testpost.repository.PostRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PostRepository postRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    //회원가입
    public void signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());

        // 회원 중복 확인
        Optional<Post> checkUsername = postRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        // 사용자 등록
        Post post = new Post(username, password);
        postRepository.save(post);
    }

    //로그인
    public void login(LoginRequestDto requestDto, HttpServletResponse res) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        //사용자가 존재하는지 확인
        Post post = postRepository.findByUsername(username).orElseThrow(()->
                new IllegalArgumentException("등록된 사용자가 없습니다."));

        //비밀번호 확인
        if(!passwordEncoder.matches(password, post.getPassword())){
           throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        //JWT 생성 및 쿠키에 저장 후 Response 객체에 추가
        String token = jwtUtil.createToken(post.getUsername());
        jwtUtil.addJwtToCookie(token,res);
    }
}
