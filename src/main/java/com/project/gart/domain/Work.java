package com.project.gart.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wordId;
    private String wordResource;
    private Date workDate;
    private String wordCategory;
    private String wordDescription;
    private Boolean isDelete;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_USER_ID")
    private User fkUserId;

    @Builder
    public Work(Long wordId, String wordResource, Date workDate, String wordCategory, String wordDescription, Boolean isDelete, User fkUserId) {
        this.wordId = wordId;
        this.wordResource = wordResource;
        this.workDate = workDate;
        this.wordCategory = wordCategory;
        this.wordDescription = wordDescription;
        this.isDelete = isDelete;
        this.fkUserId = fkUserId;
    }
}
