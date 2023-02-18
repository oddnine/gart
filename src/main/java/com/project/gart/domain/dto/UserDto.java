package com.project.gart.domain.dto;

import com.project.gart.domain.User;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    private String email;
    private String password;
    private String name;
    private Date birthday;
    private String address;
    private String photo;

    @Builder
    public UserDto(String email, String password, String name, Date birthday, String address, String photo) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.photo = photo;
    }

    public UserDto(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.name = user.getName();
        this.birthday = user.getBirthday();
        this.address = user.getAddress();
        this.photo = user.getPhoto();
    }
}
