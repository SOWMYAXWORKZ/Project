package com.xworkz.medisales.repository.retailerRepo;

import com.xworkz.medisales.dto.RetailerDto;
import com.xworkz.medisales.entity.RetailerEntity;

import java.util.List;

public interface RetailerRepo {
    boolean save(RetailerEntity retailerEntity);

    List<RetailerDto> getAllVendorsDetails();

    void updateRetailer(RetailerDto retailerDto);

    RetailerDto fetchRetailerById(int id);

    boolean deleteRetailerById(int id);
}
