package com.example.receiptservice.service;

import com.example.receiptservice.dto.DrugCreateDto;
import com.example.receiptservice.dto.DrugDto;
import com.example.receiptservice.dto.DrugUpdateDto;
import com.example.receiptservice.entity.Drug;
import com.example.receiptservice.repository.DrugRepository;
import com.example.receiptservice.service.impl.DrugServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DrugService implements DrugServiceImpl {

    private final DrugRepository drugRepository;

    @Override
    public DrugDto createDrug(DrugCreateDto drugCreateDto) {
        Drug drug = new Drug();
        drug.setTrade_name(drugCreateDto.getTradeName());
        drug.setIsControlledSubstance(drugCreateDto.getIsControlledSubstance());
        drug.setRequiresPrescription(drugCreateDto.getRequiresPrescription());
        drug.setIsActive(true);
        Drug savedDrug = drugRepository.save(drug);
        return convertToDto(savedDrug);
    }

    @Override
    public DrugDto getDrugById(Long id) {
        Drug drug = drugRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Drug not found with id: " + id));
        return convertToDto(drug);
    }

    @Override
    public List<DrugDto> getAllDrugs() {
        return drugRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public DrugDto updateDrug(Long id, DrugUpdateDto drugUpdateDto) {
        Drug drug = drugRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Drug not found with id: " + id));

        if (drugUpdateDto.getTradeName() != null) {
            drug.setTrade_name(drugUpdateDto.getTradeName());
        }
        if (drugUpdateDto.getIsControlledSubstance() != null) {
            drug.setIsControlledSubstance(drugUpdateDto.getIsControlledSubstance());
        }
        if (drugUpdateDto.getRequiresPrescription() != null) {
            drug.setRequiresPrescription(drugUpdateDto.getRequiresPrescription());
        }
        if (drugUpdateDto.getIsActive() != null) {
            drug.setIsActive(drugUpdateDto.getIsActive());
        }

        Drug updatedDrug = drugRepository.save(drug);
        return convertToDto(updatedDrug);
    }

    @Override
    public void deleteDrug(Long id) {
        Drug drug = drugRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Drug not found with id: " + id));
        drugRepository.delete(drug);
    }

    private DrugDto convertToDto(Drug drug) {
        DrugDto drugDto = new DrugDto();
        drugDto.setId(drug.getId());
        drugDto.setTradeName(drug.getTrade_name());
        drugDto.setIsControlledSubstance(drug.getIsControlledSubstance());
        drugDto.setRequiresPrescription(drug.getRequiresPrescription());
        drugDto.setIsActive(drug.getIsActive());
        drugDto.setCreatedAt(drug.getCreatedAt());
        drugDto.setUpdatedAt(drug.getUpdatedAt());
        return drugDto;
    }
}