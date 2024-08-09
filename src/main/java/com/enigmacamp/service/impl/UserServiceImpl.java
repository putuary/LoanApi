package com.enigmacamp.service.impl;

import com.enigmacamp.model.entity.AppUser;
import com.enigmacamp.model.entity.Role;
import com.enigmacamp.model.entity.User;
import com.enigmacamp.model.response.UserResponse;
import com.enigmacamp.repository.UserRepository;
import com.enigmacamp.service.UserService;
import com.enigmacamp.utils.constant.ERole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private  final UserRepository userRepository;
    @Override
    public AppUser loadUserByUserId(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Invalid creadetial user"));
        return AppUser.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRoles())
                .build();
    }

    @Override
    public UserResponse getUserById(String id) {
        if(userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).get();
            List<ERole> roles = new ArrayList<>();
            for (Role role : user.getRoles()) {
                roles.add(role.getRole());
            }
            return UserResponse.builder()
                    .email(user.getEmail())
                    .role(roles)
                    .build();
        } else {
            throw new RuntimeException("User with id" + id + " not found");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { // method untuk memverivikasi username
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Invalid creadetial user"));;
        return AppUser.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRoles())
                .build();
    }
}
