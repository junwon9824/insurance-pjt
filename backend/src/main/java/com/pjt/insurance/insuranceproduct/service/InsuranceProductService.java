package com.pjt.insurance.insuranceproduct.service;

import com.pjt.insurance.insuranceproduct.model.dto.request.InsuranceProductRequest; // 보험 상품 요청 DTO
import com.pjt.insurance.insuranceproduct.model.dto.response.InsuranceProductResponse; // 보험 상품 응답 DTO
import com.pjt.insurance.insuranceproduct.model.entity.InsuranceProduct;
import com.pjt.insurance.insuranceproduct.repository.InsuranceProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class InsuranceProductService {

    private final InsuranceProductRepository insuranceProductRepository;

    @Transactional
    public InsuranceProductResponse createInsuranceProduct(InsuranceProductRequest request) {
        InsuranceProduct insuranceProduct = InsuranceProduct.builder()
                .name(request.getName())
                .description(request.getDescription())
                .startDate(request.getStartDate())
                .status(request.getStatus())
                .build();

        InsuranceProduct savedProduct = insuranceProductRepository.save(insuranceProduct);
        return mapToResponse(savedProduct);
    }

    public InsuranceProductResponse getInsuranceProductById(Long id) {
        InsuranceProduct insuranceProduct = insuranceProductRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("보험 상품을 찾을 수 없습니다."));
        return mapToResponse(insuranceProduct);
    }

    public List<InsuranceProductResponse> getAllInsuranceProducts() {
        List<InsuranceProduct> products = insuranceProductRepository.findAll();
        return products.stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional
    public InsuranceProductResponse updateInsuranceProduct(Long id, InsuranceProductRequest request) {
        InsuranceProduct insuranceProduct = insuranceProductRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("보험 상품을 찾을 수 없습니다."));

        insuranceProduct.setName(request.getName());
        insuranceProduct.setDescription(request.getDescription());
        insuranceProduct.setStartDate(request.getStartDate());
        insuranceProduct.setStatus(request.getStatus());

        InsuranceProduct updatedProduct = insuranceProductRepository.save(insuranceProduct);
        return mapToResponse(updatedProduct);
    }

    @Transactional
    public void deleteInsuranceProduct(Long id) {
        if (!insuranceProductRepository.existsById(id)) {
            throw new RuntimeException("보험 상품을 찾을 수 없습니다.");
        }
        insuranceProductRepository.deleteById(id);
    }

    // 응답 DTO로 매핑하는 메서드
    private InsuranceProductResponse mapToResponse(InsuranceProduct insuranceProduct) {
        return InsuranceProductResponse.builder()
                .id(insuranceProduct.getId())
                .name(insuranceProduct.getName())
                .description(insuranceProduct.getDescription())
                .status(insuranceProduct.getStatus())
                .startDate(insuranceProduct.getStartDate().toString()) // 필요에 따라 LocalDate 포맷 조정
                .build();
    }
}
