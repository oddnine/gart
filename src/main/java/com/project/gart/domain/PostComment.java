package com.project.gart.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    private String commentText;
    private Long parentCommentId;
    private LocalDateTime commentDate;
    private Boolean isDelete;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_USER_ID")
    private User fkUserId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_POST_ID")
    private Post fkPostId;

    @Builder
    public PostComment(Long commentId, String commentText, Long parentCommentId, LocalDateTime commentDate, Boolean isDelete, User fkUserId, Post fkPostId) {
        this.commentId = commentId;
        this.commentText = commentText;
        this.parentCommentId = parentCommentId;
        this.commentDate = commentDate;
        this.isDelete = isDelete;
        this.fkUserId = fkUserId;
        this.fkPostId = fkPostId;
    }
}
