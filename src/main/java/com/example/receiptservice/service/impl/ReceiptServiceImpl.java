package com.example.receiptservice.service.impl;

import com.example.receiptservice.dto.ReceiptCreateDto;
import com.example.receiptservice.dto.ReceiptDto;
import com.example.receiptservice.dto.ReceiptUpdateDto;
import com.example.receiptservice.entity.Citizen;
import com.example.receiptservice.entity.Doctor;
import com.example.receiptservice.entity.Hospital;
import com.example.receiptservice.entity.Pharmacy;
import com.example.receiptservice.entity.Receipt;
import com.example.receiptservice.repository.CitizenRepository;
import com.example.receiptservice.repository.DoctorRepository;
import com.example.receiptservice.repository.HospitalRepository;
import com.example.receiptservice.repository.PharmacyRepository;
import com.example.receiptservice.repository.ReceiptRepository;
import com.example.receiptservice.service.ReceiptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final CitizenRepository citizenRepository;
    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;
    private final PharmacyRepository pharmacyRepository;

    @Override
    public ReceiptDto createReceipt(ReceiptCreateDto receiptCreateDto) {
        log.info("Creating new receipt with data: {}", receiptCreateDto);
        Receipt receipt = new Receipt();
        
        // Map from ReceiptCreateDto to Receipt entity
        receipt.setSerialNumber(receiptCreateDto.getSerialNumber());
        Citizen citizen = citizenRepository.findById(receiptCreateDto.getCitizenId())
                .orElseThrow(() -> new RuntimeException("Citizen not found"));
        Doctor doctor = doctorRepository.findById(receiptCreateDto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        Hospital hospital = hospitalRepository.findById(receiptCreateDto.getHospitalId())
                .orElseThrow(() -> new RuntimeException("Hospital not found"));

        receipt.setCitizen(citizen);
        receipt.setDoctor(doctor);
        receipt.setHospital(hospital);
        receipt.setStatus(receiptCreateDto.getStatus());
        receipt.setFundingSource(receiptCreateDto.getFundingSource());
        receipt.setQrCodePayload(receiptCreateDto.getQrCodePayload());
        receipt.setIssueDate(receiptCreateDto.getIssueDate());

        Receipt savedReceipt = receiptRepository.save(receipt);
        log.info("Successfully created receipt with id: {}", savedReceipt.getId());
        return convertToDto(savedReceipt);
    }

    @Override
    public ReceiptDto getReceiptById(Long id) {
        log.info("Fetching receipt with id: {}", id);
        Receipt receipt = receiptRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Receipt not found with id: {}", id);
                    return new RuntimeException("Receipt not found with id: " + id);
                });
        log.info("Successfully fetched receipt with id: {}", id);
        return convertToDto(receipt);
    }

    @Override
    public List<ReceiptDto> getAllReceipts() {
        log.info("Fetching all receipts");
        List<Receipt> receipts = receiptRepository.findAll();
        log.info("Found {} receipts", receipts.size());
        return receipts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ReceiptDto updateReceipt(Long id, ReceiptUpdateDto receiptUpdateDto) {
        log.info("Updating receipt with id: {} with data: {}", id, receiptUpdateDto);
        Receipt receipt = receiptRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Receipt not found with id: {}", id);
                    return new RuntimeException("Receipt not found with id: " + id);
                });

        // Partial update from ReceiptUpdateDto
        if (receiptUpdateDto.getSerialNumber() != null) {
            receipt.setSerialNumber(receiptUpdateDto.getSerialNumber());
        }
        if (receiptUpdateDto.getCitizenId() != null) {
            Citizen citizen = citizenRepository.findById(receiptUpdateDto.getCitizenId())
                    .orElseThrow(() -> new RuntimeException("Citizen not found"));
            receipt.setCitizen(citizen);
        }
        if (receiptUpdateDto.getDoctorId() != null) {
            Doctor doctor = doctorRepository.findById(receiptUpdateDto.getDoctorId())
                    .orElseThrow(() -> new RuntimeException("Doctor not found"));
            receipt.setDoctor(doctor);
        }
        if (receiptUpdateDto.getHospitalId() != null) {
            Hospital hospital = hospitalRepository.findById(receiptUpdateDto.getHospitalId())
                    .orElseThrow(() -> new RuntimeException("Hospital not found"));
            receipt.setHospital(hospital);
        }
        if (receiptUpdateDto.getFulfilledByPharmacyId() != null) {
            Pharmacy pharmacy = pharmacyRepository.findById(receiptUpdateDto.getFulfilledByPharmacyId())
                    .orElseThrow(() -> new RuntimeException("Pharmacy not found"));
            receipt.setFulfilledByPharmacy(pharmacy);
        }

        Receipt updatedReceipt = receiptRepository.save(receipt);
        log.info("Successfully updated receipt with id: {}", updatedReceipt.getId());
        return convertToDto(updatedReceipt);
    }

    @Override
    public void deleteReceipt(Long id) {
        log.info("Deleting receipt with id: {}", id);
        if (!receiptRepository.existsById(id)) {
            log.error("Receipt with id: {} not found for deletion", id);
            throw new RuntimeException("Receipt not found with id: " + id);
        }
        receiptRepository.deleteById(id);
        log.info("Successfully deleted receipt with id: {}", id);
    }

    private ReceiptDto convertToDto(Receipt receipt) {
        ReceiptDto dto = new ReceiptDto();
        dto.setId(receipt.getId());
        dto.setCitizenId(receipt.getCitizen().getId());
        dto.setDoctorId(receipt.getDoctor().getId());
        dto.setHospitalId(receipt.getHospital().getId());
        if (receipt.getFulfilledByPharmacy() != null) {
            dto.setFulfilledByPharmacyId(receipt.getFulfilledByPharmacy().getId());
        }
        return dto;
    }
}
