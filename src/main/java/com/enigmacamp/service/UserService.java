package com.enigmacamp.service;

import com.enigmacamp.model.entity.AppUser;
import com.enigmacamp.model.response.UserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService  extends UserDetailsService {
    AppUser loadUserByUserId(String id);
    UserResponse getUserById(String id);
}
