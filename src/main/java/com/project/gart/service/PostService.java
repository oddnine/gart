package com.project.gart.service;

import com.project.gart.domain.Post;
import com.project.gart.domain.User;
import com.project.gart.domain.dto.PostDto;
import com.project.gart.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;

    public Long save(Post post) {
        return postRepository.save(post).getPostId();
    }

    public PostDto findById(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        if (post == null)
            return null;
        return new PostDto(post);
    }

    public List<PostDto> findByPostTitle(String postTitle) {
        List<Post> findPosts = postRepository.findByPostTitle(postTitle);
        List<PostDto> postDtoList = new ArrayList<>();
        for (Post findPost : findPosts) {
            postDtoList.add(new PostDto(findPost));
        }

        return postDtoList;
    }

    public List<PostDto> findByUserName(String name) {
        List<User> findUser = userService.findByName(name);

        List<Post> findPosts = postRepository.findByFkUserIdIn(findUser);

        List<PostDto> postDtoList = new ArrayList<>();

        for (Post findPost : findPosts) {
            postDtoList.add(new PostDto(findPost));
        }

        return postDtoList;
    }

    public List<PostDto> findByUserEmail(String email) {
        User findUser = userService.findByEmail(email);

        List<Post> findPost = postRepository.findByFkUserId(findUser);
        List<PostDto> postDtoList = new ArrayList<>();

        for (Post post : findPost) {
            postDtoList.add(new PostDto(post));
        }

        return postDtoList;
    }

    public List<PostDto> findByPostTitleContaining(String postTitle) {
        List<Post> findPosts = postRepository.findByPostTitleContaining(postTitle);
        List<PostDto> postDtoList = new ArrayList<>();
        for (Post findPost : findPosts) {
            postDtoList.add(new PostDto(findPost));
        }

        return postDtoList;
    }
}
