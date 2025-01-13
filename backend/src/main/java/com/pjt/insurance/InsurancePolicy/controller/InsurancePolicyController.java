package com.pjt.insurance.InsurancePolicy.controller;

import com.pjt.insurance.InsurancePolicy.model.dto.request.InsurancePolicyRequest; // 요청 DTO 변경
import com.pjt.insurance.InsurancePolicy.model.dto.response.InsurancePolicyResponse; // 응답 DTO 변경
import com.pjt.insurance.InsurancePolicy.service.InsurancePolicyService; // 서비스 이름 변경
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
@RequestMapping("/v1/policies") // 경로 변경
@Tag(name = "보험 계약 API", description = "보험 계약과 관련된 기능 제공") // 태그 변경
public class InsurancePolicyController {

    private final InsurancePolicyService insurancePolicyService; // 서비스 이름 변경

    @PostMapping
    @Operation(summary = "보험 계약 등록", description = "새로운 보험 계약을 등록합니다.") // 설명 변경
    public ResponseEntity<InsurancePolicyResponse> createInsurancePolicy(@Valid @RequestBody InsurancePolicyRequest request) { // 요청 DTO 변경
        InsurancePolicyResponse response = insurancePolicyService.createInsurancePolicy(request); // 서비스 메서드 변경
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "보험 계약 조회", description = "특정 보험 계약 정보를 조회합니다.") // 설명 변경
    public ResponseEntity<InsurancePolicyResponse> getInsurancePolicy(@PathVariable Long id) { // 응답 DTO 변경
        InsurancePolicyResponse response = insurancePolicyService.getInsurancePolicyById(id); // 서비스 메서드 변경
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "모든 보험 계약 조회", description = "모든 보험 계약 정보를 조회합니다.") // 설명 변경
    public ResponseEntity<List<InsurancePolicyResponse>> getAllInsurancePolicies() { // 응답 DTO 변경
        List<InsurancePolicyResponse> responses = insurancePolicyService.getAllInsurancePolicies(); // 서비스 메서드 변경
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    @Operation(summary = "보험 계약 업데이트", description = "특정 보험 계약 정보를 업데이트합니다.") // 설명 변경
    public ResponseEntity<InsurancePolicyResponse> updateInsurancePolicy(@PathVariable Long id, @Valid @RequestBody InsurancePolicyRequest request) { // 요청 DTO 변경
        InsurancePolicyResponse response = insurancePolicyService.updateInsurancePolicy(id, request); // 서비스 메서드 변경
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "보험 계약 삭제", description = "특정 보험 계약을 삭제합니다.") // 설명 변경
    public ResponseEntity<Void> deleteInsurancePolicy(@PathVariable Long id) { // 메서드 이름 변경
        insurancePolicyService.deleteInsurancePolicy(id); // 서비스 메서드 변경
        return ResponseEntity.noContent().build();
    }
}
