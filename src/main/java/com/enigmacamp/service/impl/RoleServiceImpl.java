package com.enigmacamp.service.impl;

import com.enigmacamp.model.entity.Role;
import com.enigmacamp.repository.RoleRepository;
import com.enigmacamp.service.RoleService;
import com.enigmacamp.utils.constant.ERole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getOrSave(ERole role) {
        Optional<Role> optionalRole = roleRepository.findByRole(role);
        if(optionalRole.isPresent()) {
            return optionalRole.get();
        }

        Role currentRole = Role.builder()
                .role(role)
                .build();

        return roleRepository.saveAndFlush(currentRole);
    }
}
