package com.pjt.insurance.InsurancePolicy.service;

import com.pjt.insurance.InsurancePolicy.model.dto.request.InsuranceProductRequest;
import com.pjt.insurance.InsurancePolicy.model.dto.response.InsuranceProductResponse;
import com.pjt.insurance.InsurancePolicy.repository.InsurancePolicyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class InsurancePolicyService {

    private final InsurancePolicyRepository  insurancePolicyRepository;



}
