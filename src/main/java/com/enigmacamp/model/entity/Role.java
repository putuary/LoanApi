package com.enigmacamp.model.entity;

import com.enigmacamp.utils.constant.ERole;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "t_role")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "role_id")
    private String id;
    @Enumerated(EnumType.STRING)
    private ERole role; // Enum
}
