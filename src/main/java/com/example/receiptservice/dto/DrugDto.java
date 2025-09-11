package com.example.receiptservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrugDto {
    private Long id;
    private String tradeName;
    private Boolean isControlledSubstance;
    private Boolean requiresPrescription;
    private Boolean isActive;
    private Instant createdAt;
    private Instant updatedAt;
}
