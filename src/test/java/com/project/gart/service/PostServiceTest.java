package com.project.gart.service;

import com.project.gart.domain.Post;
import com.project.gart.domain.User;
import com.project.gart.domain.Work;
import com.project.gart.domain.dto.PostDto;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostServiceTest {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private WorkService workService;

    private User user;
    private Work work;
    private Work work1;
    private Work work2;
    private Post post;
    private Post post1;
    private Post post2;

    @BeforeEach
    void beforeEach() {
        user = User.builder().email("hyuk@naver.com").address("경기도").name("혁키").password("123").birthday(Date.valueOf("1999-09-02")).photo("ewqewqe").build();

        userService.save(user);

        work = Work.builder().workName("작품1").workResource("qwewqe").workCategory("미술").workDescription("작품입니다.").isDelete(false).build();

        work1 = Work.builder().workName("작품2").workResource("qqwee").workCategory("음악").workDescription("음악입니다.").isDelete(false).build();

        work2 = Work.builder().workName("작품3").workResource("qqewqeqwwee").workCategory("미술").workDescription("작품2입니다.").isDelete(false).build();

        workService.save(user, work);

        workService.save(user, work1);

        workService.save(user, work2);

        post = Post.builder()
                .postTitle("내 작품 보시오")
                .postDescription("설명")
                .fkUserId(user)
                .fkWorkId(work).build();

        postService.save(post);

        post1 = Post.builder()
                .postTitle("내 작품 보시오1111111")
                .postDescription("설명1111111")
                .fkUserId(user)
                .fkWorkId(work1).build();

        postService.save(post1);

        post2 = Post.builder()
                .postTitle("내 작품 보시오2222222")
                .postDescription("설명22222222")
                .fkUserId(user)
                .fkWorkId(work2).build();

        postService.save(post2);
    }

    @Test
    public void 이름으로_게시글_찾기() {
        assertThat(postService.findByUserName(user.getName()).size()).isEqualTo(3);
    }

    @Test
    public void 이메일로_게시글_찾기() {
        assertThat(postService.findByUserEmail(user.getEmail()).size()).isEqualTo(3);
    }
}