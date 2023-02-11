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

    @Builder
    public Post(Long postId, String postTitle, String postDescription, LocalDateTime postDate, Boolean isDelete, Work fkWorkId) {
        this.postId = postId;
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.postDate = postDate;
        this.isDelete = isDelete;
        this.fkWorkId = fkWorkId;
    }
}
