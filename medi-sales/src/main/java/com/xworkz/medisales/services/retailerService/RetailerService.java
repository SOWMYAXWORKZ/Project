package com.xworkz.medisales.services.retailerService;

import com.xworkz.medisales.dto.RetailerDto;
import com.xworkz.medisales.dto.VendorDto;

import java.util.List;

public interface RetailerService {

    boolean validateAndSave(RetailerDto retailerDto);

    List<RetailerDto> getAllRetailersDetails();

    void updateRetailer(RetailerDto retailerDto);

    RetailerDto getRetailerById(int id); // fetch

    void deleteRetailerById(int id);
}
