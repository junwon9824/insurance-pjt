package com.pjt.insurance.Claim.repository;

import com.pjt.insurance.Claim.model.entity.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimRepository extends JpaRepository<Claim,Long> {

}
