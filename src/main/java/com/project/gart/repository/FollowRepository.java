package com.project.gart.repository;

import com.project.gart.domain.Follow;
import com.project.gart.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    Optional<Follow> findByFkFollowerIdAndFkFollowingId(User fkFollowerId, User fkFollowingId);

    @Query(value = "SELECT u FROM User u JOIN FETCH Follow f ON f.fkFollowerId = u WHERE f.fkFollowingId = :fkFollowingId")
    List<User> findFollowerList(@Param("fkFollowingId") User fkFollowingId);

    Long countByFkFollowingId(User fkFollowingId);

    Long countByFkFollowerId(User fkFollowerId);
}
