package com.example.store.storeApi.domain.service;

import com.example.store.storeApi.persistence.entity.*;
import com.example.store.storeApi.persistence.entity.Role;
import com.example.store.storeApi.persistence.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
public class InitialDataLoader {

    @Autowired
    private RoleRepository roleRepository;

    @Bean
    public ApplicationRunner initializer() {
        List<RoleName> roles = Arrays.asList(RoleName.ROLE_ADMIN, RoleName.ROLE_USER);
        return args -> roles.forEach(i -> createRoleIfNotFound(i));
    }

    private Optional<Role> createRoleIfNotFound(RoleName roleName) {
        Optional<com.example.store.storeApi.persistence.entity.Role> role = roleRepository.findByName(roleName);
        if (!role.isPresent()) {
            Role newRole = new Role();
            newRole.setName(roleName);
            newRole = roleRepository.save(newRole);
        }
        return role;
    }
}