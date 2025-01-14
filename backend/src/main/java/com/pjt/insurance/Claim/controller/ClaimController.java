package com.pjt.insurance.Claim.controller;

import com.pjt.insurance.Claim.model.dto.request.ClaimRequest; // 요청 DTO 변경
import com.pjt.insurance.Claim.model.dto.response.ClaimResponse;
import com.pjt.insurance.Claim.service.ClaimService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/claims") // 경로 변경
@Tag(name = "청구 API", description = "청구와 관련된 기능 제공") // 태그 변경
public class ClaimController {

    private final ClaimService claimService; // 서비스 이름 변경

    @PostMapping
    @Operation(summary = "청구 등록", description = "새로운 청구를 등록합니다.") // 설명 변경
    public ResponseEntity<ClaimResponse> createClaim(@Valid @RequestBody ClaimRequest request) { // 요청 DTO 변경
        ClaimResponse response = claimService.createClaim(request); // 서비스 메서드 변경
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "청구 조회", description = "특정 청구 정보를 조회합니다.") // 설명 변경
    public ResponseEntity<ClaimResponse> getClaim(@PathVariable Long id) { // 응답 DTO 변경
        ClaimResponse response = claimService.getClaimById(id); // 서비스 메서드 변경
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "모든 청구 조회", description = "모든 청구 정보를 조회합니다.") // 설명 변경
    public ResponseEntity<List<ClaimResponse>> getAllClaims() { // 응답 DTO 변경
        List<ClaimResponse> responses = claimService.getAllClaims(); // 서비스 메서드 변경
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    @Operation(summary = "청구 업데이트", description = "특정 청구 정보를 업데이트합니다.") // 설명 변경
    public ResponseEntity<ClaimResponse> updateClaim(@PathVariable Long id, @Valid @RequestBody ClaimRequest request) { // 요청 DTO 변경
        ClaimResponse response = claimService.updateClaim(id, request); // 서비스 메서드 변경
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "청구 삭제", description = "특정 청구를 삭제합니다.") // 설명 변경
    public ResponseEntity<Void> deleteClaim(@PathVariable Long id) { // 메서드 이름 변경
        claimService.deleteClaim(id); // 서비스 메서드 변경
        return ResponseEntity.noContent().build();
    }
}
