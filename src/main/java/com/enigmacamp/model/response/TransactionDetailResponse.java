package com.enigmacamp.model.response;

import com.enigmacamp.utils.constant.LoanStatus;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TransactionDetailResponse {
    private String id;
    private Long transactionDate;
    private Double nominal;
    private LoanStatus loanStatus;
    private Long createdAt;
    private Long updatedAt;
}
