package com.example.receiptservice.service.impl;

import com.example.receiptservice.dto.ReceiptItemCreateDto;
import com.example.receiptservice.dto.ReceiptItemDto;

import java.util.List;

public interface ReceiptItemService {
    ReceiptItemDto createReceiptItem(ReceiptItemCreateDto receiptItemCreateDto);
    ReceiptItemDto getReceiptItemById(Long id);
    List<ReceiptItemDto> getAllReceiptItems();
    ReceiptItemDto updateReceiptItem(Long id, ReceiptItemDto receiptItemDto);
    void deleteReceiptItem(Long id);
}
