package com.example.receiptservice.dto;

import com.example.receiptservice.entity.FundingSource;
import com.example.receiptservice.entity.ReceiptStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptDto {
    private Long id;
    private Long citizenId;
    private Long doctorId;
    private Long hospitalId;
    private Long fulfilledByPharmacyId;
}
