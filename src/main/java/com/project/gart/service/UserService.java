package com.project.gart.service;

import com.project.gart.domain.User;
import com.project.gart.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public Boolean login(String email, String password) {
        User findUser = userRepository.findByEmail(email).orElse(null);
        if (findUser == null) {
            return false;
        }
        return findUser.validatePassword(password);
    }
}
