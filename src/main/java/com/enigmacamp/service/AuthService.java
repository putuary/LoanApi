package com.enigmacamp.service;

import com.enigmacamp.model.request.AuthRequest;
import com.enigmacamp.model.response.LoginResponse;
import com.enigmacamp.model.response.RegisterResponse;

public interface AuthService {
    RegisterResponse registerCustomer(AuthRequest authRequest);

    LoginResponse loginCustomer(AuthRequest authRequest);
}
