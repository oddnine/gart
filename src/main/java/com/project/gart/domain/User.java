package com.project.gart.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(unique = true, length = 255)
    private String email;
    private String password;
    private String name;
    private Date birthday;
    private String address;
    private String photo;

    @Builder
    public User(Long userId, String email, String password, String name, Date birthday, String address, String photo) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.photo = photo;
    }

    public Boolean validatePassword(String password) {
        if (!this.password.equals(password)) {
            return false;
        }
        return true;
    }
}
