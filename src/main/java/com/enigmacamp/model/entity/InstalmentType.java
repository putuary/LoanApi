package com.enigmacamp.model.entity;

import com.enigmacamp.utils.constant.EInstalmentType;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "t_instalment_type")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class InstalmentType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Enumerated(EnumType.STRING)
    private EInstalmentType instalmentType;
//    @OneToMany(mappedBy = "instalmentType")
//    private List<LoanTransaction> loanTransactions;
}
