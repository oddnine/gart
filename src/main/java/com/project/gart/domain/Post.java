package com.project.gart.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    private String postTitle;
    private String postDescription;
    private LocalDateTime postDate;
    private Boolean isDelete;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_WORK_ID")
    private Work fkWorkId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_USER_ID")
    private User fkUserId;

    @Builder
    public Post(Long postId, String postTitle, String postDescription, LocalDateTime postDate, Boolean isDelete, Work fkWorkId, User fkUserId) {
        this.postId = postId;
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.postDate = LocalDateTime.now();
        this.isDelete = isDelete;
        this.fkWorkId = fkWorkId;
        this.fkUserId = fkUserId;
    }
}
