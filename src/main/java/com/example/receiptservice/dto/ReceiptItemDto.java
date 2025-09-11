package com.example.receiptservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptItemDto {
    private Long id;
    private Long receiptId;
    private Long drugId;
    private String dosage;
    private Integer quantity;
    private String instructions;
}
