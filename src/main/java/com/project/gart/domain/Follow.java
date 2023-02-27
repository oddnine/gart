package com.project.gart.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_FOLLOWING_ID")
    private User fkFollowingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_FOLLOWER_ID")
    private User fkFollowerId;

    @Builder
    public Follow(Long followId, User fkFollowingId, User fkFollowerId) {
        this.followId = followId;
        this.fkFollowingId = fkFollowingId;
        this.fkFollowerId = fkFollowerId;
    }
}
