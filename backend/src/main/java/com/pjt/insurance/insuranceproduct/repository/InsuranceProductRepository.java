package com.pjt.insurance.insuranceproduct.repository;

 import com.pjt.insurance.insuranceproduct.model.entity.InsuranceProduct;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InsuranceProductRepository extends JpaRepository<InsuranceProduct,Long> {

}
