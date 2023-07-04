package com.sparta.testpost.service;

import com.sparta.testpost.dto.LoginRequestDto;
import com.sparta.testpost.dto.SignupRequestDto;
import com.sparta.testpost.entity.Post;
import com.sparta.testpost.entity.User;
import com.sparta.testpost.jwt.JwtUtil;
import com.sparta.testpost.repository.PostRepository;
import com.sparta.testpost.repository.UserRepostiory;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepostiory userRepostiory;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    //회원가입
    public void signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());

        // 회원 중복 확인
        Optional<User> checkUsername = userRepostiory.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        // 사용자 등록
        User user = new User(username, password);
        userRepostiory.save(user);
    }

    //로그인
    public void login(LoginRequestDto requestDto, HttpServletResponse res) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        //사용자가 존재하는지 확인
        User user = userRepostiory.findByUsername(username).orElseThrow(()->
                new IllegalArgumentException("등록된 사용자가 없습니다."));

        //비밀번호 확인
        if(!passwordEncoder.matches(password, user.getPassword())){
           throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        //JWT 생성 및 쿠키에 저장 후 Response 객체에 추가
        String token = jwtUtil.createToken(user.getUsername());
        jwtUtil.addJwtToCookie(token,res);
    }
}
