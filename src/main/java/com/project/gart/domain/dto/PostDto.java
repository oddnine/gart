package com.project.gart.domain.dto;

import com.project.gart.domain.Post;
import com.project.gart.domain.User;
import com.project.gart.domain.Work;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDto {
    private String postTitle;
    private String postDescription;
    private LocalDateTime postDate;
    private Boolean isDelete;
    private Long fkWorkId;
    private Long fkUserId;

    @Builder
    public PostDto(String postTitle, String postDescription, LocalDateTime postDate, Boolean isDelete, Work fkWorkId, User fkUserId) {
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.postDate = postDate;
        this.isDelete = isDelete;
        this.fkWorkId = fkWorkId.getWorkId();
        this.fkUserId = fkUserId.getUserId();
    }

    public PostDto(Post post) {
        this.postTitle = post.getPostTitle();
        this.postDescription = post.getPostDescription();
        this.postDate = post.getPostDate();
        this.isDelete = post.getIsDelete();
        this.fkWorkId = post.getFkWorkId().getWorkId();
        this.fkUserId = post.getFkUserId().getUserId();
    }
}
