package com.xworkz.medisales.services.distributorService;

import com.xworkz.medisales.dto.CreateStockDto;
import com.xworkz.medisales.entity.CreateStockEntity;

import java.util.List;

public interface CreateStockService {

    boolean validateAndSaveStock(CreateStockDto createStockDto);

    String generateBatchNumber(String productName, String productCompany);

    String generateHSNNumber();

    CreateStockDto getStockById(int id);

    List<CreateStockDto> getAllStockDetails();



    //calculate the discount
    Double calculateDiscount(Double mrp, Double rate);



    //calculate the gst
    Double calculateGst(Double mrp);

    Double calculateAndSaveStock(double mrp, double rate, int quantity, double discount, double gstAmount);

    CreateStockDto getBillByProductName(String productName);

    List<CreateStockDto> getStockDetailsByDistributor(String loggedInEmail);

  //  List<CreateStockDto> fetchStockByEmail(String email);

 //   boolean updateStock(CreateStockDto createStockDto);


    // CreateStockDto getProductByName(String productName);
}
