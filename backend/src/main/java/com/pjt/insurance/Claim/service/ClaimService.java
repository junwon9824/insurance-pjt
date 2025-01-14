package com.pjt.insurance.Claim.service;

import com.pjt.insurance.Claim.exception.ClaimErrorCode;
import com.pjt.insurance.Claim.exception.ClaimException;
import com.pjt.insurance.Claim.model.dto.request.ClaimRequest;
import com.pjt.insurance.Claim.model.dto.response.ClaimResponse;
import com.pjt.insurance.Claim.model.entity.Claim;
import com.pjt.insurance.Claim.repository.ClaimRepository;
import com.pjt.insurance.InsurancePolicy.repository.InsurancePolicyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClaimService {

    private final ClaimRepository claimRepository;
    private final InsurancePolicyRepository policyRepository; // 정책 리포지토리 추가

    @Transactional
    public ClaimResponse createClaim(ClaimRequest request) {
        // 정책이 존재하는지 확인
        var policy = policyRepository.findById(request.getPolicyId())
                .orElseThrow(() -> new ClaimException(ClaimErrorCode.NOT_EXISTS_CLAIM));

        // Claim 엔티티 생성
        Claim claim = Claim.builder()
                .policy(policy)
                .amount(request.getAmount())
                .status(request.getStatus())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Claim savedClaim = claimRepository.save(claim);
        return mapToResponse(savedClaim);
    }

    public ClaimResponse getClaimById(Long id) {
        Claim claim = claimRepository.findById(id)
                .orElseThrow(() -> new ClaimException(ClaimErrorCode.NOT_EXISTS_CLAIM));
        return mapToResponse(claim);
    }

    public List<ClaimResponse> getAllClaims() {
        List<Claim> claims = claimRepository.findAll();
        return claims.stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional
    public ClaimResponse updateClaim(Long id, ClaimRequest request) {
        Claim claim = claimRepository.findById(id)
                .orElseThrow(() -> new ClaimException(ClaimErrorCode.NOT_EXISTS_CLAIM));

        claim.setAmount(request.getAmount());
        claim.setStatus(request.getStatus());
        claim.setUpdatedAt(LocalDateTime.now());

        Claim updatedClaim = claimRepository.save(claim);
        return mapToResponse(updatedClaim);
    }

    @Transactional
    public void deleteClaim(Long id) {
        if (!claimRepository.existsById(id)) {
            throw new ClaimException(ClaimErrorCode.NOT_EXISTS_CLAIM);
        }
        claimRepository.deleteById(id);
    }

    // 응답 DTO로 매핑하는 메서드
    private ClaimResponse mapToResponse(Claim claim) {
        return ClaimResponse.builder()
                .id(claim.getId())
                .policyId(claim.getPolicy().getId()) // 정책 ID 포함
                .amount(claim.getAmount())
                .status(claim.getStatus())
                .createdAt(claim.getCreatedAt().toString()) // 필요에 따라 LocalDateTime 포맷 조정
                .updatedAt(claim.getUpdatedAt().toString())
                .build();
    }
}
