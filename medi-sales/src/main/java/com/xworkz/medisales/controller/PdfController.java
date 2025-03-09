package com.xworkz.medisales.controller;

import com.xworkz.medisales.dto.BillDto;
import com.xworkz.medisales.dto.CreateStockDto;
import com.xworkz.medisales.dto.MediSalesDto;
import com.xworkz.medisales.services.billservice.BillService;
import com.xworkz.medisales.services.distributorService.CreateStockService;
import com.xworkz.medisales.services.medisalesservice.MediSalesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping

public class PdfController {

    @Autowired
    BillService billService;

    @Autowired
    CreateStockService createStockService;

    @Autowired
    MediSalesService mediSalesService;
    @GetMapping("generatePdf")
    public String getPdf(HttpServletRequest request){

        // Retrieve attributes safely from the session


        List<BillDto> billDtoList = (List<BillDto>) request.getSession().getAttribute("listBillDto");
        if (billDtoList == null) {
            billDtoList = new ArrayList<>();
        }

        List<CreateStockDto> createStockDtoList = (List<CreateStockDto>) request.getSession().getAttribute("productDtoList");
        if (createStockDtoList == null) {
            createStockDtoList = new ArrayList<>();
        }

        MediSalesDto medDto = (MediSalesDto) request.getSession().getAttribute("vendor");
        if (medDto == null) {
            medDto = new MediSalesDto();
        }

        MediSalesDto medDtos = (MediSalesDto) request.getSession().getAttribute("sessionDto");
        if (medDtos == null) {
            medDtos = new MediSalesDto();
        }

        Double finalAmount = (Double) request.getSession().getAttribute("totolCost");
        if (finalAmount == null) {
            finalAmount = 0.0;
        }

        log.info("get pdf details: {}", billDtoList);
        billService.pdfGenerate(billDtoList, createStockDtoList, medDto, finalAmount, medDtos);

        return "generatePdf";

    }

}
