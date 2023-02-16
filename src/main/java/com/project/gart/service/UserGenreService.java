package com.project.gart.service;

import com.project.gart.domain.Genre;
import com.project.gart.domain.User;
import com.project.gart.domain.UserGenre;
import com.project.gart.repository.UserGenreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserGenreService {
    private final UserGenreRepository userGenreRepository;

    public void saveUserGenres(User user, List<Genre> genres) {
        List<UserGenre> userGenres = new ArrayList<>();
        for (Genre genre : genres) {
            UserGenre userGenre = UserGenre.builder()
                    .fkGenreId(genre)
                    .fkUserId(user)
                    .build();

            userGenres.add(userGenre);
        }

        userGenreRepository.saveAll(userGenres);
    }

    public void updateUserGenres(User user, List<Genre> genres) {
        deleteAllByUserId(user);

        List<UserGenre> userGenres = new ArrayList<>();
        for (Genre genre : genres) {
            UserGenre userGenre = UserGenre.builder()
                    .fkGenreId(genre)
                    .fkUserId(user)
                    .build();

            userGenres.add(userGenre);
        }

        userGenreRepository.saveAll(userGenres);
    }

    public List<UserGenre> findByFkUserId(User user) {
        return userGenreRepository.findByFkUserId(user);
    }

    public int deleteAllByUserId(User user){
        return userGenreRepository.deleteAllByFkUserId(user);
    }
}
