package com.enigmacamp.model.entity;

import com.enigmacamp.utils.constant.ApprovalStatus;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "trx_loan")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class LoanTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus; // enum
    private Long approvedAt;
    private String approvedBy;
    private Long createdAt;
    private Long updatedAt;
    private Double nominal;
    private Long rejectAt;
    private Long rejectBy;

    @ManyToOne
    @JoinColumn(name = "loan_type_id")
    private LoanType loanType;

    @ManyToOne
    @JoinColumn(name = "instalment_type_id")
    private InstalmentType instalmentType;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "loanTransaction", cascade = CascadeType.ALL)
    private List<LoanTransactionDetail> loanTransactionDetails;
}