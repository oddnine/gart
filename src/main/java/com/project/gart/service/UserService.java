package com.project.gart.service;

import com.project.gart.domain.User;
import com.project.gart.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Transactional
@Service
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
        return validateLogin(email, password);
    }

    private Boolean validateLogin(String email, String password) {
        Optional<User> findUser = userRepository.findByEmailAndPassword(email, password);
        if (!findUser.isEmpty()) {
            return true;
        }
        return false;
    }
}
