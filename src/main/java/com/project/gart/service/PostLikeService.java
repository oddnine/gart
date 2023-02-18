package com.project.gart.service;

import com.project.gart.domain.Post;
import com.project.gart.domain.User;
import com.project.gart.domain.PostLike;
import com.project.gart.repository.PostLikeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostLikeService {
    private final PostLikeRepository postLikeRepository;
    private final PostService postService;
    private final UserService userService;

    public Long save(Post post, User user) throws Exception {
        PostLike findPostLike = findByPostAndUser(post, user);

        if (findPostLike != null) throw new IllegalStateException("좋아요를 이미 누르셨습니다.");
        else {
            PostLike postLike = PostLike.builder().fkPostId(post).fkUserId(user).build();

            return postLikeRepository.save(postLike).getPostLikeId();
        }
    }

    public void delete(Post post, User user) throws Exception {
        PostLike findPostLike = findByPostAndUser(post, user);

        if (findPostLike == null) throw new IllegalStateException("좋아요를 아직 안 누르셨습니다.");
        else {
            postLikeRepository.delete(findPostLike);
        }
    }

    public PostLike findByPostAndUser(Post post, User user) {
        return postLikeRepository.findByFkPostIdAndFkUserId(post, user);
    }

    public List<User> likedUserList(Post post) {
        List<PostLike> findPostLike = postLikeRepository.findByFkPostId(post);
        List<User> userList = new ArrayList<>();
        for (PostLike postLike : findPostLike) {
            userList.add(postLike.getFkUserId());
        }

        return userList;
    }

    public Long countByPost(Post post) {
        return postLikeRepository.countByFkPostId(post);
    }
}
