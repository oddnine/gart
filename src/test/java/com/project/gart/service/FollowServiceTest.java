package com.project.gart.service;

import com.project.gart.domain.User;
import com.project.gart.domain.dto.UserDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class FollowServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private FollowService followService;

    private User user;
    private User user1;
    private User user2;

    @BeforeEach
    void beforeEach() {
        user = User.builder().email("hyuk@naver.com").address("경기도").name("혁키").password("123").birthday(Date.valueOf("1999-09-02")).photo("ewqewqe").build();

        userService.save(user);

        user1 = User.builder().email("song@naver.com").address("경기도").name("송송").password("234").birthday(Date.valueOf("1999-07-10")).photo("aaa").build();

        userService.save(user1);

        user2 = User.builder().email("ju@naver.com").address("경기도").name("주주").password("345").birthday(Date.valueOf("1999-06-10")).photo("bbb").build();

        userService.save(user2);
    }

    @Test
    void 팔로잉_언팔로우() {
        followService.follow(user, user1);
        followService.follow(user, user2);

        assertThatThrownBy(() -> followService.follow(user, user1))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("이미 팔로잉 중입니다.");

        followService.follow(user1, user2);

        followService.follow(user2, user);
        followService.follow(user2, user1);

        followService.unFollow(user2, user);

        assertThat(followService.countByFollower(user)).isEqualTo(0L);
        assertThat(followService.countByFollower(user1)).isEqualTo(2L);
        assertThat(followService.countByFollower(user2)).isEqualTo(2L);

        assertThat(followService.countByFollowing(user)).isEqualTo(2L);
        assertThat(followService.countByFollowing(user1)).isEqualTo(1L);
        assertThat(followService.countByFollowing(user2)).isEqualTo(1L);

    }

    @Test
    void 팔로워_조회() {
        followService.follow(user, user1);
        followService.follow(user, user2);

        followService.follow(user1, user2);

        followService.follow(user2, user);
        followService.follow(user2, user1);

        assertThat(followService.findFollowerList(user1).size()).isEqualTo(2);
    }
}