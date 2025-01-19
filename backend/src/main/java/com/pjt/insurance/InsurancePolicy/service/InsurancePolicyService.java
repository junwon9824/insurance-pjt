package com.pjt.insurance.InsurancePolicy.service;

import com.pjt.insurance.InsurancePolicy.model.dto.request.InsurancePolicyRequest;
import com.pjt.insurance.InsurancePolicy.model.dto.response.InsurancePolicyResponse;
import com.pjt.insurance.InsurancePolicy.model.entity.InsurancePolicy;
import com.pjt.insurance.InsurancePolicy.repository.InsurancePolicyRepository;
import com.pjt.insurance.insuranceproduct.exception.InsuranceProductErrorCode;
import com.pjt.insurance.insuranceproduct.exception.InsuranceProductException;
import com.pjt.insurance.insuranceproduct.repository.InsuranceProductRepository;
import com.pjt.insurance.user.exception.UserErrorCode;
import com.pjt.insurance.user.exception.UserException;
import com.pjt.insurance.user.model.entity.MemberProfile; // 필요 시 추가
import com.pjt.insurance.insuranceproduct.model.entity.InsuranceProduct; // 필요 시 추가
import com.pjt.insurance.user.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class InsurancePolicyService {

    private final InsurancePolicyRepository insurancePolicyRepository;
    private final MemberRepository memberRepository;
    private final InsuranceProductRepository insuranceProductRepository;
    // 필요에 따라 MemberProfileRepository, InsuranceProductRepository 추가

    // 보험 계약 생성
    @Transactional
    public InsurancePolicyResponse createInsurancePolicy(InsurancePolicyRequest request) {

        long memberid = request.getMemberId();
        MemberProfile memberProfile = memberRepository.findById(memberid).orElseThrow(() -> new UserException(UserErrorCode.NOT_EXISTS_USER));

        InsuranceProduct insuranceProduct = insuranceProductRepository.findById(request.getProductId()).orElseThrow(()
                -> new InsuranceProductException(InsuranceProductErrorCode.NOT_EXISTS_PRODUCT));

        // 동일한 상품에 대한 기존 정책 조회
        InsurancePolicy existingPolicy = insurancePolicyRepository.findByMemberProfileAndInsuranceProduct(memberProfile, insuranceProduct);

        if (existingPolicy != null) {
            // 기존 정책이 존재하면 에러 출력
            throw new InsuranceProductException(InsuranceProductErrorCode.ALREADY_EXISTS_PRODUCT);
        }

        InsurancePolicy policy = InsurancePolicy.builder()
                .memberProfile(memberProfile) // 실제 MemberProfile 객체로 변경
                .insuranceProduct(insuranceProduct) // 실제 InsuranceProduct 객체로 변경
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .status("ACTIVE") // 기본 상태 설정
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .build();

        InsurancePolicy savedPolicy = insurancePolicyRepository.save(policy);
        return convertToResponse(savedPolicy);
    }

    // 특정 보험 계약 조회
    public InsurancePolicyResponse getInsurancePolicyById(Long id) {
        InsurancePolicy policy = insurancePolicyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("보험 계약을 찾을 수 없습니다.")); // 예외 처리
        return convertToResponse(policy);
    }

    // 모든 보험 계약 조회
    public List<InsurancePolicyResponse> getAllInsurancePolicies() {
        List<InsurancePolicy> policies = insurancePolicyRepository.findAll();
        return policies.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // 보험 계약 업데이트
    @Transactional
    public InsurancePolicyResponse updateInsurancePolicy(Long id, InsurancePolicyRequest request) {
        InsurancePolicy policy = insurancePolicyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("보험 계약을 찾을 수 없습니다.")); // 예외 처리

        policy.setStartDate(request.getStartDate());
        policy.setEndDate(request.getEndDate());
        policy.setUpdatedAt(LocalDate.now());

        InsurancePolicy updatedPolicy = insurancePolicyRepository.save(policy);
        return convertToResponse(updatedPolicy);
    }

    // 보험 계약 삭제
    @Transactional
    public void deleteInsurancePolicy(Long id) {
        if (!insurancePolicyRepository.existsById(id)) {
            throw new RuntimeException("보험 계약을 찾을 수 없습니다."); // 예외 처리
        }
        insurancePolicyRepository.deleteById(id);
    }

    // 엔티티를 DTO로 변환하는 메서드
    private InsurancePolicyResponse convertToResponse(InsurancePolicy policy) {
        return InsurancePolicyResponse.builder()
                .id(policy.getId())
                .memberId(policy.getMemberProfile().getId()) // 실제 MemberProfile에서 ID 가져오기
                .productId(policy.getInsuranceProduct().getId()) // 실제 InsuranceProduct에서 ID 가져오기
                .startDate(policy.getStartDate())
                .endDate(policy.getEndDate())
                .status(policy.getStatus())
                .createdAt(policy.getCreatedAt())
                .updatedAt(policy.getUpdatedAt())
                .build();
    }

}
