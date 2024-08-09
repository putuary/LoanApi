package com.enigmacamp.service.impl;

import com.enigmacamp.model.entity.InstalmentType;
import com.enigmacamp.repository.InstalmentTypeRepository;
import com.enigmacamp.service.InstalmentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstalmentTypeServiceImpl implements InstalmentTypeService {

    private final InstalmentTypeRepository instalmentTypeRepository;
    @Override
    public List<InstalmentType> getAllInstalmentType() {
        return instalmentTypeRepository.findAll();
    }

    @Override
    public InstalmentType saveInstalmentType(InstalmentType instalmentType) {
        return instalmentTypeRepository.save(instalmentType);
    }

    @Override
    public InstalmentType getInstalmentTypeById(String id) {
        if(instalmentTypeRepository.findById(id).isPresent()) {
            return instalmentTypeRepository.findById(id).get();
        } else {
            throw new RuntimeException("Instalment type with id" + id + " not found");
        }
    }

    @Override
    public InstalmentType updateInstalmentType(InstalmentType instalmentType) {
        if(instalmentTypeRepository.findById(instalmentType.getId()).isPresent()) {
            return saveInstalmentType(instalmentType);
        } else {
            throw new RuntimeException("Instalment type with id" + instalmentType.getId() + " not found");
        }
    }

    @Override
    public void deleteInstalmentType(String id) {
        if(instalmentTypeRepository.findById(id).isPresent()) {
            instalmentTypeRepository.deleteById(id);
        } else {
            throw new RuntimeException("Instalment type with id" + id + " not found");
        }
    }
}
