package com.pjt.insurance.Claim.model.entity;

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
@Table(name = "claims") // 테이블 이름 변경
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 청구 ID

    @ManyToOne(fetch = FetchType.LAZY) // 청구가 특정 정책에 속함
    @JoinColumn(name = "policy_id", nullable = false) // 외래 키 설정
    private InsurancePolicy policy; // 청구와 관련된 보험 정책

    @Column(nullable = false)
    private Double amount; // 청구 금액

    @Column(nullable = false)
    private String status; // 청구 상태 (예: "PENDING", "APPROVED", "DENIED")

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // 생성일

    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // 수정일
}
