package com.project.gart.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class WorkQna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qnaId;
    private LocalDateTime qnaDate;
    private String qnaText;
    private Long qnaParentId;
    private Boolean isDelete;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_WORK_ID")
    private Work fkWorkId;

    @Builder
    public WorkQna(Long qnaId, LocalDateTime qnaDate, String qnaText, Long qnaParentId, Boolean isDelete, Work fkWorkId) {
        this.qnaId = qnaId;
        this.qnaDate = qnaDate;
        this.qnaText = qnaText;
        this.qnaParentId = qnaParentId;
        this.isDelete = isDelete;
        this.fkWorkId = fkWorkId;
    }
}
