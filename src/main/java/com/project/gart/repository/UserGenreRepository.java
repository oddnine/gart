package com.project.gart.repository;

import com.project.gart.domain.User;
import com.project.gart.domain.UserGenre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserGenreRepository extends JpaRepository<UserGenre, Long> {
    List<UserGenre> findByFkUserId(User fkUserId);

    int deleteByFkUserId(User fkUserId);
}
