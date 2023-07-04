package com.sparta.testpost.repository;

import com.sparta.testpost.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepostiory extends JpaRepository<User,Long>{

    Optional<User> findByUsername(String username); // 이름 조회
}