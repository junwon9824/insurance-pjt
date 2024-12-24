package com.pjt.insurance.security.repository;

import com.ssafy.fullerting.security.model.entity.Token;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<Token, Long>{
}
