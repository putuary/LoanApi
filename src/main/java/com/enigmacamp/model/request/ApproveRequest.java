package com.enigmacamp.model.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApproveRequest {
    private String loanTransactionId;
    private Integer interestRates;
}
