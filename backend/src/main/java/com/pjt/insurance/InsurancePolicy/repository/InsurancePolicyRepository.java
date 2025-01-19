package com.pjt.insurance.InsurancePolicy.repository;

import com.pjt.insurance.InsurancePolicy.model.entity.InsurancePolicy;
import com.pjt.insurance.insuranceproduct.model.entity.InsuranceProduct;
import com.pjt.insurance.user.model.entity.MemberProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicy, Long> {

    InsurancePolicy findByMemberProfileAndInsuranceProduct(MemberProfile memberProfile, InsuranceProduct insuranceProduct);
}
