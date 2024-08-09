package com.enigmacamp.model.response;

import com.enigmacamp.utils.constant.ApprovalStatus;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TransactionResponse {
    private String id;
    private String loanTypeId;
    private String instalmentTypeId;
    private String customerId;
    private Double nominal;
    private Long approvedAt;
    private String approvedBy;
    private ApprovalStatus approvalStatus;
    private List<TransactionDetailResponse> transactionDetailResponses;
    private Long createdAt;
    private Long updatedAt;
}
