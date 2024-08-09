package com.enigmacamp.controller;

import com.enigmacamp.model.request.AuthRequest;
import com.enigmacamp.model.response.CommonResponse;
import com.enigmacamp.model.response.LoginResponse;
import com.enigmacamp.model.response.RegisterResponse;
import com.enigmacamp.service.AuthService;
import com.enigmacamp.utils.constant.ApiPathConstant;
import com.enigmacamp.utils.constant.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPathConstant.API + ApiPathConstant.AUTH)
public class AuthController {
    private final AuthService authService;
    @PostMapping("/signup")
    public ResponseEntity<CommonResponse<RegisterResponse>> registerCustomer (@RequestBody AuthRequest authRequest) {
        RegisterResponse result = authService.registerCustomer(authRequest);
        String message =String.format(Constant.MESSAGE_SUCCESS_REGISTERED, result.getEmail());
        CommonResponse<RegisterResponse> response=new CommonResponse<>();
        response.setData(result);
        response.setMessage(message);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PostMapping("/signin")
    public ResponseEntity<CommonResponse<LoginResponse>> loginCustomer (@RequestBody AuthRequest authRequest) {
        LoginResponse result = authService.loginCustomer(authRequest);
        String message =String.format(Constant.MESSAGE_SUCCESS_LOGIN, result.getEmail());
        CommonResponse<LoginResponse> response=new CommonResponse<>();
        response.setData(result);
        response.setMessage(message);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}
