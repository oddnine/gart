package com.project.gart.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PostLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postLikeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_POST_ID")
    private Post fkPostId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_USER_ID")
    private User fkUserId;

    @Builder
    public PostLike(Long postLikeId, Post fkPostId, User fkUserId) {
        this.postLikeId = postLikeId;
        this.fkPostId = fkPostId;
        this.fkUserId = fkUserId;
    }
}
