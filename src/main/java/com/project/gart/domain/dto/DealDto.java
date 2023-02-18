package com.project.gart.domain.dto;

import com.project.gart.domain.Deal;
import com.project.gart.domain.User;
import com.project.gart.domain.Work;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DealDto {
    private String dealTitle;
    private String dealDescription;
    private int dealPrice;
    private LocalDateTime uploadDate;
    private Boolean isDelete;
    private Long fkWorkId;
    private Long fkUserId;

    @Builder
    public DealDto(String dealTitle, String dealDescription, int dealPrice, LocalDateTime uploadDate, Boolean isDelete, Work fkWorkId, User fkUserId) {
        this.dealTitle = dealTitle;
        this.dealDescription = dealDescription;
        this.dealPrice = dealPrice;
        this.uploadDate = uploadDate;
        this.isDelete = isDelete;
        this.fkWorkId = fkWorkId.getWorkId();
        this.fkUserId = fkUserId.getUserId();
    }

    public DealDto(Deal deal) {
        this.dealTitle = deal.getDealTitle();
        this.dealDescription = deal.getDealDescription();
        this.dealPrice = deal.getDealPrice();
        this.uploadDate = deal.getUploadDate();
        this.isDelete = deal.getIsDelete();
        this.fkWorkId = deal.getFkWorkId().getWorkId();
        this.fkUserId = deal.getFkUserId().getUserId();
    }
}
