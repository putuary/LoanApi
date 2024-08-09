package com.enigmacamp.service.impl;

import com.enigmacamp.model.entity.AppUser;
import com.enigmacamp.model.entity.Customer;
import com.enigmacamp.model.entity.Role;
import com.enigmacamp.model.entity.User;
import com.enigmacamp.model.request.AuthRequest;
import com.enigmacamp.model.response.LoginResponse;
import com.enigmacamp.model.response.RegisterResponse;
import com.enigmacamp.repository.UserRepository;
import com.enigmacamp.security.JwtUtil;
import com.enigmacamp.service.AuthService;
import com.enigmacamp.service.CustomerService;
import com.enigmacamp.service.RoleService;
import com.enigmacamp.utils.constant.ERole;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final RoleService roleService;
    private final CustomerService customerService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final  ERole[] listRole = ERole.values();

    @Override
    @Transactional
    public RegisterResponse registerCustomer(AuthRequest authRequest) {
        try {
            // TODO Set Role
            boolean isCustomer=false;
            List<Role> roles = new ArrayList<>();
            List<ERole> roleNames = new ArrayList<>();
            for(Integer roleId :authRequest.getRole_id()) {
                Role role = roleService.getOrSave(listRole[roleId - 1]);
                roles.add(role);
                roleNames.add(role.getRole());

                if(roleId == 1) {
                    isCustomer=true;
                }
            }

            // TODO Set Credential/user
            User user = User.builder()
                    .email(authRequest.getEmail())
                    .password(passwordEncoder.encode(authRequest.getPassword()))
                    .roles(roles)
                    .build();
            userRepository.save(user);

            // TODO : Set Customer
            if(isCustomer) {
                Customer customer = Customer.builder()
                        .firstName(authRequest.getFirstName())
                        .lastName(authRequest.getLastName())
                        .dateOfBirth(authRequest.getDateOfBirth())
                        .phone(authRequest.getPhone())
                        .status(true)
                        .user(user)
                        .build();
                customerService.saveCustomer(customer);
            }

            // TODO Create Response
            return RegisterResponse.builder()
                    .email(user.getEmail())
                    .role(roleNames)
                    .build();
        } catch (DataIntegrityViolationException e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User Already Exist");
        }
    }

    @Override
    public LoginResponse loginCustomer(AuthRequest authRequest) {
        System.out.println(authRequest.getEmail());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authRequest.getEmail(),
                authRequest.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        AppUser appUser = (AppUser) authentication.getPrincipal();
        String token = jwtUtil.generateToken(appUser);

        List<ERole> roles = new ArrayList<>();
        for(Role role : appUser.getRoles()) {
            roles.add(role.getRole());
        }
        return LoginResponse.builder()
                .email(appUser.getEmail())
                .role(roles)
                .token(token)
                .build();
    }
}
