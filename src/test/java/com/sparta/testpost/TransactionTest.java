package com.sparta.testpost;

import com.sparta.testpost.entity.Post;
import com.sparta.testpost.repository.PostRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class TransactionTest {
    @PersistenceContext
    EntityManager em;

    @Autowired
    PostRepository postRepository;

    @Test
    @Transactional
    @Rollback(value = false) // 테스트 코드에서 @Transactional 를 사용하면 테스트가 완료된 후 롤백하기 때문에 false 옵션 추가
    @DisplayName("게시글 생성 성공")
    void test1() {
        Post post = new Post();
        post.setUsername("sisley");
        post.setPassword("55555");
        post.setTitle("테스트5");
        post.setContents("게시글 테스트 5");

        em.persist(post);  // 영속성 컨텍스트에 메모 Entity 객체를 저장합니다.

    }


}
