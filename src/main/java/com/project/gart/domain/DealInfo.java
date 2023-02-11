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
public class DealInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dealInfoId;
    private LocalDateTime dealDate;
    private String shipName;
    private String shipPhone;
    private String shipAddr;
    private String dealPayment;
    private String dealState;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_DEAL_ID")
    private Deal fkDealId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_USER_ID")
    private User fkBuyerId;

    @Builder
    public DealInfo(Long dealInfoId, LocalDateTime dealDate, String shipName, String shipPhone, String shipAddr, String dealPayment, String dealState, Deal fkDealId, User fkBuyerId) {
        this.dealInfoId = dealInfoId;
        this.dealDate = dealDate;
        this.shipName = shipName;
        this.shipPhone = shipPhone;
        this.shipAddr = shipAddr;
        this.dealPayment = dealPayment;
        this.dealState = dealState;
        this.fkDealId = fkDealId;
        this.fkBuyerId = fkBuyerId;
    }
}
