package com.project.gart.service;

import com.project.gart.domain.*;
import com.project.gart.repository.WorkGenreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class WorkGenreService {
    private final WorkGenreRepository workGenreRepository;

    public void saveWorkGenres(Work work, List<Genre> genres) {
        List<WorkGenre> workGenres = new ArrayList<>();
        for (Genre genre : genres) {
            WorkGenre workGenre = WorkGenre.builder()
                    .fkGenreId(genre)
                    .fkWorkId(work)
                    .build();

            workGenres.add(workGenre);
        }

        workGenreRepository.saveAll(workGenres);
    }

    public void updateWorkGenres(Work work, List<Genre> genres) {
        deleteAllByWorkId(work);

        List<WorkGenre> workGenres = new ArrayList<>();
        for (Genre genre : genres) {
            WorkGenre workGenre = WorkGenre.builder()
                    .fkGenreId(genre)
                    .fkWorkId(work)
                    .build();

            workGenres.add(workGenre);
        }

        workGenreRepository.saveAll(workGenres);
    }

    public List<WorkGenre> findByFkWorkId(Work work) {
        return workGenreRepository.findByFkWorkId(work);
    }

    public int deleteAllByWorkId(Work work) {
        return workGenreRepository.deleteAllByFkWorkId(work);
    }
}
