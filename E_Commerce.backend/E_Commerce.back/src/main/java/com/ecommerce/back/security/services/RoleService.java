package com.ecommerce.back.security.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.back.security.entities.Role;
import com.ecommerce.back.security.enums.RoleList;
import com.ecommerce.back.security.repositories.RoleRepository;

@Service
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Optional<Role> getByRoleName(RoleList roleName) {
        return roleRepository.findByRoleName(roleName);
    }

}