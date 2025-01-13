package com.pjt.insurance.Payment.service;

import com.pjt.insurance.Payment.exception.PaymentErrorCode;
import com.pjt.insurance.Payment.exception.PaymentException;
import com.pjt.insurance.Payment.model.dto.request.PaymentRequest;
import com.pjt.insurance.Payment.model.dto.response.PaymentResponse;
import com.pjt.insurance.Payment.model.entity.Payment;
import com.pjt.insurance.Payment.repository.PaymentRepository;
import com.pjt.insurance.Policy.repository.PolicyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PolicyRepository policyRepository; // 정책 리포지토리 추가

    @Transactional
    public PaymentResponse createPayment(PaymentRequest request) {
        // 정책이 존재하는지 확인
        var policy = policyRepository.findById(request.getPolicyId())
                .orElseThrow(() -> new PaymentException(PaymentErrorCode.NOT_EXISTS_POLICY));

        Payment payment = Payment.builder()
                .policy(policy)
                .amount(request.getAmount())
                .status(request.getStatus())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Payment savedPayment = paymentRepository.save(payment);
        return mapToResponse(savedPayment);
    }

    public PaymentResponse getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentException(PaymentErrorCode.NOT_EXISTS_PAYMENT));
        return mapToResponse(payment);
    }

    public List<PaymentResponse> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return payments.stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional
    public PaymentResponse updatePayment(Long id, PaymentRequest request) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentException(PaymentErrorCode.NOT_EXISTS_PAYMENT));

        payment.setAmount(request.getAmount());
        payment.setStatus(request.getStatus());
        payment.setUpdatedAt(LocalDateTime.now());

        Payment updatedPayment = paymentRepository.save(payment);
        return mapToResponse(updatedPayment);
    }

    @Transactional
    public void deletePayment(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new PaymentException(PaymentErrorCode.NOT_EXISTS_PAYMENT);
        }
        paymentRepository.deleteById(id);
    }

    // 응답 DTO로 매핑하는 메서드
    private PaymentResponse mapToResponse(Payment payment) {
        return PaymentResponse.builder()
                .id(payment.getId())
                .policyId(payment.getPolicy().getId()) // 정책 ID 포함
                .amount(payment.getAmount())
                .status(payment.getStatus())
                .createdAt(payment.getCreatedAt().toString()) // 필요에 따라 LocalDateTime 포맷 조정
                .updatedAt(payment.getUpdatedAt().toString())
                .build();
    }
}
