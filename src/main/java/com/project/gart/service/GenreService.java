package com.project.gart.service;

import com.project.gart.domain.Genre;
import com.project.gart.domain.UserGenre;
import com.project.gart.repository.GenreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;

    public void saveGenres(List<Genre> genres) {
        genreRepository.saveAll(genres);
    }

    public List<Genre> findByUserGenreIds(List<UserGenre> userGenres) {
        List<Genre> genres = new ArrayList<>();

        for (UserGenre userGenre : userGenres) {
            genres.add(genreRepository.findById(userGenre.getFkGenreId().getGenreId()).get());
        }

        return genres;
    }
}
