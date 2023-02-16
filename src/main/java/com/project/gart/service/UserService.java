package com.project.gart.service;

import com.project.gart.domain.Genre;
import com.project.gart.domain.User;
import com.project.gart.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Boolean join(User user) {
        validateDuplicateUser(user);
        userRepository.save(user);
        return true;
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
