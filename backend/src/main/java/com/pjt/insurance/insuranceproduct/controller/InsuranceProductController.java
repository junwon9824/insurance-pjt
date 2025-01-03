package com.pjt.insurance.insuranceproduct.controller;

import com.pjt.insurance.insuranceproduct.model.dto.request.InsuranceProductRequest;
import com.pjt.insurance.insuranceproduct.model.dto.response.InsuranceProductResponse;
import com.pjt.insurance.insuranceproduct.service.InsuranceProductService;
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
@RequestMapping("/v1/products")
@Tag(name = "보험 상품 API", description = "보험 상품과 관련된 기능 제공")
public class InsuranceProductController {

    private final InsuranceProductService insuranceProductService;

    @PostMapping
    @Operation(summary = "보험 상품 등록", description = "새로운 보험 상품을 등록합니다.")
    public ResponseEntity<InsuranceProductResponse> createInsuranceProduct(@Valid @RequestBody InsuranceProductRequest request) {
        InsuranceProductResponse response = insuranceProductService.createInsuranceProduct(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "보험 상품 조회", description = "특정 보험 상품 정보를 조회합니다.")
    public ResponseEntity<InsuranceProductResponse> getInsuranceProduct(@PathVariable Long id) {
        InsuranceProductResponse response = insuranceProductService.getInsuranceProductById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "모든 보험 상품 조회", description = "모든 보험 상품 정보를 조회합니다.")
    public ResponseEntity<List<InsuranceProductResponse>> getAllInsuranceProducts() {
        List<InsuranceProductResponse> responses = insuranceProductService.getAllInsuranceProducts();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    @Operation(summary = "보험 상품 업데이트", description = "특정 보험 상품 정보를 업데이트합니다.")
    public ResponseEntity<InsuranceProductResponse> updateInsuranceProduct(@PathVariable Long id, @Valid @RequestBody InsuranceProductRequest request) {
        InsuranceProductResponse response = insuranceProductService.updateInsuranceProduct(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "보험 상품 삭제", description = "특정 보험 상품을 삭제합니다.")
    public ResponseEntity<Void> deleteInsuranceProduct(@PathVariable Long id) {
        insuranceProductService.deleteInsuranceProduct(id);
        return ResponseEntity.noContent().build();
    }
}
