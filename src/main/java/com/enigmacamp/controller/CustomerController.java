package com.enigmacamp.controller;

import com.enigmacamp.model.entity.Customer;
import com.enigmacamp.model.request.CustomerRequest;
import com.enigmacamp.model.response.CommonResponse;
import com.enigmacamp.model.response.CustomerResponse;
import com.enigmacamp.model.response.UserResponse;
import com.enigmacamp.service.CustomerService;
import com.enigmacamp.utils.constant.ApiPathConstant;
import com.enigmacamp.utils.constant.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPathConstant.API + ApiPathConstant.CUSTOMER)
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<CustomerResponse>> getCustomerById(@PathVariable String id) {
        CustomerResponse result = customerService.getCustomerById(id);
        String message = String.format(Constant.MESSAGE_SUCCESS_GET_ID, "customer", id);
        CommonResponse<CustomerResponse> response=new CommonResponse<>();
        response.setData(result);
        response.setMessage(message);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping()
    public ResponseEntity<CommonResponse<List<CustomerResponse>>> getAllCustomer() {
        List<CustomerResponse> result = customerService.getAllCustomer();
        String message = String.format(Constant.MESSAGE_SUCCESS_GET_ALL, "customer");
        CommonResponse<List<CustomerResponse>> response=new CommonResponse<>();
        response.setData(result);
        response.setMessage(message);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<CustomerResponse>> updateCustomer(@RequestBody Customer customer) {
        CustomerResponse result = customerService.updateCustomer(customer);
        String message = String.format(Constant.MESSAGE_SUCCESS_UPDATE, "customer", customer.getId());
        CommonResponse<CustomerResponse> response=new CommonResponse<>();
        response.setData(result);
        response.setMessage(message);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<CustomerResponse>> deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomer(id);
        String message = String.format(Constant.MESSAGE_SUCCESS_DELETE, "customer", id);
        CommonResponse<CustomerResponse> response=new CommonResponse<>();
        response.setData(null);
        response.setMessage(message);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}
