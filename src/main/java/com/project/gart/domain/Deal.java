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
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dealId;
    private String dealTitle;
    private String dealDescription;
    private int dealPrice;
    private LocalDateTime uploadDate;
    private Boolean isDelete;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_WORK_ID")
    private Work fkWorkId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_USER_ID")
    private User fkUserId;

    @Builder
    public Deal(Long dealId, String dealTitle, String dealDescription, int dealPrice, LocalDateTime uploadDate, Boolean isDelete, Work fkWorkId, User fkUserId) {
        this.dealId = dealId;
        this.dealTitle = dealTitle;
        this.dealDescription = dealDescription;
        this.dealPrice = dealPrice;
        this.uploadDate = uploadDate;
        this.isDelete = isDelete;
        this.fkWorkId = fkWorkId;
        this.fkUserId = fkUserId;
    }
}
