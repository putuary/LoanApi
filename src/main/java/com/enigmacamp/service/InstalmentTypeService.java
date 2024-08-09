package com.enigmacamp.service;

import com.enigmacamp.model.entity.InstalmentType;

import java.util.List;

public interface InstalmentTypeService {

    List<InstalmentType> getAllInstalmentType();
    InstalmentType saveInstalmentType(InstalmentType instalmentType);
    InstalmentType getInstalmentTypeById(String id);
    InstalmentType updateInstalmentType(InstalmentType instalmentType);
    void deleteInstalmentType(String id);

}
