package com.project.gart.service;

import com.project.gart.domain.Genre;
import com.project.gart.domain.User;
import com.project.gart.domain.UserGenre;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

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

    @Test
    public void saveUserGenre() {
        User user = User.builder()
                .email("hyuk@naver.com")
                .address("경기도")
                .name("혁키")
                .password("123")
                .birthday(Date.valueOf("1999-09-02"))
                .photo("ewqewqe")
                .build();

        userService.join(user);

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

        userGenreService.saveUserGenre(user1, genres);
    }

    @Test
    public void findAllByUserId() {
        User user = User.builder()
                .email("hyuk@naver.com")
                .address("경기도")
                .name("혁키")
                .password("123")
                .birthday(Date.valueOf("1999-09-02"))
                .photo("ewqewqe")
                .build();

        userService.join(user);

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

        userGenreService.saveUserGenre(user1, genres);

        List<UserGenre> byUserGenres = userGenreService.findByFkUserId(user1);

        assertThat(userGenreService.findByFkUserId(user1).size()).isEqualTo(2);
    }

    @Test
    public void deleteAllTest() {
        User user = User.builder()
                .email("hyuk@naver.com")
                .address("경기도")
                .name("혁키")
                .password("123")
                .birthday(Date.valueOf("1999-09-02"))
                .photo("ewqewqe")
                .build();

        userService.join(user);

        Genre genre = Genre.builder()
                .genreName("스릴")
                .build();

        Genre genre1 = Genre.builder()
                .genreName("공포")
                .build();

        List<Genre> genres = Arrays.asList(genre, genre1);

        genreService.saveGenres(genres);

        userGenreService.saveUserGenre(user, genres);

        assertThat(userGenreService.deleteAllByUserId(user)).isEqualTo(2);
    }
}