package com.enigmacamp.service.impl;


import com.enigmacamp.model.entity.LoanType;
import com.enigmacamp.repository.LoanTypeRepository;
import com.enigmacamp.service.LoanTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanTypeServiceImpl implements LoanTypeService {
    private final LoanTypeRepository loanTypeRepository;
    @Override
    public List<LoanType> getAllLoanType() {
        return loanTypeRepository.findAll();
    }

    @Override
    public LoanType saveLoanType(LoanType loanType) {
        return loanTypeRepository.save(loanType);
    }

    @Override
    public LoanType getLoanTypeById(String id) {
        if(loanTypeRepository.findById(id).isPresent()) {
            return loanTypeRepository.findById(id).get();
        } else {
            throw new RuntimeException("Loan type with id" + id + " not found");
        }
    }

    @Override
    public LoanType updateLoanType(LoanType loanType) {
        if(loanTypeRepository.findById(loanType.getId()).isPresent()) {
            return saveLoanType(loanType);
        } else {
            throw new RuntimeException("Loan type with id" + loanType.getId() + " not found");
        }
    }

    @Override
    public void deleteLoanType(String id) {
        if(loanTypeRepository.findById(id).isPresent()) {
            loanTypeRepository.deleteById(id);
        } else {
            throw new RuntimeException("Loan type with id" + id + " not found");
        }
    }
}
