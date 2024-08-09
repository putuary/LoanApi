package com.enigmacamp.service;

import com.enigmacamp.model.entity.LoanTransaction;
import com.enigmacamp.model.request.ApproveRequest;
import com.enigmacamp.model.request.LoanRequest;
import com.enigmacamp.model.response.TransactionResponse;

public interface TransactionService {
    TransactionResponse requestLoan(LoanRequest loanRequest);
    TransactionResponse approveTransaction(ApproveRequest approveRequest, String id);
    TransactionResponse getTransactionById(String id);
    void payInstalment(String id);
}
