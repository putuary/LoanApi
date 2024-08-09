package com.enigmacamp.service;

import com.enigmacamp.model.entity.Role;
import com.enigmacamp.utils.constant.ERole;

public interface RoleService {
    Role getOrSave(ERole role);
}
