package com.project.gart.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class WorkGenre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workGenreId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_GENRE_ID")
    private Genre fkGenreId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_WORK_ID")
    private Work fkWorkId;

    @Builder
    public WorkGenre(Long workGenreId, Genre fkGenreId, Work fkWorkId) {
        this.workGenreId = workGenreId;
        this.fkGenreId = fkGenreId;
        this.fkWorkId = fkWorkId;
    }
}
