package com.enigmacamp.model.response;

import com.enigmacamp.model.entity.Role;
import com.enigmacamp.utils.constant.ERole;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class LoginResponse {
    private String email;
    private List<ERole> role;
    private String token;
}
