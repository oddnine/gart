package com.project.gart.service;

import com.project.gart.domain.Follow;
import com.project.gart.domain.User;
import com.project.gart.domain.dto.UserDto;
import com.project.gart.repository.FollowRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;
    private final UserService userService;

    public Long follow(User follower, User following) {
        try {
            findByFollowerAndFollowing(follower, following);

            throw new IllegalStateException("이미 팔로잉 중입니다.");
        } catch (NullPointerException e) {
            return followRepository.save(Follow.builder()
                    .fkFollowerId(follower)
                    .fkFollowingId(following).build()).getFollowId();
        }
    }

    public void unFollow(User follower, User following) {
        Follow findFollow = findByFollowerAndFollowing(follower, following);
        followRepository.delete(findFollow);
    }

    public Follow findByFollowerAndFollowing(User follower, User following) {
        return followRepository.findByFkFollowerIdAndFkFollowingId(follower, following).orElseThrow(() -> new NullPointerException("팔로잉 조회 결과가 없습니다."));
    }

    public List<UserDto> findFollowerList(User following) {
        List<User> followerList = followRepository.findFollowerList(following);

        List<UserDto> userDtoList = new ArrayList<>();

        //n + 1
        for (User user : followerList) {
            System.out.println("fsdfsd" + user.getName());
            userDtoList.add(new UserDto(user));
        }

        return userDtoList;
    }

    public Long countByFollower(User following) {
        return followRepository.countByFkFollowingId(following);
    }

    public Long countByFollowing(User follower) {
        return followRepository.countByFkFollowerId(follower);
    }
}
