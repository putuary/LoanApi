package com.enigmacamp.controller;

import com.enigmacamp.model.entity.LoanType;
import com.enigmacamp.model.request.ApproveRequest;
import com.enigmacamp.model.request.LoanRequest;
import com.enigmacamp.model.response.CommonResponse;
import com.enigmacamp.model.response.TransactionResponse;
import com.enigmacamp.service.TransactionService;
import com.enigmacamp.utils.constant.ApiPathConstant;
import com.enigmacamp.utils.constant.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPathConstant.API + ApiPathConstant.TRANSACTION)
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<CommonResponse<TransactionResponse>> requestLoan(@RequestBody LoanRequest loanRequest) {
        TransactionResponse result = transactionService.requestLoan(loanRequest);
        String message = String.format(Constant.MESSAGE_SUCCESS_INSERT, "request loan");
        CommonResponse<TransactionResponse> response=new CommonResponse<>();
        response.setData(result);
        response.setMessage(message);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PutMapping("/{adminId}/approve")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseEntity<CommonResponse<TransactionResponse>> approveTransaction(@RequestBody ApproveRequest approveRequest, @PathVariable String adminId) {
        TransactionResponse result = transactionService.approveTransaction(approveRequest, adminId);
        String message = String.format(Constant.MESSAGE_SUCCESS_APPROVE,  result.getId());
        CommonResponse<TransactionResponse> response=new CommonResponse<>();
        response.setData(result);
        response.setMessage(message);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<TransactionResponse>> getTransactionById(@PathVariable String id) {
        TransactionResponse result = transactionService.getTransactionById(id);
        String message = String.format(Constant.MESSAGE_SUCCESS_GET_ID, "transaction", id);
        CommonResponse<TransactionResponse> response=new CommonResponse<>();
        response.setData(result);
        response.setMessage(message);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PutMapping("/{trxId}/pay")
    public ResponseEntity<CommonResponse<TransactionResponse>> payInstalment(@PathVariable String trxId) {
        transactionService.payInstalment(trxId);
        String message = String.format(Constant.MESSAGE_SUCCESS_PAY, trxId);
        CommonResponse<TransactionResponse> response=new CommonResponse<>();
        response.setData(null);
        response.setMessage(message);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }



}
