package com.enigmacamp.service;

import com.enigmacamp.model.entity.LoanType;

import java.util.List;

public interface LoanTypeService {
    List<LoanType> getAllLoanType();
    LoanType saveLoanType(LoanType loanType);
    LoanType getLoanTypeById(String id);
    LoanType updateLoanType(LoanType loanType);
    void deleteLoanType(String id);
}
