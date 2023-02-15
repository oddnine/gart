package com.project.gart.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserGenre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userGenreId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_GENRE_ID")
    private Genre fkGenreId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_USER_ID")
    private User fkUserId;

    @Builder
    public UserGenre(Long userGenreId, Genre fkGenreId, User fkUserId) {
        this.userGenreId = userGenreId;
        this.fkGenreId = fkGenreId;
        this.fkUserId = fkUserId;
    }
}
