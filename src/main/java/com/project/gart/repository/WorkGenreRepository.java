package com.project.gart.repository;

import com.project.gart.domain.User;
import com.project.gart.domain.UserGenre;
import com.project.gart.domain.Work;
import com.project.gart.domain.WorkGenre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkGenreRepository extends JpaRepository<WorkGenre, Long> {
    List<WorkGenre> findByFkWorkId(Work fkWorkId);

    int deleteAllByFkWorkId(Work fkWorkId);
}
