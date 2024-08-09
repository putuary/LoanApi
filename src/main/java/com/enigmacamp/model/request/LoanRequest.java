package com.enigmacamp.model.request;

import com.enigmacamp.model.entity.Customer;
import com.enigmacamp.model.entity.InstalmentType;
import com.enigmacamp.model.entity.LoanType;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanRequest {
    private LoanType loanType;
    private InstalmentType instalmentType;
    private Customer customer;
    private Double nominal;
}
