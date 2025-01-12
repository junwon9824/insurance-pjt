package com.pjt.insurance.InsurancePolicy.repository;

import com.pjt.insurance.InsurancePolicy.model.entity.InsurancePolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicy, Long> {

}
