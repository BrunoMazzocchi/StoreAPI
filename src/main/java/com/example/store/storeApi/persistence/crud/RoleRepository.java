package com.example.store.storeApi.persistence.crud;
import com.example.store.storeApi.persistence.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface RoleRepository  extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
