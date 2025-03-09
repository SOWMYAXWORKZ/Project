package com.xworkz.medisales.services.distributorService;


import com.xworkz.medisales.dto.CreateStockDto;
import com.xworkz.medisales.entity.CreateStockEntity;
import com.xworkz.medisales.repository.distributorRepo.CreateStockRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Slf4j
@Service

public class CreateStockServiceImpl implements CreateStockService {

    @Autowired
    CreateStockRepo createStockRepo;

    @Override
    public boolean validateAndSaveStock(CreateStockDto createStockDto) {
        boolean validateStock = false;
        CreateStockEntity createStockEntity = new CreateStockEntity();
        log.info("validateAndStock method is invoking...");

        // Validate product company and name
        if (createStockDto.getProductCompany() != null && !createStockDto.getProductCompany().isEmpty() &&
                createStockDto.getProductName() != null && !createStockDto.getProductName().isEmpty()) {

            // Null checks for mrp, rate, discount, gst, amount before setting values
            if (createStockDto.getMrp() == null) {
                createStockDto.setMrp(0.0); // Set default value if null
            }

            if (createStockDto.getRate() == null) {
                createStockDto.setRate(0.0); // Set default value if null
            }

            if (createStockDto.getDiscount() == null) {
                createStockDto.setDiscount(0.0); // Set default value if null
            }

            if (createStockDto.getGst() == null) {
                createStockDto.setGst(0.0); // Set default value if null
            }

            if (createStockDto.getAmount() == null) {
                createStockDto.setAmount(0.0); // Set default value if null
            }
            if (createStockDto.getEmail() != null) {
                createStockEntity.setEmail(createStockDto.getEmail());
            }


            // Copy properties from DTO to entity (no need to handle null checks for mrp and rate)
            BeanUtils.copyProperties(createStockDto, createStockEntity);

            // Set batch number and HSN number
            createStockEntity.setBatchNumber(generateBatchNumber(createStockEntity.getProductName(), createStockEntity.getProductCompany()));
            createStockEntity.setHsn(generateHSNNumber());

            // Set created by, created date, and time
            createStockEntity.setCreatedBy(createStockEntity.getProductCompany());
            createStockEntity.setCreatedDate(LocalDate.now());
            createStockEntity.setCreatedTime(Time.valueOf(new SimpleDateFormat("HH:mm:ss").format(new Date())));

            //  discount calculation
            double discount = calculateDiscount(createStockDto.getMrp(), createStockDto.getRate());
            createStockEntity.setDiscount(discount);

            // Explicitly handle GST calculation
            double gstAmount = calculateGst(createStockDto.getMrp());
            createStockEntity.setGst(gstAmount);

            // Calculate total amount
            double amount = calculateAndSaveStock(createStockDto.getMrp(), createStockDto.getRate(), createStockDto.getQuantity(), discount, gstAmount);
            createStockEntity.setAmount(amount);

            log.info("Calculated Amount: {}", amount);

            // Save the entity to the repository
            log.info("Now the data is going to save : {}", createStockEntity);
            validateStock = createStockRepo.addStock(createStockEntity);
        } else {
            log.info("Validation failed: Product Company or Name is missing");
        }

        return validateStock;
    }

    @Override
    public String generateBatchNumber(String productName, String productCompany) {
        Random random = new Random();
        int randomNum = random.nextInt(900) + 100;  // it generate 3-digit random number

        char productFirstChar = (productName != null && !productName.isEmpty()) ? productName.charAt(0) : 'X';
        char companyFirstChar = (productCompany != null && !productCompany.isEmpty()) ? productCompany.charAt(0) : 'Y';

        return "" + productFirstChar + randomNum + companyFirstChar; //Batch Number Format

    }

    @Override
    public String generateHSNNumber() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddyyHHmmss");
        return LocalDateTime.now().format(formatter);
    }

    @Override
    public CreateStockDto getStockById(int id) {
        if (id > 0) {
            return createStockRepo.getStockById(id);
        }
        return null;
    }

    @Override
    public List<CreateStockDto> getAllStockDetails() {
        return createStockRepo.getAllStockDetails();
    }

    //calculate the discount
    @Override
    public Double calculateDiscount(Double mrp, Double rate) {
        double discountPrice = (mrp - rate);
        return ((discountPrice / mrp) * 100);
    }

    //calculate the gst
    @Override
    public Double calculateGst(Double mrp) {
        double gstRate = 12.0f;
        return (mrp * gstRate) / 100;
    }

    // calculate the  amount
    @Override
    public Double calculateAndSaveStock(double mrp, double rate, int quantity, double discount, double gstAmount) {
        double subtotal = rate * quantity; // Subtotal = rate * quantity
        double discountAmount = (subtotal * discount) / 100; // Discount amount
        double gst = (subtotal * gstAmount) / 100; // GST amount
        return (subtotal - discountAmount) + gst; // Final amount
    }

    @Override
    public CreateStockDto getBillByProductName(String productName) {
        CreateStockDto createStockDto = new CreateStockDto();
        CreateStockEntity createStockEntity = createStockRepo.getBillByProductName(productName);
        BeanUtils.copyProperties(createStockEntity, createStockDto);

        return createStockDto;
    }

    // thorugh email
    @Override
    public List<CreateStockDto> getStockDetailsByDistributor(String loggedInEmail) {
        if (loggedInEmail != null && !loggedInEmail.isEmpty()) {
            return createStockRepo.getStockDetailsByDistributor(loggedInEmail);
        }
        return new ArrayList<>(); // return an empty list
    }

//    @Override
//    public List<CreateStockDto> fetchStockByEmail(String email) {
//       return createStockRepo.fetchStockByEmail(email);
//    }

//    @Override
//    public boolean updateStock(CreateStockDto createStockDto) {
//        createStockRepo.updateBy
//    }


}

