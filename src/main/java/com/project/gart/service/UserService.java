package com.project.gart.service;

import com.project.gart.domain.User;
import com.project.gart.domain.dto.UserDto;
import com.project.gart.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Long save(User user) {
        validateDuplicateUser(user);
        return userRepository.save(user).getUserId();
    }

    private void validateDuplicateUser(User user) {
        userRepository.findByEmail(user.getEmail())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new NullPointerException("유저 조회 결과가 없습니다."));
    }

    public Boolean login(String email, String password) {
        User findUser = userRepository.findByEmail(email).orElseThrow(() -> new NullPointerException("유저 조회 결과가 없습니다."));
        return findUser.validatePassword(password);
    }
}
