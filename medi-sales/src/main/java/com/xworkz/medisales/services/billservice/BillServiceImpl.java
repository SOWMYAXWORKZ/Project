package com.xworkz.medisales.services.billservice;

import com.itextpdf.html2pdf.HtmlConverter;
import com.xworkz.medisales.dto.BillDto;
import com.xworkz.medisales.dto.CreateStockDto;
import com.xworkz.medisales.dto.MediSalesDto;
import com.xworkz.medisales.entity.BillEntity;
import com.xworkz.medisales.repository.billrepo.BillRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class BillServiceImpl implements BillService {

    @Autowired
    BillRepo billRepo;


    @Autowired
    TemplateEngine templateEngine;

    @Override
    public Long generateBillNum(Long existingBillNo) {
        if (existingBillNo != null && existingBillNo > 0) {
            log.info("Using existing bill number: {}", existingBillNo);
            return existingBillNo;
        }

        // Fetch last bill number from DB
        Long lastBillNo = billRepo.findBillNo();

        // If no previous bill number exists, start from 1000
        Long newBillNo = (lastBillNo != null && lastBillNo > 0) ? lastBillNo + 1 : 1000;

        log.info("Generated new bill number: {}", newBillNo);
        return newBillNo;
    }



    @Override
    public LocalDate getBillDate() {
        return LocalDate.now();
    }
    @Override
    public boolean getTotalAmount(CreateStockDto createStockDto) {
        if (createStockDto.getRate() != null && createStockDto.getQuantity() != null &&
                createStockDto.getDiscount() != null && createStockDto.getGst() != null) {

            double rate = createStockDto.getRate();
            int quantity = createStockDto.getQuantity();
            double discountPercentage = createStockDto.getDiscount();
            double gstPercentage = createStockDto.getGst();


            // Calculate subtotal
            double subtotal = rate * quantity;

            //Calculate discount in  (percentage)
            double discountAmount = (subtotal * discountPercentage) / 100;
            double priceAfterDiscount = subtotal - discountAmount;

            // Calculate  GST in (percentage)
            double gstPrice = (priceAfterDiscount * gstPercentage) / 100;
            double finalAmount = priceAfterDiscount + gstPrice;

            log.info("Rate: {}, Quantity: {}", rate, quantity);
            log.info("Subtotal (Rate * Quantity): {}", subtotal);
            log.info("Discount Amount: {}", discountAmount);
            log.info("Price After Discount: {}", priceAfterDiscount);
            log.info("GST Amount: {}", gstPrice);
            log.info("Final Amount: {}", finalAmount);


            createStockDto.setAmount(finalAmount);

            log.info("Calculated total amount: {}", finalAmount);



            return true;

        } else {
            log.info("invalid input");
            return false;
        }
    }

    @Override
    public Long findBillNo() {
        Long latestBillNo = billRepo.findBillNo(); // Fetch latest bill number from repository
        return (latestBillNo != null) ? latestBillNo : 1000L; // Default to 1000 if no bill exists
    }

    @Override
    public void saveBill(BillEntity billEntity){
        billRepo.save(billEntity);
    }

    @Override
    public void pdfGenerate(List<BillDto> billDtoList, List<CreateStockDto> createStockDtoList, MediSalesDto mediSalesDto, Double finalAmount, MediSalesDto medDto) {
        Context context = new Context();
        context.setVariable("person", mediSalesDto); //for  contactperson(Buyer details)
        context.setVariable("createStock",createStockDtoList); // for medicine list
        context.setVariable("bills",billDtoList); //for bill details
        context.setVariable("login",medDto); // for login  details
        context.setVariable("finalAmount", finalAmount);

        String htmlData = templateEngine.process("SalesBill",context);
        log.info("Html data: {}", htmlData);


        File file = new File("D://medisalesproject//SalesBills//" + "SalesBill.pdf");
        try(FileOutputStream fileOutputStream = new FileOutputStream(file.getPath())){
            HtmlConverter.convertToPdf(htmlData,fileOutputStream);
        }catch (IOException e) {
            log.error("Error while generating the PDF file.", e);
            throw new RuntimeException("Could not generate PDF file. Check logs.");
        }

    }

}