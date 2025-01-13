package com.pjt.insurance.insuranceproduct.model.entity;

import com.pjt.insurance.InsurancePolicy.model.entity.InsurancePolicy;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "insurance_product")
public class InsuranceProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long id;

    @Column(name = "product_name", nullable = false, length = 100)
    private String name; // 보험 상품 이름

    @Column(name = "product_description", length = 255)
    private String description; // 보험 상품 설명

    @Column(name = "start_date")
    private LocalDate startDate; // 보험 시작 날짜

    @Column(name = "status", length = 20)
    private String status; // 보험 상태 (예: 활성화, 만료 등)

    @OneToMany(mappedBy = "insuranceProduct", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<InsurancePolicy> policies; // 이 보험 상품에 속한 보험 계약 목록

    // 추가 필드 및 메서드를 여기에 추가할 수 있습니다.
}
