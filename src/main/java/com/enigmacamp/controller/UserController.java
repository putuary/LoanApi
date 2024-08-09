package com.enigmacamp.controller;

import com.enigmacamp.model.response.CommonResponse;
import com.enigmacamp.model.response.UserResponse;
import com.enigmacamp.service.UserService;
import com.enigmacamp.utils.constant.ApiPathConstant;
import com.enigmacamp.utils.constant.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPathConstant.API + ApiPathConstant.USER)
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<UserResponse>> getUserById(@PathVariable String id) {
        UserResponse result = userService.getUserById(id);
        String message =String.format(Constant.MESSAGE_SUCCESS_GET_ID, "user", id);
        CommonResponse<UserResponse> response=new CommonResponse<>();
        response.setData(result);
        response.setMessage(message);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}
