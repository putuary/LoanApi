package com.enigmacamp.service.impl;

import com.enigmacamp.model.entity.*;
import com.enigmacamp.model.request.ApproveRequest;
import com.enigmacamp.model.request.LoanRequest;
import com.enigmacamp.model.response.TransactionDetailResponse;
import com.enigmacamp.model.response.TransactionResponse;
import com.enigmacamp.repository.CustomerRepository;
import com.enigmacamp.repository.LoanTransactionDetailRepository;
import com.enigmacamp.repository.LoanTransactionRepository;
import com.enigmacamp.repository.UserRepository;
import com.enigmacamp.service.*;
import com.enigmacamp.utils.constant.ApprovalStatus;
import com.enigmacamp.utils.constant.LoanStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final LoanTransactionRepository loanTransactionRepository;
    private final InstalmentTypeService instalmentTypeService;
    private final LoanTypeService loanTypeService;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final LoanTransactionDetailRepository loanTransactionDetailRepository;

    @Override
    @Transactional
    public TransactionResponse requestLoan(LoanRequest loanRequest) {
        InstalmentType instalmentType = instalmentTypeService.getInstalmentTypeById(loanRequest.getInstalmentType().getId());
        LoanType loanType = loanTypeService.getLoanTypeById(loanRequest.getLoanType().getId());
        Customer customer = customerRepository.findById(loanRequest.getCustomer().getId()).get();

        LoanTransaction loanTransaction = LoanTransaction.builder()
                .instalmentType(instalmentType)
                .loanType(loanType)
                .customer(customer)
                .nominal(loanRequest.getNominal())
                .createdAt(System.currentTimeMillis())
                .build();
        LoanTransaction loanTransactionCreated = loanTransactionRepository.save(loanTransaction);

        LoanTransactionDetail loanTransactionDetail = LoanTransactionDetail.builder()
                .loanTransaction(loanTransactionCreated)
                .nominal(loanRequest.getNominal())
                .transactionDate(loanTransactionCreated.getCreatedAt())
                .loanStatus(LoanStatus.PAID)
                .createdAt(loanTransactionCreated.getCreatedAt())
                .build();
        LoanTransactionDetail loanTransactionDetailCreated = loanTransactionDetailRepository.save(loanTransactionDetail);

        List<TransactionDetailResponse> loanTransactionDetailResponses = new ArrayList<>();
        TransactionDetailResponse transactionDetailResponse = TransactionDetailResponse.builder()
                .id(loanTransactionDetailCreated.getId())
                .transactionDate(loanTransactionDetailCreated.getTransactionDate())
                .nominal(loanTransactionDetailCreated.getNominal())
                .loanStatus(loanTransactionDetailCreated.getLoanStatus())
                .createdAt(loanTransactionDetailCreated.getCreatedAt())
                .build();
        loanTransactionDetailResponses.add(transactionDetailResponse);

        return TransactionResponse.builder()
                .id(loanTransactionCreated.getId())
                .loanTypeId(loanTransactionCreated.getLoanType().getId())
                .instalmentTypeId(loanTransactionCreated.getInstalmentType().getId())
                .customerId(loanTransactionCreated.getCustomer().getId())
                .nominal(loanTransactionCreated.getNominal())
                .createdAt(loanTransactionCreated.getCreatedAt())
                .transactionDetailResponses(loanTransactionDetailResponses)
                .build();
    }

    @Override
    public TransactionResponse approveTransaction(ApproveRequest approveRequest, String id) {
        if (loanTransactionRepository.findById(approveRequest.getLoanTransactionId()).isPresent()) {
            LoanTransaction loanTransaction = loanTransactionRepository.findById(approveRequest.getLoanTransactionId()).get();
            User user = userRepository.findById(id).get();
            if(approveRequest.getInterestRates() >= 3) {
                loanTransaction.setApprovalStatus(ApprovalStatus.APPROVED);
                loanTransaction.setApprovedBy(user.getEmail());
                loanTransaction.setApprovedAt(System.currentTimeMillis());
                loanTransactionRepository.save(loanTransaction);

                List<TransactionDetailResponse> loanTransactionDetailResponses = new ArrayList<>();
                for(LoanTransactionDetail loanTransactionDetail : loanTransaction.getLoanTransactionDetails()) {
                    TransactionDetailResponse transactionDetailResponse = TransactionDetailResponse.builder()
                            .id(loanTransactionDetail.getId())
                            .transactionDate(loanTransactionDetail.getTransactionDate())
                            .nominal(loanTransactionDetail.getNominal())
                            .loanStatus(loanTransactionDetail.getLoanStatus())
                            .createdAt(loanTransactionDetail.getCreatedAt())
                            .updatedAt(loanTransactionDetail.getUpdatedAt())
                            .build();
                    loanTransactionDetailResponses.add(transactionDetailResponse);
                }


                return TransactionResponse.builder()
                        .id(loanTransaction.getId())
                        .loanTypeId(loanTransaction.getLoanType().getId())
                        .instalmentTypeId(loanTransaction.getInstalmentType().getId())
                        .customerId(loanTransaction.getCustomer().getId())
                        .nominal(loanTransaction.getNominal())
                        .approvedAt(loanTransaction.getApprovedAt())
                        .approvedBy(loanTransaction.getApprovedBy())
                        .approvalStatus(loanTransaction.getApprovalStatus())
                        .transactionDetailResponses(loanTransactionDetailResponses)
                        .createdAt(loanTransaction.getCreatedAt())
                        .updatedAt(loanTransaction.getUpdatedAt())
                        .build();
            }
            return null;
        } else{
            throw new RuntimeException("Transaction Not Found");
        }
    }

    @Override
    public TransactionResponse getTransactionById(String id) {

        if (loanTransactionRepository.findById(id).isPresent()) {

            LoanTransaction loanTransaction = loanTransactionRepository.findById(id).get();

            List<TransactionDetailResponse> loanTransactionDetailResponses = new ArrayList<>();
            for (LoanTransactionDetail loanTransactionDetail : loanTransaction.getLoanTransactionDetails()) {
                TransactionDetailResponse transactionDetailResponse = TransactionDetailResponse.builder()
                        .id(loanTransactionDetail.getId())
                        .transactionDate(loanTransactionDetail.getTransactionDate())
                        .nominal(loanTransactionDetail.getNominal())
                        .loanStatus(loanTransactionDetail.getLoanStatus())
                        .createdAt(loanTransactionDetail.getCreatedAt())
                        .updatedAt(loanTransactionDetail.getUpdatedAt())
                        .build();
                loanTransactionDetailResponses.add(transactionDetailResponse);
            }


            return TransactionResponse.builder()
                    .id(loanTransaction.getId())
                    .loanTypeId(loanTransaction.getLoanType().getId())
                    .instalmentTypeId(loanTransaction.getInstalmentType().getId())
                    .customerId(loanTransaction.getCustomer().getId())
                    .nominal(loanTransaction.getNominal())
                    .approvedAt(loanTransaction.getApprovedAt())
                    .approvedBy(loanTransaction.getApprovedBy())
                    .approvalStatus(loanTransaction.getApprovalStatus())
                    .transactionDetailResponses(loanTransactionDetailResponses)
                    .createdAt(loanTransaction.getCreatedAt())
                    .updatedAt(loanTransaction.getUpdatedAt())
                    .build();
        } else {
            throw new RuntimeException("Transaction Not Found");
        }
    }

    @Override
    public void payInstalment(String id) {
        if (loanTransactionRepository.findById(id).isPresent()) {
            LoanTransaction loanTransaction = loanTransactionRepository.findById(id).get();
            for (LoanTransactionDetail loanTransactionDetail : loanTransaction.getLoanTransactionDetails()) {
                loanTransactionDetail.setLoanStatus(LoanStatus.PAID);
                loanTransactionDetailRepository.save(loanTransactionDetail);
            }
        } else {
            throw new RuntimeException("Transaction Not Found");
        }
    }
}
