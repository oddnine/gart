package com.project.gart.service;

import com.project.gart.domain.Genre;
import com.project.gart.domain.User;
import com.project.gart.domain.UserGenre;
import com.project.gart.domain.Work;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class UserGenreServiceTest {
    @Autowired
    private UserGenreService userGenreService;

    @Autowired
    private UserService userService;

    @Autowired
    private GenreService genreService;

    @Autowired
    private WorkService workService;

    @Autowired
    private WorkGenreService workGenreService;

    private User user;
    private Work work;
    private Work work1;
    private Work work2;

    @BeforeEach
    public void beforeEach() {
        user = User.builder().email("hyuk@naver.com").address("경기도").name("혁키").password("123").birthday(Date.valueOf("1999-09-02")).photo("ewqewqe").build();

        userService.save(user);

        work = Work.builder().workName("작품1").workResource("qwewqe").workCategory("미술").workDescription("작품입니다.").isDelete(false).build();

        work1 = Work.builder().workName("작품2").workResource("qqwee").workCategory("음악").workDescription("음악입니다.").isDelete(false).build();

        work2 = Work.builder().workName("작품3").workResource("qqewqeqwwee").workCategory("미술").workDescription("작품2입니다.").isDelete(false).build();

        workService.save(user, work);

        workService.save(user, work1);

        workService.save(user, work2);
    }

    @Test
    public void saveUserGenre() {
        Genre genre = Genre.builder()
                .genreName("스릴")
                .build();

        Genre genre1 = Genre.builder()
                .genreName("공포")
                .build();

        List<Genre> genres = Arrays.asList(genre, genre1);

        genreService.saveGenres(genres);

        User user1 = User.builder()
                .userId(user.getUserId())
                .build();

        userGenreService.saveUserGenres(user1, genres);
    }

    @Test
    public void findAllByUserId() {
        Genre genre = Genre.builder()
                .genreName("스릴")
                .build();

        Genre genre1 = Genre.builder()
                .genreName("공포")
                .build();

        List<Genre> genres = Arrays.asList(genre, genre1);

        genreService.saveGenres(genres);

        User user1 = User.builder()
                .userId(user.getUserId())
                .build();

        userGenreService.saveUserGenres(user1, genres);

        List<UserGenre> byUserGenres = userGenreService.findByFkUserId(user1);

        assertThat(userGenreService.findByFkUserId(user1).size()).isEqualTo(2);
    }

    @Test
    public void deleteAllTest() {
        Genre genre = Genre.builder()
                .genreName("스릴")
                .build();

        Genre genre1 = Genre.builder()
                .genreName("공포")
                .build();

        List<Genre> genres = Arrays.asList(genre, genre1);

        genreService.saveGenres(genres);

        userGenreService.saveUserGenres(user, genres);

        assertThat(userGenreService.deleteAllByUserId(user)).isEqualTo(2);
    }

    @Test
    public void findWorkByUserGenre() {
        Genre genre = Genre.builder()
                .genreName("밝은")
                .build();

        Genre genre1 = Genre.builder()
                .genreName("어두운")
                .build();

        Genre genre2 = Genre.builder()
                .genreName("신나는")
                .build();

        genreService.saveGenres(Arrays.asList(genre, genre1, genre2));

        userGenreService.saveUserGenres(user, Arrays.asList(genre, genre1));

        workGenreService.saveWorkGenres(work, Arrays.asList(genre, genre1));

        workGenreService.saveWorkGenres(work1, Arrays.asList(genre1, genre2));

        workGenreService.saveWorkGenres(work2, Arrays.asList(genre2));

        assertThat(userGenreService.findWorkByUserGenre(user).size()).isEqualTo(2);
    }
}