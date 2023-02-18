package com.project.gart.repository;

import com.project.gart.domain.Post;
import com.project.gart.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByPostTitle(String postTitle);

    List<Post> findByFkUserId(User fkUserId);

    List<Post> findAllByFkUserIdIn(List<User> fkUserId);
}
