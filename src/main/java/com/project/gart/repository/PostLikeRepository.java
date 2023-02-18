package com.project.gart.repository;

import com.project.gart.domain.Post;
import com.project.gart.domain.PostLike;
import com.project.gart.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    List<PostLike> findByFkPostId(Post fkPostId);

    PostLike findByFkPostIdAndFkUserId(Post fkPostId, User fkUserId);

    Long countByFkPostId(Post fkPostId);
}
