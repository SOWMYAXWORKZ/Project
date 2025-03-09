package com.xworkz.medisales.services.billservice;

import com.xworkz.medisales.dto.BillDto;
import com.xworkz.medisales.dto.CreateStockDto;
import com.xworkz.medisales.dto.MediSalesDto;
import com.xworkz.medisales.entity.BillEntity;

import java.time.LocalDate;
import java.util.List;

public interface BillService {

    Long generateBillNum(Long existingBillNo);

    LocalDate getBillDate();

    boolean getTotalAmount(CreateStockDto createStockDto);

    Long findBillNo();

    void saveBill(BillEntity billEntity);

    void pdfGenerate(List<BillDto> billDtoList, List<CreateStockDto> createStockDtoList, MediSalesDto mediSalesDto, Double finalAmount, MediSalesDto medDto);
}
