package com.project.gart.repository;

import com.project.gart.domain.User;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void save() {
        User user = User.builder()
                .email("hyuk@naver.com")
                .address("경기도")
                .name("혁키")
                .password("123")
                .birthday(Date.valueOf("1999-09-02"))
                .photo("ewqewqe")
                .build();

        userRepository.save(user);

        assertThat(user.getUserId()).isNotNull();
    }
}