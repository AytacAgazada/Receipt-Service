package com.example.receiptservice.service.impl;

import com.example.receiptservice.dto.DrugCreateDto;
import com.example.receiptservice.dto.DrugDto;
import com.example.receiptservice.dto.DrugUpdateDto;

import java.util.List;

public interface DrugService {
    DrugDto createDrug(DrugCreateDto drugCreateDto);
    DrugDto getDrugById(Long id);
    List<DrugDto> getAllDrugs();
    DrugDto updateDrug(Long id, DrugUpdateDto drugUpdateDto);
    void deleteDrug(Long id);
}
