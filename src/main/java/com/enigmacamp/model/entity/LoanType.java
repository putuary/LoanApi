package com.enigmacamp.model.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "t_loan_type")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class LoanType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "loan_type_id")
    private String id;
    private String type;
    @Column(name = "maximum_loan")
    private Double maxLoan;
}
