package com.ecommerce.back.security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.back.security.entities.Role;
import com.ecommerce.back.security.enums.RoleList;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(RoleList roleName);

}
