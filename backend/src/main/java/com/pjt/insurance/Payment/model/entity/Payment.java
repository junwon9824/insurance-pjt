package com.pjt.insurance.Payment.model.entity;

import com.pjt.insurance.InsurancePolicy.model.entity.InsurancePolicy;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 결제 ID

    @ManyToOne(fetch = FetchType.LAZY) // 결제가 특정 정책에 속함
    @JoinColumn(name = "policy_id", nullable = false) // 외래 키 설정
    private InsurancePolicy policy; // 결제와 관련된 보험 정책

    @Column(nullable = false)
    private Double amount; // 결제 금액

    @Column(nullable = false)
    private String status; // 결제 상태 (예: "PENDING", "COMPLETED", "FAILED")

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // 생성일

    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // 수정일
}
