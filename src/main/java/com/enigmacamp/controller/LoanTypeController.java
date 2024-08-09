package com.enigmacamp.controller;

import com.enigmacamp.model.entity.InstalmentType;
import com.enigmacamp.model.entity.LoanType;
import com.enigmacamp.model.response.CommonResponse;
import com.enigmacamp.service.LoanTypeService;
import com.enigmacamp.utils.constant.ApiPathConstant;
import com.enigmacamp.utils.constant.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPathConstant.API + ApiPathConstant.LOAN_TYPE)
public class LoanTypeController {
    private final LoanTypeService loanTypeService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseEntity<CommonResponse<LoanType>> createLoanType(@RequestBody LoanType loanType) {
        LoanType result = loanTypeService.saveLoanType(loanType);
        String message = String.format(Constant.MESSAGE_SUCCESS_INSERT, "loan type", loanType.getId());
        CommonResponse<LoanType> response=new CommonResponse<>();
        response.setData(result);
        response.setMessage(message);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<LoanType>> getLoanTypeById(@PathVariable String id) {
        LoanType result = loanTypeService.getLoanTypeById(id);
        String message = String.format(Constant.MESSAGE_SUCCESS_GET_ID, "loan type", id);
        CommonResponse<LoanType> response=new CommonResponse<>();
        response.setData(result);
        response.setMessage(message);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping()
    public ResponseEntity<CommonResponse<List<LoanType>>> getAllLoanType() {
        List<LoanType> result = loanTypeService.getAllLoanType();
        String message = String.format(Constant.MESSAGE_SUCCESS_GET_ALL, "loan type");
        CommonResponse<List<LoanType>> response=new CommonResponse<>();
        response.setData(result);
        response.setMessage(message);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseEntity<CommonResponse<LoanType>> updateLoanType(@RequestBody LoanType loanType) {
        LoanType result = loanTypeService.updateLoanType(loanType);
        String message = String.format(Constant.MESSAGE_SUCCESS_UPDATE, "loan type", loanType.getId());
        CommonResponse<LoanType> response=new CommonResponse<>();
        response.setData(result);
        response.setMessage(message);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<InstalmentType>> deleteInstalmentType(@PathVariable String id) {
        loanTypeService.deleteLoanType(id);
        String message = String.format(Constant.MESSAGE_SUCCESS_DELETE, "loan type", id);
        CommonResponse<InstalmentType> response=new CommonResponse<>();
        response.setData(null);
        response.setMessage(message);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}
