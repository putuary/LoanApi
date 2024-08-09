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
public class UserResponse {
    private String email;
    private List<ERole> role;
}