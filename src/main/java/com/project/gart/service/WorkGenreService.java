package com.project.gart.service;

import com.project.gart.domain.*;
import com.project.gart.domain.dto.WorkDto;
import com.project.gart.repository.WorkGenreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WorkGenreService {
    private final WorkGenreRepository workGenreRepository;
    private final WorkService workService;

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
        deleteByWorkId(work);

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

    public List<WorkDto> findByFkGenreIds(List<Genre> genres) {
        List<WorkGenre> findWorkGenres = workGenreRepository.findByFkGenreIdIn(genres);
        return workService.findByWorkGenre(findWorkGenres);
    }

    public int deleteByWorkId(Work work) {
        return workGenreRepository.deleteByFkWorkId(work);
    }
}
