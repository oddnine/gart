package com.project.gart.service;

import com.project.gart.domain.User;
import com.project.gart.domain.Work;
import com.project.gart.domain.dto.WorkDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class WorkServiceTest {
    @Autowired
    private WorkService workService;

    @Autowired
    private UserService userService;

    private User user;

    @BeforeEach
    public void beforeEach() {
        user = User.builder().email("hyuk@naver.com").address("경기도").name("혁키").password("123").birthday(Date.valueOf("1999-09-02")).photo("ewqewqe").build();

        userService.save(user);
    }

    @Test
    public void uploadAndUpdateWorkTest() {
        Work work = Work.builder().workName("작품1").workResource("qwewqe").workCategory("미술").workDescription("작품입니다.").isDelete(false).build();

        workService.save(user, work);

        Work update = Work.builder().workName("작품1").workResource("qwewqe").workCategory("음악").workDescription("음악 작품입니다.").isDelete(false).build();

        workService.updateWork(work, update);

        WorkDto find = workService.findByWorkId(work.getWorkId());

        assertThat(find.getWorkCategory()).isEqualTo("음악");
    }

    @Test
    public void deleteTest() {
        Work work = Work.builder().workName("작품1").workResource("qwewqe").workCategory("미술").workDescription("작품입니다.").isDelete(false).build();

        workService.save(user, work);

        workService.delete(work.getWorkId());

        WorkDto findWork = workService.findByWorkId(work.getWorkId());

        assertThat(findWork.getIsDelete()).isTrue();
    }
}