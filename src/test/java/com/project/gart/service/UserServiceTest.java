package com.project.gart.service;

import com.project.gart.domain.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void join() {
        User user = User.builder()
                .email("hyuk@naver.com")
                .address("경기도")
                .name("혁키")
                .password("123")
                .birthday(Date.valueOf("1999-09-02"))
                .photo("ewqewqe")
                .build();

        assertThat(userService.save(user)).isEqualTo(user.getUserId());
    }

    @Test
    public void duplicateJoinTest() {
        User user = User.builder()
                .email("hyuk@naver.com")
                .address("경기도")
                .name("혁키")
                .password("123")
                .birthday(Date.valueOf("1999-09-02"))
                .photo("ewqewqe")
                .build();

        Long userId = null;
        try {
            userId = userService.save(user);
        } catch (Exception e) {
            System.out.println(e);
        }

        assertThat(userId).isNotNull();
    }

    @Test
    public void login() {
        User user = User.builder()
                .email("hyuk@naver.com")
                .address("경기도")
                .name("혁키")
                .password("123")
                .birthday(Date.valueOf("1999-09-02"))
                .photo("ewqewqe")
                .build();

        userService.save(user);

        assertThat(userService.login(user.getEmail(), user.getPassword())).isTrue();
        assertThat(userService.login(user.getEmail(), "fsdfdsfsd")).isFalse();
        assertThat(userService.login("qweqwer", "qweqewwqe")).isFalse();
    }
}