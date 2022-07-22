package com.example.store.storeApi.persistence.repository;

import com.example.store.storeApi.persistence.entity.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    @Override
    Optional<RefreshToken> findById(Long id);

    Optional<RefreshToken> findByToken(String token);

}