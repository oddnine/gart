package com.project.gart.domain.dto;

import com.project.gart.domain.User;
import com.project.gart.domain.Work;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
public class WorkDto {
    private Long workId;
    private String workName;
    private String workResource;
    private Date workDate;
    private String workCategory;
    private String workDescription;
    private Boolean isDelete;
    private Long fkUserId;

    @Builder
    public WorkDto(Long workId, String workName, String workResource, Date workDate, String workCategory, String workDescription, Boolean isDelete, User fkUserId) {
        this.workId = workId;
        this.workName = workName;
        this.workResource = workResource;
        this.workDate = workDate;
        this.workCategory = workCategory;
        this.workDescription = workDescription;
        this.isDelete = isDelete;
        this.fkUserId = fkUserId.getUserId();
    }

    public WorkDto(Work work) {
        this.workId = work.getWorkId();
        this.workName = work.getWorkName();
        this.workResource = work.getWorkResource();
        this.workDate = work.getWorkDate();
        this.workCategory = work.getWorkCategory();
        this.workDescription = work.getWorkDescription();
        this.isDelete = work.getIsDelete();
        this.fkUserId = work.getFkUserId().getUserId();
    }
}
