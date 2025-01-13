package com.pjt.insurance.Payment.controller;

import com.pjt.insurance.Payment.model.dto.request.PaymentRequest; // DTO 변경
import com.pjt.insurance.Payment.model.dto.response.PaymentResponse; // DTO 변경
import com.pjt.insurance.Payment.service.PaymentService; // 서비스 이름 변경
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
@RequestMapping("/v1/payments") // 경로 변경
@Tag(name = "결제 API", description = "결제와 관련된 기능 제공") // 태그 변경
public class PaymentController {

    private final PaymentService paymentService; // 서비스 이름 변경

    @PostMapping
    @Operation(summary = "결제 등록", description = "새로운 결제를 등록합니다.") // 설명 변경
    public ResponseEntity<PaymentResponse> createPayment(@Valid @RequestBody PaymentRequest request) { // 요청 DTO 변경
        PaymentResponse response = paymentService.createPayment(request); // 서비스 메서드 변경
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "결제 조회", description = "특정 결제 정보를 조회합니다.") // 설명 변경
    public ResponseEntity<PaymentResponse> getPayment(@PathVariable Long id) { // 응답 DTO 변경
        PaymentResponse response = paymentService.getPaymentById(id); // 서비스 메서드 변경
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "모든 결제 조회", description = "모든 결제 정보를 조회합니다.") // 설명 변경
    public ResponseEntity<List<PaymentResponse>> getAllPayments() { // 응답 DTO 변경
        List<PaymentResponse> responses = paymentService.getAllPayments(); // 서비스 메서드 변경
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    @Operation(summary = "결제 업데이트", description = "특정 결제 정보를 업데이트합니다.") // 설명 변경
    public ResponseEntity<PaymentResponse> updatePayment(@PathVariable Long id, @Valid @RequestBody PaymentRequest request) { // 요청 DTO 변경
        PaymentResponse response = paymentService.updatePayment(id, request); // 서비스 메서드 변경
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "결제 삭제", description = "특정 결제를 삭제합니다.") // 설명 변경
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) { // 메서드 이름 변경
        paymentService.deletePayment(id); // 서비스 메서드 변경
        return ResponseEntity.noContent().build();
    }
}
