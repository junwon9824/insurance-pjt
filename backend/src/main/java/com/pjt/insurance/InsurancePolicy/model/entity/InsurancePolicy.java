package com.pjt.insurance.InsurancePolicy.model.entity;

import com.pjt.insurance.insuranceproduct.model.entity.InsuranceProduct;
import com.pjt.insurance.user.model.entity.MemberProfile;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "insurance_policy")
public class InsurancePolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "policy_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private InsuranceProduct insuranceProduct; // 보험 상품과의 관계

    @ManyToOne
    @JoinColumn(name = "member_profile_id") // 외래 키 설정
    private MemberProfile memberProfile; // 관련 회원 프로필

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate; // 계약 시작일

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate; // 계약 만료일

    @Column(name = "status", nullable = false)
    private String status; // 계약 상태 (예: "ACTIVE", "EXPIRED", "CANCELLED")

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt;

    // 추가적인 메서드 및 비즈니스 로직
}
