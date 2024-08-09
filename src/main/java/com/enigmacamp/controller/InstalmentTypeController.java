package com.enigmacamp.controller;

import com.enigmacamp.model.entity.InstalmentType;
import com.enigmacamp.model.response.CommonResponse;
import com.enigmacamp.service.InstalmentTypeService;
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
@RequestMapping(ApiPathConstant.API + ApiPathConstant.INSTALMEN_TYPE)
public class InstalmentTypeController {

    private final InstalmentTypeService instalmentTypeService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseEntity<CommonResponse<InstalmentType>> createInstalmentType(@RequestBody InstalmentType instalmentType) {
        InstalmentType result = instalmentTypeService.saveInstalmentType(instalmentType);
        String message = String.format(Constant.MESSAGE_SUCCESS_INSERT, "instalment type", instalmentType.getId());
        CommonResponse<InstalmentType> response=new CommonResponse<>();
        response.setData(result);
        response.setMessage(message);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<InstalmentType>> getInstalmentTypeById(@PathVariable String id) {
        InstalmentType result = instalmentTypeService.getInstalmentTypeById(id);
        String message = String.format(Constant.MESSAGE_SUCCESS_GET_ID, "instalment type", id);
        CommonResponse<InstalmentType> response=new CommonResponse<>();
        response.setData(result);
        response.setMessage(message);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping()
    public ResponseEntity<CommonResponse<List<InstalmentType>>> getAllInstalmentType() {
        List<InstalmentType> result = instalmentTypeService.getAllInstalmentType();
        String message = String.format(Constant.MESSAGE_SUCCESS_GET_ALL, "instalment type");
        CommonResponse<List<InstalmentType>> response=new CommonResponse<>();
        response.setData(result);
        response.setMessage(message);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseEntity<CommonResponse<InstalmentType>> updateInstalmentType(@RequestBody InstalmentType instalmentType) {
        InstalmentType result = instalmentTypeService.updateInstalmentType(instalmentType);
        String message = String.format(Constant.MESSAGE_SUCCESS_UPDATE, "instalment type", instalmentType.getId());
        CommonResponse<InstalmentType> response=new CommonResponse<>();
        response.setData(result);
        response.setMessage(message);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<InstalmentType>> deleteInstalmentType(@PathVariable String id) {
        instalmentTypeService.deleteInstalmentType(id);
        String message = String.format(Constant.MESSAGE_SUCCESS_DELETE, "instalment type", id);
        CommonResponse<InstalmentType> response=new CommonResponse<>();
        response.setData(null);
        response.setMessage(message);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}
