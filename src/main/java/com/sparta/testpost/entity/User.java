package com.sparta.testpost.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor //기본생성자
@Entity
@Table(name = "user")
public class User {
    // (식별자) 작성자, 비밀번호

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Id
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false, unique = true)
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}


