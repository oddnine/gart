package com.project.gart.repository;

import com.project.gart.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkGenreRepository extends JpaRepository<WorkGenre, Long> {
    List<WorkGenre> findByFkWorkId(Work fkWorkId);

    int deleteAllByFkWorkId(Work fkWorkId);

    List<WorkGenre> findAllByFkGenreIdIn(List<Genre> FkGenreId);
}
