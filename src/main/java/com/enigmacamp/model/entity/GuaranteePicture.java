package com.enigmacamp.model.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "t_guarantee_picture")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class GuaranteePicture {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String contentType;
    private String name;
    private String path;
    private Long size;
}
