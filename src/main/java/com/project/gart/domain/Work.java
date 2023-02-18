package com.project.gart.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workId;
    private String workName;
    private String workResource;
    private Date workDate;
    private String workCategory;
    private String workDescription;
    private Boolean isDelete;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_USER_ID")
    private User fkUserId;

    @Builder
    public Work(Long workId, String workName, String workResource, Date workDate, String workCategory, String workDescription, Boolean isDelete, User fkUserId) {
        this.workId = workId;
        this.workName = workName;
        this.workResource = workResource;
        this.workDate = new Date();
        this.workCategory = workCategory;
        this.workDescription = workDescription;
        this.isDelete = isDelete;
        this.fkUserId = fkUserId;
    }

    public void setUser(User user) {
        this.fkUserId = user;
    }

    public Work updateWork(Work updateWork) {
        this.workName = updateWork.workName;
        this.workCategory = updateWork.getWorkCategory();
        this.workDescription = updateWork.getWorkDescription();

        return this;
    }

    public void deleteWork() {
        this.isDelete = true;
    }
}
