package com.pjt.insurance.security.repository;

import com.pjt.insurance.security.model.entity.InvalidToken;
import org.springframework.data.repository.CrudRepository;

public interface InvalidTokenRepository extends CrudRepository<InvalidToken, String> {
}
