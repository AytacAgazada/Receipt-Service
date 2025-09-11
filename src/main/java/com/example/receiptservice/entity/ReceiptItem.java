package com.example.receiptservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.math.BigDecimal;
import java.time.Instant;


import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "receipt_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "receipt_id", nullable = false)
    private Long receiptId;

    @Column(name = "drug_id", nullable = false)
    private Long drugId;

    @Column(name = "dosage", nullable = false, length = 255)
    private String dosage;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "instructions", nullable = false, columnDefinition = "TEXT")
    private String instructions;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

}
