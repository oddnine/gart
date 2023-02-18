package com.project.gart.service;

import com.project.gart.domain.Post;
import com.project.gart.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private PostRepository postRepository;

    private Long save(Post post) {
        return postRepository.save(post).getPostId();
    }
}
