package com.example.receiptservice.service;

import com.example.receiptservice.repository.ReceiptRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DrugService {

    private final ReceiptRepository receiptRepository;


}
