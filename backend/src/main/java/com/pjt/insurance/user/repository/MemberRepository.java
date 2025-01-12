package com.pjt.insurance.user.repository;

import com.pjt.insurance.user.model.entity.MemberProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberProfile, Long> {
    Optional<MemberProfile> findByEmail(String email);

    Optional<MemberProfile> findByEmailAndAuthProvider(String email, String provider);

}