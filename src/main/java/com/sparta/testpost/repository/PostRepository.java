package com.sparta.testpost.repository;

import com.sparta.testpost.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {

//    List<Post> findAllByOrderByModifiedAtDesc();
    List<Post> findAllByOrderByCreatedAtDesc();
//    Optional<Post> findById(Long id); // 선택한 게시글(하나) 읽어오는 방법
}
