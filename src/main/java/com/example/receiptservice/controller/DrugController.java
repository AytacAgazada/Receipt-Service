package com.example.receiptservice.controller;

import com.example.receiptservice.dto.DrugCreateDto;
import com.example.receiptservice.dto.DrugDto;
import com.example.receiptservice.dto.DrugUpdateDto;
import com.example.receiptservice.service.impl.DrugServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drugs")
public class DrugController {

    private final DrugServiceImpl drugService;

    public DrugController(DrugServiceImpl drugService) {
        this.drugService = drugService;
    }

    @PostMapping
    public ResponseEntity<DrugDto> createDrug(@Valid @RequestBody DrugCreateDto drugCreateDto) {
        DrugDto createdDrug = drugService.createDrug(drugCreateDto);
        return new ResponseEntity<>(createdDrug, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DrugDto> getDrugById(@PathVariable Long id) {
        DrugDto drugDto = drugService.getDrugById(id);
        return ResponseEntity.ok(drugDto);
    }

    @GetMapping
    public ResponseEntity<List<DrugDto>> getAllDrugs() {
        List<DrugDto> drugs = drugService.getAllDrugs();
        return ResponseEntity.ok(drugs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DrugDto> updateDrug(@PathVariable Long id, @Valid @RequestBody DrugUpdateDto drugUpdateDto) {
        DrugDto updatedDrug = drugService.updateDrug(id, drugUpdateDto);
        return ResponseEntity.ok(updatedDrug);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDrug(@PathVariable Long id) {
        drugService.deleteDrug(id);
        return ResponseEntity.noContent().build();
    }
}
